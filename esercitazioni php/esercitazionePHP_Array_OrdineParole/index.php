<!DOCTYPE html>
<html>
<head>
    <title>Esercizio 1</title>
</head>
<body>


    <?php
        $parole = array(
            "pedonale" => 8,
            "schiacciare" => 11,
            "insetto" => 7,
            "marinaio" => 8,
            "consonanti" => 10,
            "documento" => 9,
            "viale" => 5,
            "prescrizione" => 12,
            "godore" => 6,
            "ciclope" => 7
        );
        
        asort($parole);

        echo "<ul>";
        foreach($parole as $chiave => $valore){
             echo "<li> $chiave";
        }
        echo "</ul>";
    ?>


</body>
</html>

