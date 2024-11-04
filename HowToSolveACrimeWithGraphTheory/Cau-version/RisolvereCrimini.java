
public class RisolvereCrimini {

    public static void main(String[] args) {
        CrimeSolver crime = new CrimeSolver();
        
        /*
        Esempio 1:
        crime.addAccusation("A", "B");
        crime.addAccusation("A", "C");
        crime.addAccusation("B", "A");
        crime.addAccusation("C", "A");
        crime.addAccusation("C", "B");
        */
        
        /*
        Esempio 2:
        crime.addAccusation("A", "B");
        crime.addAccusation("A", "C");
        crime.addAccusation("A", "D");
        crime.addAccusation("B", "A");
        crime.addAccusation("C", "A");
        crime.addAccusation("C", "B");
        crime.addAccusation("C", "D");
        crime.addAccusation("D", "B");
        */

        System.out.println("Era una grigia e monotona giornata di novembre, quando all'improvviso un urlo stridente squarciò il silenzio della classe, facendo gelare il sangue a tutti gli studenti.");
        System.out.println("Era Andrea, il quale stava guardando dentro lo zaino con aria preoccupata...");
        System.out.println("Andrea: \"LA PENNA DEL MIO TABLET E' SCOMPARSA!\"");
        System.out.println("Prof. Blagho: \"Ragazzi rimaniamo qui in classe finchè non esce il colpevole/i!\"");
        System.out.println("Kevin: menomale mi stavo per addormentare");
        System.out.println("Passarono 5,10,20 minuti, ma ancora nessuno s'era fatto avanti.");
        System.out.println("Prof. Blagho: \"Va bene ragazzi l'avete voluta voi, adesso interrogo ognuno riguardo al caso e chi è il colpevole/i di questo misfatto subirà la più grande punizione della storia del Belluzzi!\"");
        System.out.println("Inizio dell'interrogatorio...");

        System.out.println("Matteo: \"Non ho rubato la penna! Linda è stata l'ultima a vederla.\"");
        System.out.println("Linda: \"Io avevo visto Yassin vicino al banco di Andrea con fare sospetto...\""); 
        System.out.println("Yassin: \"Non so nulla della penna, ma ho visto Gianluca con un oggetto strano, simile ad una penna!\""); 
        System.out.println("Gianluca: \"Alessandro è quello che ha sempre un sacco di roba in giro, forse è lui!\""); 
        System.out.println("Alessandro: \"Io non ho nulla a che fare con questo, Luca era vicino ad Andrea!\""); 
        System.out.println("Luca: \"Daniele è l'unico che non ha mai prestato attenzione, potrebbe averla presa senza pensare!\""); 
        System.out.println("Daniele: \"Serena ha passato del tempo con Andrea, chiedetele a lei!\""); 
        System.out.println("Serena: \"Io? Non l'ho mai vista quella penna, è Nassim che mi sembra sospetto!\""); 
        System.out.println("Nassim: \"Kevin ha sempre un atteggiamento strano, non fidatevi di lui!\""); 
        System.out.println("Kevin: \"Stavo dormendo durante la lezione, ma secondo me è Matteo. Lui è quello che inizia sempre le accuse, potrebbe averla presa lui!\""); 
        System.out.println("Le accuse continuarono finchè si scoprì chi era davvero stato...");
        
        crime.addAccusation("Matteo", "Linda");
        crime.addAccusation("Linda", "Yassin");
        crime.addAccusation("Yassin", "Gianluca");
        crime.addAccusation("Gianluca", "Alessandro");
        crime.addAccusation("Alessandro", "Luca");
        crime.addAccusation("Luca", "Daniele");
        crime.addAccusation("Daniele", "Serena");
        crime.addAccusation("Serena", "Nassim");
        crime.addAccusation("Nassim", "Kevin");
        crime.addAccusation("Kevin", "Matteo");
        crime.addAccusation("Matteo", "Gianluca");
        crime.addAccusation("Yassin", "Alessandro");
        crime.addAccusation("Yassin", "Matteo");
        crime.addAccusation("Linda", "Kevin");
        crime.addAccusation("Daniele", "Luca");
        crime.addAccusation("Serena", "Yassin");
        crime.addAccusation("Alessandro", "Nassim");
        crime.addAccusation("Gianluca", "Daniele");
        crime.addAccusation("Nassim", "Linda");
        crime.addAccusation("Daniele", "Matteo");
        crime.addAccusation("Matteo", "Daniele");
        
        System.out.println(crime);
        if (crime.findPossibleCriminals(3).size() > 1) {
            System.out.println("Il prof grazie all'interogatorio capì chi era davvero stato e " + crime.findPossibleCriminals(3) + " subirono 6 ore ininterrotte di lezione in inglese di PHP");
        }
        if (crime.findPossibleCriminals(3).size() == 1) {
            System.out.println("Il prof grazie all'interogatorio capì chi era davvero stato e " + crime.findPossibleCriminals(3) + " subì 6 ore ininterrotte di lezione in inglese di PHP");
        }
        
        
    }
}
