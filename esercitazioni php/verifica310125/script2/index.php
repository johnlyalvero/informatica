<!DOCTYPE html>
<html>
    <head>
        <title>Script 2</title>
        <style>
            body {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <?php
            $count = 0;
            $result = -1;

            do {
                $count++;
                echo "<h2>TIRO $count</h2>";

                // Scegli un indice casuale tra i 6 prodotti
                $rand1 = rand(1, 6);
                $rand2 = rand(1, 6);
                $rand3 = rand(1, 6);

                $result = $rand1 + $rand2 + $rand3;

                // Ottiene l'immagine
                $imgPath1 = './img/' . $rand1 . '.png';
                $imgPath2 = './img/' . $rand2 . '.png';
                $imgPath3 = './img/' . $rand3 . '.png';


                // Mostra il messaggio pubblicitario
                echo "<img src='$imgPath1' alt='$rand1'><br>";
                echo "<img src='$imgPath2' alt='$rand2'><br>";
                echo "<img src='$imgPath3' alt='$rand3'>";

                echo "<p>La somma tra i valori dei tre dadi usciti è <strong>$result</strong></p><br>";
            }while($result < 13);
        ?>
    </body>
</html>
