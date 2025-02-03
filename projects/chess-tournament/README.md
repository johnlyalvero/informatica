# Gestione Torneo di Scacchi

## 📌 Obiettivo del Programma
Questo programma consente di gestire un torneo di scacchi, permettendo di:
- **Leggere un calendario di partite** da un file CSV (`calendario.csv`).
- **Selezionare da quale turno iniziare** e fino a quale turno registrare i risultati.
- **Registrare i risultati** delle partite con il formato ufficiale degli scacchi.
- **Salvare i risultati** in un file CSV (`risultati.csv`) per futura consultazione.

## 🛠️ Funzionamento

1. **Preparazione del Calendario**  
   - Il file `calendario.csv` deve contenere le partite, con ogni turno su una nuova riga e le partite separate da una virgola.  
   - Esempio di `calendario.csv`:
     ```
     Giocatore1 vs Giocatore2,Giocatore3 vs Giocatore4
     Giocatore5 vs Giocatore6,Giocatore7 vs Giocatore8
     ```

2. **Esecuzione del Programma**
   - Quando il programma viene eseguito, legge il file `calendario.csv`.
   - Chiede all'utente da quale turno iniziare e fino a quale turno registrare i risultati.
   - Per ogni partita del turno selezionato, chiede il risultato (accettando solo i formati validi).

3. **Formato dei Risultati**
   - `1-0` → Vittoria del Bianco  
   - `0-1` → Vittoria del Nero  
   - `½-½` → Patta  
   - `-` → Partita non giocata  

4. **Salvataggio dei Risultati**
   - I risultati vengono salvati in `risultati.csv` nel seguente formato:
     ```
     Turno 1, Giocatore1 vs Giocatore2: 1-0
     Turno 1, Giocatore3 vs Giocatore4: ½-½
     ```

## ⚠️ Controlli di Sicurezza
- Il programma impedisce all'utente di inserire caratteri non validi per i turni e i risultati.
- Se il file `calendario.csv` è vuoto o inesistente, mostra un messaggio di errore.

## 🏆 Autore
Questo software è stato sviluppato per gestire un torneo di scacchi in modo efficiente e preciso.
