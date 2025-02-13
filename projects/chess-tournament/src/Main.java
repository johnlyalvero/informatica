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
    private static final String DATA_FOLDER = "data/";
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
                System.out.println("1. Inserire risultati");
                System.out.println("2. Generare nuovi turni");
                System.out.println("3. Visualizzare classifica");
                System.out.println("4. Visualizzare turni svolti");
                System.out.println("5. Esci");
                System.out.println("======================================");

                System.out.print("Scelta: ");
                
                int scelta = Integer.parseInt(scanner.nextLine());

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
                        mostraTurniSvolti();
                        attendiInputPrimaDiContinuare();
                    }
                    case 5 -> {
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
                        bw.write("Nome,Cognome,Win,Lose,Draw,ELO");
                        bw.newLine();
                        System.out.println("Il database dei giocatori non esiste. Inserisci i giocatori:");

                        while (true) {
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine().trim();
                            System.out.print("Cognome: ");
                            String cognome = scanner.nextLine().trim();

                            bw.write(nome + "," + cognome + ",0,0,0," + ELO_DEFAULT);
                            bw.newLine();

                            System.out.print("Vuoi aggiungere un altro giocatore? (s/n): ");
                            String risposta = scanner.nextLine().trim().toLowerCase();
                            if (!risposta.equals("s")) {
                                break;
                            }
                        }
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

    private static void inserireRisultati() {
        try {
            System.out.print("Inserisci il turno: ");
            int turno = Integer.parseInt(scanner.nextLine());

            System.out.print("Inserisci il nome del primo giocatore: ");
            String giocatore1 = scanner.nextLine().trim();

            System.out.print("Inserisci il nome del secondo giocatore: ");
            String giocatore2 = scanner.nextLine().trim();

            System.out.print("Inserisci il risultato (1-0, 0-1, 1/2-1/2): ");
            String risultato = scanner.nextLine().trim();

            aggiornaElo(giocatore1, giocatore2, risultato, turno);
        } catch (NumberFormatException e) {
            System.out.println("Errore nell'inserimento, riprova.");
        }
    }

    private static void aggiornaElo(String giocatore1, String giocatore2, String risultato, int turno) {
        System.out.println("Salvataggio automatico Elo in corso...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RISULTATI_FILE, true))) {
            calcolaElo(giocatore1, giocatore2, risultato);
            bw.write(turno + "," + giocatore1 + "," + giocatore2 + "," + risultato + "," + "NUOVI_ELO_CALCOLATI");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del risultato.");
        }
    }

    private static void calcolaElo(String giocatore1, String giocatore2, String risultato) {
        Map<String, String[]> giocatoriMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dati = line.split(",");
                if (dati.length == 6) {
                    giocatoriMap.put(dati[0] + " " + dati[1], dati);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file giocatori.");
            return;
        }

        if (!giocatoriMap.containsKey(giocatore1) || !giocatoriMap.containsKey(giocatore2)) {
            System.out.println("Errore: uno o entrambi i giocatori non trovati.");
            return;
        }

        String[] dati1 = giocatoriMap.get(giocatore1);
        String[] dati2 = giocatoriMap.get(giocatore2);

        int elo1 = Integer.parseInt(dati1[5]);
        int elo2 = Integer.parseInt(dati2[5]);

        double expected1 = 1 / (1 + Math.pow(10, (elo2 - elo1) / 400.0));
        double expected2 = 1 / (1 + Math.pow(10, (elo1 - elo2) / 400.0));

        double score1 = risultato.equals("1-0") ? 1 : risultato.equals("1/2-1/2") ? 0.5 : 0;
        double score2 = 1 - score1;

        int nuovoElo1 = (int) Math.round(elo1 + K_FACTOR * (score1 - expected1));
        int nuovoElo2 = (int) Math.round(elo2 + K_FACTOR * (score2 - expected2));

        dati1[5] = String.valueOf(nuovoElo1);
        dati2[5] = String.valueOf(nuovoElo2);

        if (score1 == 1) {
            dati1[2] = String.valueOf(Integer.parseInt(dati1[2]) + 1);
            dati2[3] = String.valueOf(Integer.parseInt(dati2[3]) + 1);
        } else if (score2 == 1) {
            dati2[2] = String.valueOf(Integer.parseInt(dati2[2]) + 1);
            dati1[3] = String.valueOf(Integer.parseInt(dati1[3]) + 1);
        } else {
            dati1[4] = String.valueOf(Integer.parseInt(dati1[4]) + 1);
            dati2[4] = String.valueOf(Integer.parseInt(dati2[4]) + 1);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GIOCATORI_FILE))) {
            bw.write("Nome,Cognome,Win,Lose,Draw,ELO");
            bw.newLine();
            for (String[] dati : giocatoriMap.values()) {
                bw.write(String.join(",", dati));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del file giocatori.");
        }

        System.out.println("Elo aggiornato: " + giocatore1 + " (" + nuovoElo1 + "), " + giocatore2 + " (" + nuovoElo2 + ")");
    }

    private static void generaNuoviTurni() {
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
            bw.write("Turno " + turno + "," + String.join(",", nuoviTurni));
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

    private static void mostraClassifica() {
        List<String[]> giocatori = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(GIOCATORI_FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                giocatori.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura della classifica.");
            return;
        }

        giocatori.sort((a, b) -> Integer.compare(Integer.parseInt(b[5]), Integer.parseInt(a[5])));

        System.out.println("======================================");
        System.out.println("            CLASSIFICA               ");
        System.out.println("======================================");
        System.out.printf("%-20s %-10s %-10s %-10s %-10s\n", "Nome", "Vittorie", "Sconfitte", "Patte", "Elo");
        System.out.println("--------------------------------------------------------------");

        for (String[] giocatore : giocatori) {
            System.out.printf("%-20s %-10s %-10s %-10s %-10s\n",
                    giocatore[0] + " " + giocatore[1],
                    giocatore[2],
                    giocatore[3],
                    giocatore[4],
                    giocatore[5]);
        }
        System.out.println("==============================================================");
    }

    private static void mostraTurniSvolti() {
        System.out.println("======================================");
        System.out.println("           TURNI SVOLTI               ");
        System.out.println("======================================");

        try (BufferedReader br = new BufferedReader(new FileReader(RISULTATI_FILE))) {
            String line = br.readLine();
            if (line == null) {
                System.out.println("Nessun turno svolto finora.");
                return;
            }

            while ((line = br.readLine()) != null) {
                String[] dati = line.split(",");
                System.out.printf("Turno %s: %s vs %s | Risultato: %s | Nuovo Elo: %s - %s\n",
                        dati[0],
                        dati[1],
                        dati[2],
                        dati[3],
                        dati[4],
                        dati[5]);
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura dei turni svolti.");
        }

        System.out.println("======================================");
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static void attendiInputPrimaDiContinuare() {
        System.out.println("\nPremi INVIO per tornare al menu...");
        scanner.nextLine();
    }
}