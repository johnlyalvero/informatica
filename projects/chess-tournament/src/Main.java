import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final String DATA_FOLDER = "./data/";
    private static final String GIOCATORI_FILE = DATA_FOLDER + "giocatori.csv";
    private static final String CALENDARIO_FILE = DATA_FOLDER + "calendario.csv";
    private static final String RISULTATI_FILE = DATA_FOLDER + "risultati.csv";
    private static final int ELO_DEFAULT = 800;
    private static final int K_FACTOR = 32;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inizializzaDatabase();
        while (true) {
            clearScreen();
            System.out.println("======================================");
            System.out.println("          TORNEO DI SCACCHI          ");
            System.out.println("======================================");
            System.out.println("\n1. Inserire risultati");
            System.out.println("2. Generare nuovi turni");
            System.out.println("\n3. Visualizzare classifica");
            System.out.println("4. Visualizzare giocatori");
            System.out.println("\n5. Visualizzare turno corrente");
            System.out.println("6. Visualizzare turni svolti");
            System.out.println("\n0. Esci");
            System.out.println("======================================");

            System.out.print("Scelta: ");
            
            String input = scanner.nextLine().trim();
            
            // Controllo input per assicurarsi che sia un numero valido
            if (!input.matches("[0-6]")) {
                System.out.println("Scelta non valida, inserisci un numero tra 0 e 6.");
                attendiInputPrimaDiContinuare();
                continue;
            }

            int scelta = Integer.parseInt(input);

            switch (scelta) {
                case 1 -> {
                    inserireRisultati();
                    attendiInputPrimaDiContinuare();
                }
                case 2 -> {
                    generaNuoviTurni();
                    attendiInputPrimaDiContinuare();
                }
                case 3 -> {
                    mostraClassifica();
                    attendiInputPrimaDiContinuare();
                }
                case 4 -> {
                    mostraGiocatoriPerCodice();
                    attendiInputPrimaDiContinuare();
                }
                case 5 -> {
                    mostraTurnoCorrente();
                    attendiInputPrimaDiContinuare();
                }
                case 6 -> {
                    mostraTurniSvolti();
                    attendiInputPrimaDiContinuare();
                }
                case 0 -> {
                    System.out.println("Grazie per aver usato il sistema. Arrivederci!");
                    return;
                }
                default -> {
                    System.out.println("Scelta non valida, riprova.");
                    attendiInputPrimaDiContinuare();
                }
            }
        }
    }

    private static void inizializzaDatabase() {
   
            File dataFolder = new File(DATA_FOLDER);
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            try {
                File fileGiocatori = new File(GIOCATORI_FILE);
                if (!fileGiocatori.exists()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileGiocatori))) {
                        bw.write("ID,Nome,Cognome,Win,Lose,Draw,ELO");
                        bw.newLine();
                        System.out.println("Il database dei giocatori non esiste. Inserisci i giocatori:");

                        System.out.print("Lista giocatori: ");
                        String input = scanner.nextLine().trim();
                        
                        // Controllo validità input: solo lettere, spazi e virgole
                        if (!input.matches("[a-zA-Z ,]+")) {
                            System.err.println("Errore: L'input può contenere solo lettere, spazi e virgole.");
                            return;
                        }
                        
                        String[] giocatori = input.split(",");
                        int id = 1;
                    
                        for (String giocatore : giocatori) {
                            String[] nomi = giocatore.trim().split(" ");
                            
                            if (nomi.length >= 3) {
                                String nome = nomi[0] + " " + nomi[1];
                                String cognome = nomi[2];
                                bw.write(id + "," + nome + "," + cognome + ",0,0,0," + ELO_DEFAULT);
                            } else if (nomi.length == 2) {
                                bw.write(id + "," + nomi[0] + "," + nomi[1] + ",0,0,0," + ELO_DEFAULT);
                            } else if (nomi.length == 1) {
                                bw.write(id + "," + nomi[0] + ", ,0,0,0," + ELO_DEFAULT);
                            }
                            bw.newLine();
                            id++;
                        }

                        System.out.println("Salvataggio completato.");
                    }
                }

                File fileCalendario = new File(CALENDARIO_FILE);
                if (!fileCalendario.exists()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileCalendario))) {
                        bw.write("Turno,Partite");
                        bw.newLine();
                    }
                }

                File fileRisultati = new File(RISULTATI_FILE);
                if (!fileRisultati.exists()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRisultati))) {
                        bw.write("Turno,Giocatore1,Giocatore2,Risultato,NuoviElo");
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Errore nella creazione dei file: " + e.getMessage());
            }
        
    }

    //GESTIONE DEI TURNI E DEI RISULTATI
    private static void inserireRisultati() {
        try (BufferedReader calendarioReader = new BufferedReader(new FileReader(CALENDARIO_FILE));
             BufferedReader risultatiReader = new BufferedReader(new FileReader(RISULTATI_FILE));
             BufferedWriter risultatiWriter = new BufferedWriter(new FileWriter(RISULTATI_FILE, true))) {
    
            String ultimaLineaCalendario = null;
            String line;
    
            // Trova l'ultimo turno nel calendario
            while ((line = calendarioReader.readLine()) != null) {
                ultimaLineaCalendario = line;
            }
    
            if (ultimaLineaCalendario == null || ultimaLineaCalendario.startsWith("Turno")) {
                System.out.println("Nessun turno attivo trovato.");
                return;
            }
    
            String[] calendarioParti = ultimaLineaCalendario.split(",");
            int turnoCorrente = Integer.parseInt(calendarioParti[0].trim());
    
            // Memorizza le partite del turno corrente
            List<String> partite = new ArrayList<>();
            for (int i = 1; i < calendarioParti.length; i++) {
                partite.add(calendarioParti[i].trim());
            }
    
            // Memorizza le partite già registrate nei risultati
            Set<String> partiteRegistrate = new HashSet<>();
            risultatiReader.readLine(); // Salta l'intestazione
            while ((line = risultatiReader.readLine()) != null) {
                String[] risultatiParti = line.split(",");
                if (risultatiParti.length >= 4 && Integer.parseInt(risultatiParti[0].trim()) == turnoCorrente) {
                    String match = risultatiParti[1].trim() + " vs " + risultatiParti[2].trim();
                    partiteRegistrate.add(match);
                }
            }
    
            // Filtra le partite senza risultato
            List<String> partiteNonRegistrate = new ArrayList<>();
            for (String partita : partite) {
                if (!partiteRegistrate.contains(partita)) {
                    partiteNonRegistrate.add(partita);
                }
            }
    
            if (partiteNonRegistrate.isEmpty()) {
                System.out.println("Tutte le partite di questo turno hanno già un risultato registrato.");
                return;
            }
    
            // Mostra le partite disponibili per l'inserimento del risultato
            System.out.println("Seleziona la partita a cui vuoi aggiungere un risultato:");
            for (int i = 0; i < partiteNonRegistrate.size(); i++) {
                System.out.println((i + 1) + ". " + partiteNonRegistrate.get(i));
            }
    
            System.out.print("Inserisci il numero della partita: ");
            int sceltaPartita;
            try {
                sceltaPartita = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (sceltaPartita < 0 || sceltaPartita >= partiteNonRegistrate.size()) {
                    System.out.println("Scelta non valida.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                return;
            }
    
            String partitaSelezionata = partiteNonRegistrate.get(sceltaPartita);
            String[] giocatori = partitaSelezionata.split(" vs ");
            if (giocatori.length != 2) {
                System.out.println("Errore nel formato della partita.");
                return;
            }
    
            String giocatore1 = giocatori[0].trim();
            String giocatore2 = giocatori[1].trim();
    
            // Chiede il risultato con controllo input valido
            String risultato;
            while (true) {
                System.out.print("Inserisci il risultato (1-0, 0-1, 1/2-1/2): ");
                risultato = scanner.nextLine().trim();
                if (risultato.matches("1-0|0-1|1/2-1/2")) {
                    break;
                } else {
                    System.out.println("Errore: Risultato non valido. Inserisci '1-0', '0-1' o '1/2-1/2'.");
                }
            }
    
            // Registra il risultato e aggiorna Elo
            aggiornaElo(giocatore1, giocatore2, risultato, turnoCorrente);
    
        } catch (IOException e) {
            System.out.println("Errore nella lettura/scrittura dei file: " + e.getMessage());
        }
    }    
     
    private static void aggiornaElo(String giocatore1, String giocatore2, String risultato, int turno) {
        System.out.println("Salvataggio automatico Elo in corso...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RISULTATI_FILE, true))) {
            calcolaElo(giocatore1, giocatore2, risultato);
            bw.write(turno + "," + giocatore1 + "," + giocatore2 + "," + risultato + ",NUOVI_ELO_CALCOLATI");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del risultato.");
        }
    }    

    private static void calcolaElo(String idGiocatore1, String idGiocatore2, String risultato) {
        Map<String, String[]> giocatoriMap = new HashMap<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line;
            br.readLine(); // Salta l'intestazione
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length == 7) {
                    giocatoriMap.put(dati[0].trim(), dati);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file giocatori.");
            return;
        }
    
        if (!giocatoriMap.containsKey(idGiocatore1) || !giocatoriMap.containsKey(idGiocatore2)) {
            System.out.println("Errore: Uno o entrambi i giocatori non trovati.");
            return;
        }
    
        String[] dati1 = giocatoriMap.get(idGiocatore1);
        String[] dati2 = giocatoriMap.get(idGiocatore2);
    
        int elo1 = Integer.parseInt(dati1[6]); // Elo del primo giocatore
        int elo2 = Integer.parseInt(dati2[6]); // Elo del secondo giocatore
    
        double expected1 = 1 / (1 + Math.pow(10, (elo2 - elo1) / 400.0));
        double expected2 = 1 / (1 + Math.pow(10, (elo1 - elo2) / 400.0));
    
        double score1 = risultato.equals("1-0") ? 1 : risultato.equals("1/2-1/2") ? 0.5 : 0;
        double score2 = 1 - score1;
    
        int nuovoElo1 = (int) Math.round(elo1 + K_FACTOR * (score1 - expected1));
        int nuovoElo2 = (int) Math.round(elo2 + K_FACTOR * (score2 - expected2));
    
        dati1[6] = String.valueOf(nuovoElo1);
        dati2[6] = String.valueOf(nuovoElo2);
    
        // Aggiorna statistiche vittorie/sconfitte/pareggi
        if (score1 == 1) {
            dati1[3] = String.valueOf(Integer.parseInt(dati1[3]) + 1);
            dati2[4] = String.valueOf(Integer.parseInt(dati2[4]) + 1);
        } else if (score2 == 1) {
            dati2[3] = String.valueOf(Integer.parseInt(dati2[3]) + 1);
            dati1[4] = String.valueOf(Integer.parseInt(dati1[4]) + 1);
        } else {
            dati1[5] = String.valueOf(Integer.parseInt(dati1[5]) + 1);
            dati2[5] = String.valueOf(Integer.parseInt(dati2[5]) + 1);
        }
    
        // Scrive il file aggiornato
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GIOCATORI_FILE))) {
            bw.write("ID,Nome,Cognome,Win,Lose,Draw,ELO");
            bw.newLine();
            for (String[] dati : giocatoriMap.values()) {
                bw.write(String.join(",", dati));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del file giocatori.");
        }
    
        System.out.println("Elo aggiornato: " + idGiocatore1 + " (" + nuovoElo1 + "), " + idGiocatore2 + " (" + nuovoElo2 + ")");
    }

    private static void generaNuoviTurni() {
        if(!turnoCorrenteFinito()) {
            System.out.println("Il turno corrente non è ancora finito!");
            return;
        }

        System.out.println("Generazione nuovi turni in corso...");
        List<String[]> giocatori = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                giocatori.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura dei giocatori.");
            return;
        }

        giocatori.sort((a, b) -> Integer.compare(Integer.parseInt(b[5]), Integer.parseInt(a[5])));

        List<String> nuoviTurni = new ArrayList<>();
        Set<String> coppieGiocate = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CALENDARIO_FILE))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 1; i < parts.length; i++) {
                    coppieGiocate.add(parts[i]);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del calendario.");
        }

        for (int i = 0; i < giocatori.size() - 1; i += 2) {
            String match = giocatori.get(i)[0] + " vs " + giocatori.get(i + 1)[0];

            if (coppieGiocate.contains(match)) {
                if (i + 2 < giocatori.size()) {
                    String temp = giocatori.get(i + 1)[0];
                    giocatori.get(i + 1)[0] = giocatori.get(i + 2)[0];
                    giocatori.get(i + 2)[0] = temp;
                    match = giocatori.get(i)[0] + " vs " + giocatori.get(i + 1)[0];
                }
            }

            nuoviTurni.add(match);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CALENDARIO_FILE, true))) {
            int turno = contaLinee(CALENDARIO_FILE);
            bw.write("\n" + turno + "," + String.join(",", nuoviTurni));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei nuovi turni.");
        }

        System.out.println("Nuovi turni generati con successo!");
    }

    private static int contaLinee(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int lines = 0;
            while (br.readLine() != null) {
                lines++;
            }
            return lines;
        } catch (IOException e) {
            return 1;
        }
    }

    //VISUALIZZAZIONE DEI DATI
    private static void mostraClassifica() {
        List<String[]> giocatori = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            br.readLine(); // Salta l'intestazione
            String line;
            while ((line = br.readLine()) != null) {
                giocatori.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura della classifica.");
            return;
        }
    
        // Ordina i giocatori in base all'ELO (indice 6)
        giocatori.sort((a, b) -> Integer.compare(Integer.parseInt(b[6]), Integer.parseInt(a[6])));
    
        System.out.println("======================================");
        System.out.println("            CLASSIFICA               ");
        System.out.println("======================================");
        System.out.printf("%-10s %-30s %-10s %-10s %-10s %-10s\n", "ID", "Nome", "Vittorie", "Sconfitte", "Patte", "Elo");
        System.out.println("--------------------------------------------------------------");
    
        for (String[] giocatore : giocatori) {
            System.out.printf("%-10s %-30s %-10s %-10s %-10s %-10s\n",
                    giocatore[0], 
                    giocatore[1]+ " " + giocatore[2],
                    giocatore[3],
                    giocatore[4],
                    giocatore[5],
                    giocatore[6]);
        }
        System.out.println("==============================================================");
    }    

    private static void mostraTurniSvolti() {
        System.out.println("======================================");
        System.out.println("           TURNI SVOLTI               ");
        System.out.println("======================================");
    
        // Mappa per associare ID giocatore ai nomi completi
        Map<String, String[]> mappaGiocatori = new HashMap<>();
        
        try (BufferedReader giocatoriReader = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line = giocatoriReader.readLine(); // Salta l'intestazione
            while ((line = giocatoriReader.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length >= 7) {
                    String id = dati[0].trim();
                    mappaGiocatori.put(id, dati);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura dei giocatori: " + e.getMessage());
            return;
        }
    
        try (BufferedReader risultatiReader = new BufferedReader(new FileReader(RISULTATI_FILE))) {
            String line = risultatiReader.readLine(); // Salta l'intestazione
            if (line == null) {
                System.out.println("Nessun turno svolto finora.");
                return;
            }
    
            Map<Integer, List<String>> turni = new HashMap<>();
    
            while ((line = risultatiReader.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length < 5) continue;
    
                int turno = Integer.parseInt(dati[0].trim());
                String id1 = dati[1].trim();
                String id2 = dati[2].trim();
                String risultato = dati[3].trim();
                String nuoviElo = dati[4].trim();
    
                String[] datiGiocatore1 = mappaGiocatori.getOrDefault(id1, new String[]{id1, "Sconosciuto", ""});
                String[] datiGiocatore2 = mappaGiocatori.getOrDefault(id2, new String[]{id2, "Sconosciuto", ""});
    
                String nome1 = datiGiocatore1[1] + " " + datiGiocatore1[2];
                String nome2 = datiGiocatore2[1] + " " + datiGiocatore2[2];
                String elo1 = datiGiocatore1[6];
                String elo2 = datiGiocatore2[6];
    
                String partita = "- " + id1 + " vs " + id2 + " | " + nome1 + " vs " + nome2 +
                                 " | Risultato: " + risultato +
                                 " | Nuovo Elo: " + elo1 + " - " + elo2;
    
                turni.putIfAbsent(turno, new ArrayList<>());
                turni.get(turno).add(partita);
            }
    
            // Stampa i turni e le partite svolte
            for (Map.Entry<Integer, List<String>> entry : turni.entrySet()) {
                System.out.println("Turno " + entry.getKey() + ":");
                for (String match : entry.getValue()) {
                    System.out.println(match);
                }
                System.out.println("--------------------------------------");
            }
    
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore nella lettura dei file: " + e.getMessage());
        }
    
        System.out.println("======================================");
    }
    
    private static void mostraTurnoCorrente() {
        System.out.println("======================================");
        System.out.println("        TURNO CORRENTE     ");
        System.out.println("======================================");
    
        // Mappa per associare ID giocatore ai nomi completi
        Map<String, String> mappaGiocatori = new HashMap<>();
        
        try (BufferedReader giocatoriReader = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line = giocatoriReader.readLine(); // Salta l'intestazione
            while ((line = giocatoriReader.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length >= 3) {
                    String id = dati[0].trim();
                    String nomeCompleto = dati[1].trim() + " " + dati[2].trim();
                    mappaGiocatori.put(id, nomeCompleto);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura dei giocatori: " + e.getMessage());
            return;
        }
    
        try (BufferedReader calendarioReader = new BufferedReader(new FileReader(CALENDARIO_FILE));
             BufferedReader risultatiReader = new BufferedReader(new FileReader(RISULTATI_FILE))) {
            String ultimaLineaCalendario = null;
            String line;
    
            // Trova l'ultimo turno nel calendario
            while ((line = calendarioReader.readLine()) != null) {
                ultimaLineaCalendario = line;
            }
    
            if (ultimaLineaCalendario == null || ultimaLineaCalendario.startsWith("Turno")) {
                System.out.println("Nessun turno attivo trovato.");
                return;
            }
    
            String[] calendarioParti = ultimaLineaCalendario.split(",");
            int turnoCorrente = Integer.parseInt(calendarioParti[0].replace("Turno ", "").trim());
    
            // Memorizza le partite del turno corrente con stato "In attesa"
            Map<String, String> partite = new HashMap<>();
            for (int i = 1; i < calendarioParti.length; i++) {
                partite.put(calendarioParti[i].trim(), "In attesa");
            }
    
            // Controlla se ci sono risultati registrati
            risultatiReader.readLine(); // Salta l'intestazione
            while ((line = risultatiReader.readLine()) != null) {
                String[] risultatiParti = line.split(",");
                if (risultatiParti.length >= 4 && Integer.parseInt(risultatiParti[0].trim()) == turnoCorrente) {
                    String match = risultatiParti[1].trim() + " vs " + risultatiParti[2].trim();
                    partite.put(match, "Risultato: " + risultatiParti[3].trim());
                }
            }
    
            // Mostra la situazione del turno
            System.out.println("Turno " + turnoCorrente + ":");
            for (Map.Entry<String, String> entry : partite.entrySet()) {
                String[] giocatori = entry.getKey().split(" vs ");
                if (giocatori.length == 2) {
                    String nome1 = mappaGiocatori.getOrDefault(giocatori[0], "Sconosciuto");
                    String nome2 = mappaGiocatori.getOrDefault(giocatori[1], "Sconosciuto");
                    System.out.println("- " + giocatori[0] + " vs " + giocatori[1] + " | " + nome1 + " vs " + nome2 + " | " + entry.getValue());
                }
            }
            System.out.println("======================================");
    
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore nella lettura dei file: " + e.getMessage());
        }
    }

    private static void mostraGiocatoriPerCodice() {
        System.out.println("======================================");
        System.out.println("           ELENCO GIOCATORI           ");
        System.out.println("======================================");
        
        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line = br.readLine(); // Salta l'intestazione
            if (line == null) {
                System.out.println("Nessun giocatore trovato.");
                return;
            }
            
            System.out.printf("%-10s %-30s %-10s %-10s %-10s %-10s\n", "ID", "Nome", "Vittorie", "Sconfitte", "Patte", "ELO");
            System.out.println("----------------------------------------------------------------");
            
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length == 7) {
                    System.out.printf("%-10s %-30s %-10s %-10s %-10s %-10s\n",
                            dati[0],
                            dati[1] + " " + dati[2],
                            dati[3],
                            dati[4],
                            dati[5],
                            dati[6]);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file giocatori: " + e.getMessage());
        }
        
        System.out.println("======================================");
    }

    private static boolean turnoCorrenteFinito() {
        try (BufferedReader br = new BufferedReader(new FileReader(CALENDARIO_FILE))) {
            br.readLine(); // Salta l'intestazione
            if (br.readLine() == null) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file calendario.csv: " + e.getMessage());
        }

        try (BufferedReader calendarioReader = new BufferedReader(new FileReader(CALENDARIO_FILE));
             BufferedReader risultatiReader = new BufferedReader(new FileReader(RISULTATI_FILE))) {
    
            String ultimaLineaCalendario = null;
            String line;
    
            // Trova l'ultimo turno nel calendario
            while ((line = calendarioReader.readLine()) != null) {
                ultimaLineaCalendario = line;
            }
    
            if (ultimaLineaCalendario == null || ultimaLineaCalendario.startsWith("Turno")) {
                System.out.println("Nessun turno attivo trovato.");
                return false;
            }
    
            String[] calendarioParti = ultimaLineaCalendario.split(",");
            int turnoCorrente = Integer.parseInt(calendarioParti[0].replace("Turno ", "").trim());
    
            // Costruisce un set con le partite del turno corrente
            Set<String> partiteDaVerificare = new HashSet<>();
            for (int i = 1; i < calendarioParti.length; i++) {
                partiteDaVerificare.add(calendarioParti[i].trim());
            }
    
            // Controlla nel file risultati se le partite del turno corrente hanno un risultato
            risultatiReader.readLine(); // Salta l'intestazione
            while ((line = risultatiReader.readLine()) != null) {
                String[] risultatiParti = line.split(",");
                if (risultatiParti.length >= 4 && Integer.parseInt(risultatiParti[0].trim()) == turnoCorrente) {
                    String match = risultatiParti[1].trim() + " vs " + risultatiParti[2].trim();
                    partiteDaVerificare.remove(match);
                }
            }
    
            // Se il set è vuoto, significa che tutte le partite hanno un risultato
            return partiteDaVerificare.isEmpty();
    
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore nella lettura dei file: " + e.getMessage());
            return false;
        }
    }
    
    //METODI DI UTILITÀ
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static void attendiInputPrimaDiContinuare() {
        System.out.println("\nPremi INVIO per tornare al menu...");
        scanner.nextLine();
    }

}