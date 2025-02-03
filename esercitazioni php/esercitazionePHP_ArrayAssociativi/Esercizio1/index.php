<!DOCTYPE html>
<html>
<head>
    <title>Esercizio 1</title>
</head>
<body>


    <?php
        $schede = array(
        'scheda1'=>array('nome'=>'Seijuro',
        'cognome'=>'Akashi',
        'anno di nascita'=>2007,
        'ruolo'=>'Playmaker',
        'club'=>'Rakuzan High',
        'nazionalità'=>'Giapponese' ),
   
        'scheda2'=>array('nome'=>'Daiki',
        'cognome'=>'Aomine',
        'anno di nascita'=>2007,
        'ruolo'=>'Power Forward',
        'club'=>'Tōō Academy',
        'nazionalità'=>'Giapponese' ),
   
        'scheda3'=>array('nome'=>'Ryōta',
        'cognome'=>'Kise',
        'anno di nascita'=>2007,
        'ruolo'=>'Small Forward',
        'club'=>'Kaijō',
        'nazionalità'=>'Giapponese' ),
   
        'scheda4'=>array('nome'=>'Shintarō',
        'cognome'=>'Midorima',
        'anno di nascita'=>2007,
        'ruolo'=>'Shooting Guard',
        'club'=>'Shūtoku',
        'nazionalità'=>'Giapponese' ));
   
        foreach($schede as $scheda => $lista){
            echo "\n<h2>$scheda</h2>";
            foreach($schede[$scheda] as $chiave => $valore){
                echo "<p> $chiave: $valore";
            }
            echo "<p>--------------------------";
        }
    ?>


</body>
</html>

