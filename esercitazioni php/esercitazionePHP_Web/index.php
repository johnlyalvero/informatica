<!DOCTYPE html>
<html>
<head>
    <title>Messaggio Pubblicitario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .advertisement {
            border: 2px solid #ddd;
            border-radius: 10px;
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            width: 60%;
        }

        .advertisement img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }

        .advertisement a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            font-size: 1.2em;
        }

        .advertisement a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>

    <?php
    // Carica il file JSON
    $path = "sites.json";
    $jsonString = file_get_contents($path);
    $jsonData = json_decode($jsonString, true);

    // Scegli un indice casuale tra i 6 prodotti
    $randomIndex = rand(0, 5);

    // Estrai i dati del prodotto scelto
    $product = $jsonData[$randomIndex];

    // Ottieni il titolo, l'immagine e il link
    $title = $product["title"];
    $imgPath = $product["img_source"];
    $link = $product["link"];

    // Mostra il messaggio pubblicitario
    echo "<div class='advertisement'>";
    echo "<h2>$title</h2>";
    echo "<img src='$imgPath' alt='$title'><br>";
    echo "<a href='$link' target='_blank'>Visita il sito</a>";
    echo "</div>";
    ?>

</body>
</html>