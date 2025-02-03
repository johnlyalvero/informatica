<!DOCTYPE html>
<html>
    <head>
        <title>Script 2</title>
    </head>
    <body>
        <?php
            // Scegli un indice casuale tra i 6 prodotti
            $rand1 = rand(1, 6);
            $rand2 = rand(1, 6);

            $result = $rand1 + $rand2;

            // Ottiene l'immagine
            $imgPath1 = './img/' . $rand1 . '.png';
            $imgPath2 = './img/' . $rand2 . '.png';

            // Mostra il messaggio pubblicitario
            echo "<img src='$imgPath1' alt='$rand1'><br>";
            echo "<img src='$imgPath2' alt='$rand2'>";

            echo "<p>La somma tra i valori dei due dadi usciti è <strong>$result</strong></p>";
        ?>
    </body>
</html>

