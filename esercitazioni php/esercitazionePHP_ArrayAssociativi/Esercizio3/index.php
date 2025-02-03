<!DOCTYPE html>
<html>
<head>
    <title>Esercizio 3</title>
</head>
<body>


    <?php
        $giocatori = array(
            "Point Guard" => "dRyota Miyagi",
            "Shooting Guard" => "Hisashi Mitsui",
            "Small Forward" => "Kaebe Rukawa",
            "Poiwer Forward" => "Hanamichi Sakuragi",
            "Center" => "Takenori Akagi",
        );

        asort($giocatori);

        foreach($giocatori as $position => $giocatore){
            echo "<p> $position: $giocatore";
        }
    ?>


</body>
</html>

