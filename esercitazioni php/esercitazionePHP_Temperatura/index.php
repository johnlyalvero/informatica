<!DOCTYPE html>
<html>
<head>
    <title>Inserimento Temperature</title>
</head>
<body>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $citta = ["Roma", "Milano", "Napoli", "Torino", "Palermo", "Genova", "Bologna", "Firenze", "Bari", "Catania"];
    $temperature = [];
    $nonInserite = [];
    $sopra40 = [];
    $sottoMeno10 = [];
    $sottoZero = [];
    
    foreach ($citta as $nomeCitta) {
        $key = str_replace(' ', '_', strtolower($nomeCitta));
        if (isset($_POST[$key]) && $_POST[$key] !== "") {
            if (!is_numeric($_POST[$key])) {
                echo "<p style='color:red;'>Errore: Il valore inserito per $nomeCitta non è un numero valido.</p>";
                continue;
            }
            $temp = floatval($_POST[$key]);
            $temperature[$nomeCitta] = $temp;
            if ($temp >= 40) {
                $sopra40[] = $nomeCitta;
            }
            if ($temp <= -10) {
                $sottoMeno10[] = $nomeCitta;
            }
            if ($temp < 0) {
                $sottoZero[] = $nomeCitta;
            }
        } else {
            $nonInserite[] = $nomeCitta;
        }
    }
    
    if (!empty($temperature)) {
        $media = array_sum($temperature) / count($temperature);
        echo "<p>Temperatura media: " . number_format($media, 2) . "°C</p>";
    }
    if (!empty($nonInserite)) {
        echo "<p>Città senza rilevazione: " . implode(", ", $nonInserite) . "</p>";
    }
    if (!empty($sopra40)) {
        echo "<p>Città con temperatura >= 40°C: " . implode(", ", $sopra40) . "</p>";
    }
    if (!empty($sottoMeno10)) {
        echo "<p>Città con temperatura <= -10°C: " . implode(", ", $sottoMeno10) . "</p>";
    }
    if (isset($_POST['min']) && !empty($temperature)) {
        echo "<p>Temperatura minima: " . min($temperature) . "°C</p>";
    }
    if (isset($_POST['max']) && !empty($temperature)) {
        echo "<p>Temperatura massima: " . max($temperature) . "°C</p>";
    }
    if (isset($_POST['sottozero']) && !empty($sottoZero)) {
        echo "<p>Città con temperatura sotto 0°C: " . implode(", ", $sottoZero) . "</p>";
    }
} else {
?>
    <form method="post" action="">
        <h2>Inserisci le temperature delle seguenti città:</h2>
        <?php
        $citta = ["Roma", "Milano", "Napoli", "Torino", "Palermo", "Genova", "Bologna", "Firenze", "Bari", "Catania"];
        foreach ($citta as $nomeCitta) {
            $key = str_replace(' ', '_', strtolower($nomeCitta));
            echo "$nomeCitta: <input type='text' name='$key'><br><br>";
        }
        ?>
        <input type="checkbox" name="min"> Mostra temperatura minima<br>
        <input type="checkbox" name="max"> Mostra temperatura massima<br>
        <input type="checkbox" name="sottozero"> Mostra città sotto 0°C<br><br>
        <input type="submit" value="Invia">
    </form>
<?php
}
?>
</body>
</html>
