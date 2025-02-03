import java.util.*;
import java.io.*;

/**
 * Classe principale per la gestione di un torneo di scacchi.
 * - Legge il calendario da un file CSV
 * - Permette di iniziare da un turno qualsiasi e fermarsi prima dell'ultimo
 * - Raccoglie i risultati delle partite e li salva su file
 * - Controlla gli input dell'utente per evitare errori
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lettura del calendario da file
        List<List<String>> partite = leggiCalendarioDaFile("calendario.csv");

        if (partite.isEmpty()) {
            System.out.println("Errore: il calendario non è stato trovato o è vuoto.");
            return;
        }

        int turnoInizio = -1, turnoFine = -1;

        // Richiesta del turno di inizio con validazione dell'input
        while (turnoInizio < 0 || turnoInizio >= partite.size()) {
            try {
                System.out.print("Da quale turno vuoi iniziare? (1-" + partite.size() + "): ");
                turnoInizio = Integer.parseInt(scanner.next()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci un numero valido.");
            }
        }

        // Richiesta del turno di fine con validazione dell'input
        while (turnoFine < turnoInizio || turnoFine >= partite.size()) {
            try {
                System.out.print("Fino a quale turno vuoi giocare? (1-" + partite.size() + "): ");
                turnoFine = Integer.parseInt(scanner.next()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci un numero valido.");
            }
        }

        // Raccogli i risultati solo per i turni selezionati
        raccogliRisultati(scanner, partite, turnoInizio, turnoFine);
        scanner.close();
    }

    /**
     * Legge il calendario delle partite da un file CSV.
     * @param nomeFile Nome del file CSV contenente il calendario.
     * @return Lista di liste, dove ogni lista interna rappresenta un turno di partite.
     */
    public static List<List<String>> leggiCalendarioDaFile(String nomeFile) {
        List<List<String>> calendario = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> partiteTurno = Arrays.asList(line.split(",")); // Supponendo CSV con partite separate da virgola
                calendario.add(partiteTurno);
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return calendario;
    }

    /**
     * Raccoglie i risultati delle partite giocate nei turni selezionati.
     * I risultati devono essere inseriti nel formato scacchistico:
     * - "1-0" per la vittoria del Bianco
     * - "0-1" per la vittoria del Nero
     * - "½-½" per la patta
     * - "-" se la partita non è stata giocata
     *
     * @param scanner Scanner per l'input utente.
     * @param partite Lista delle partite del torneo.
     * @param turnoInizio Turno da cui iniziare la registrazione dei risultati.
     * @param turnoFine Ultimo turno da registrare.
     */
    public static void raccogliRisultati(Scanner scanner, List<List<String>> partite, int turnoInizio, int turnoFine) {
        List<String> risultati = new ArrayList<>();
        
        for (int turno = turnoInizio; turno <= turnoFine; turno++) {
            System.out.println("Turno " + (turno + 1) + ":");
            List<String> partiteTurno = partite.get(turno);
            for (String partita : partiteTurno) {
                String risultato = "";
                while (!risultato.matches("1-0|0-1|½-½|-")) {
                    System.out.print("Risultato di " + partita + " (1-0, 0-1, ½-½, -): ");
                    risultato = scanner.next();
                    if (!risultato.matches("1-0|0-1|½-½|-")) {
                        System.out.println("Errore: risultato non valido, riprova.");
                    }
                }
                risultati.add("Turno " + (turno + 1) + ", " + partita + ": " + risultato);
            }
        }

        // Salva i risultati su file
        salvaRisultatiSuFile(risultati);
    }

    /**
     * Salva i risultati delle partite in un file CSV.
     * @param risultati Lista dei risultati delle partite.
     */
    public static void salvaRisultatiSuFile(List<String> risultati) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("risultati.csv", true))) {
            for (String r : risultati) {
                bw.write(r);
                bw.newLine();
            }
            System.out.println("Risultati salvati correttamente.");
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei risultati: " + e.getMessage());
        }
    }
}
