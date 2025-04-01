<?php
    // Recupero dati dal form con fallback
    $nome = $_POST['nome'] ?? '';
    $cognome = $_POST['cognome'] ?? '';
    $professione = $_POST['professione'] ?? '';
    $sesso = $_POST['sesso'] ?? 'Non specificato';
    $suggerimenti = $_POST['suggerimenti'] ?? '';

    // Ottengo data e ora
    $data_ora = date("Y-m-d H:i:s");

    // Ottengo IP utente
    $ip = $_SERVER['REMOTE_ADDR'];

    // Costruisco il blocco di testo da scrivere
    $testo = "---------- Nuovo Commento ----------\n";
    $testo .= "Data: $data_ora\n";
    $testo .= "IP: $ip\n";
    $testo .= "Nome: $nome\n";
    $testo .= "Cognome: $cognome\n";
    $testo .= "Professione: $professione\n";
    $testo .= "Sesso: $sesso\n";
    $testo .= "Suggerimenti:\n$suggerimenti\n";
    $testo .= "------------------------------------\n\n";

    // Definizione del file di destinazione
    $filename = "../txt/commenti.txt";

    // Verifico se il file esiste e può essere scritto
    if (file_exists($filename) && is_writable($filename) || !file_exists($filename)) {

        // Apro il file in modalità append (scrive in fondo)
        if (!$handle = fopen($filename, 'a')) {
            echo "Errore: impossibile aprire il file ($filename)";
            exit;
        }

        // Scrivo nel file
        if (fwrite($handle, $testo) === FALSE) {
            echo "Errore: impossibile scrivere nel file ($filename)";
            exit;
        }

        // Chiudo il file
        fclose($handle);

        echo "<h3>Grazie per il tuo commento, $nome!</h3>";

    } else {
        echo "Il file $filename non è scrivibile.";
    }
?>
