<!DOCTYPE html>
<html>
<head>
    <title>Script 3</title>
    <style>
        body {
            text-align: center;
            background-color: #f8f9fa;
            color: black;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 90%;
            max-width: 600px;
            border: 2px solid grey;
        }
        th, td {
            border: 1px solid grey;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: lightblue;
            color: black;
        }
    </style>
</head>
<body>
    <?php
        $regioni = array(
            "Basilicata" => 2,
            "Emilia-Romagna" => 9,
            "Friuli Venezia Giulia" => 4,
            "Lazio" => 5,
            "Campania" => 5,
            "Lombardia " => 12,
            "Marche " => 5,
            "Molise " => 2,
            "Piemonte " => 8,
            "Puglia " => 6,
            "Sicilia " => 9,
            "Calabria " => 5,
            "Trentino-Alto Adige " => 2,
            "Umbria " => 2,
            "Valle d'Aosta " => 1,
            "Veneto " => 7,
            "Toscana " => 10,
            "Liguria " => 4,
            "Abruzzo " => 4,
            "Sardegna " => 5,
        );

        
        echo '<h2>Punto 1</h2>';
        echo '<p>così come proposto</p>';

        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo "<th>Numero Provicie</th>";
        echo '</tr>';
        foreach($regioni as $regione => $numeroProvicie){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo "<td>$numeroProvicie</td>";
            echo '</tr>';
        }
        echo '</table>';

        echo '<h2>Punto 2</h2>';
        echo '<p>ordinato alfabeticamente</p>';

        ksort($regioni);

        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo "<th>Numero Provicie</th>";
        echo '</tr>';
        foreach($regioni as $regione => $numeroProvicie){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo "<td>$numeroProvicie</td>";
            echo '</tr>';
        }
        echo '</table>';

        echo '<h2>Punto 3</h2>';
        echo '<p>ordinato per numero decrescente di province</p>';

        arsort($regioni);

        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo "<th>Numero Provicie</th>";
        echo '</tr>';
        foreach($regioni as $regione => $numeroProvicie){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo "<td>$numeroProvicie</td>";
            echo '</tr>';
        }
        echo '</table>';

        echo '<h2>Punto 4</h2>';
        echo '<p>due array che contengono rispettivamente, le regioni con almeno 6 province e le restanti</p>';

        $array1 = array();
        $array2 = array();
        foreach($regioni as $regione => $numeroProvicie){
            if($numeroProvicie >= 6)
                 array_push($array1, $regione);
            else
                array_push($array2, $regione);
        }

        echo '<h3>Con almeno 6 provincie</h3>';
        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo '</tr>';
        foreach($array1 as $regione){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo '</tr>';
        }
        echo '</table>';
        echo '<h3>Le restanti</h3>';
        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo '</tr>';
        foreach($array2 as $regione){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo '</tr>';
        }
        echo '</table>';

        echo '<h2>Punto 5</h2>';
        echo '<p>ordinati in ordine crescente di provinc</p>';

        asort($array1);
        asort($array2);

        echo '<h3>Con almeno 6 provincie</h3>';
        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo '</tr>';
        foreach($array1 as $regione){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo '</tr>';
        }
        echo '</table>';
        echo '<h3>Le restanti</h3>';
        echo '<table>';
        echo '<tr>';
        echo "<th>Regione</th>";
        echo '</tr>';
        foreach($array2 as $regione){
            echo '<tr>';
            echo "<td>$regione</td>";
            echo '</tr>';
        }
        echo '</table>';
    ?>
</body>
</html>