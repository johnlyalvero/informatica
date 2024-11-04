import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrimeSolver {

    // Mappa per memorizzare le accuse, dove la chiave è il nome del sospetto e il valore è una lista di nomi di persone che accusa
    private Map<String, List<String>> accusations;

    public CrimeSolver() {
        accusations = new HashMap<>();
    }

    // Aggiungi un sospetto
    public void addSuspect(String name) {
        accusations.put(name, new ArrayList<>());
    }

    // Aggiungi un'accusa
    public void addAccusation(String accuser, String accused) {
        if(! accusations.containsKey(accuser)) addSuspect(accuser);
        if(! accusations.containsKey(accused)) addSuspect(accused);
        // Aggiungi l'accusato alla lista delle accuse del sospettato
        accusations.get(accuser).add(accused);
    }
    
    @Override
    public String toString(){
        String s = "";
        for (String suspect : accusations.keySet()) {
            s += suspect + " -> " + accusations.get(suspect).toString() + "\n";
        }
        return s;
    }

    // Trova i possibili criminali in base alle accuse e al numero di persone che dicono la verità
    public List<String> findPossibleCriminals(int truthPerson) {
        List<String> possibleCriminals = new ArrayList<>(); // Lista dei possibili criminali

        for (String suspect : accusations.keySet()) {
            int truthCount = 0; // numero di sospetti che accusano il sospetto attuale

            // Controlla le accuse degli altri sospetti
            for (String other : accusations.keySet()) {
                if (!other.equals(suspect)) { // Ignora il sospetto attuale
                    if (accusations.get(other).contains(suspect)) {
                        // Se altri sospetti accusano il sospetto attuale, incrementa il conteggio
                        truthCount++;
                    }
                }
            }

            // Verifica se il conteggio delle accuse corrisponde al numero di persone che dicono la verità
            if (truthCount == truthPerson) {
                possibleCriminals.add(suspect); // Aggiungi il sospetto alla lista dei possibili criminali
            }
        }

        return possibleCriminals;
    }

}
