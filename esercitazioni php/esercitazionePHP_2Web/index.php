<!DOCTYPE html>
<html>
<head>
    <title>Tavola Pitagorica</title>
    <style>
        body {
            text-align: center;
            background-color: #f8f9fa;
            color: #333;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 90%;
            max-width: 600px;
            border: 2px solid red;
        }
        th, td {
            border: 1px solid red;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: white;
            color: black;/
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:nth-child(odd) {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
    <h1>Tavola Pitagorica Dinamica</h1>
    <?php
    // Genera un valore casuale per n tra 10 e 20
    $n = rand(10, 20);

    echo "<h2>Dimensione della Tavola: $n x $n</h2>";

    // Genera la tavola pitagorica
    echo "<table>";
    for ($row = 1; $row <= $n; $row++) {
        echo "<tr>";
        for ($col = 1; $col <= $n; $col++) {
            $product = $row * $col;
            echo "<td><strong>$product</strong></td>"; // Aggiunto <strong> per il grassetto
        }
        echo "</tr>";
    }
    echo "</table>";
?>
</body>
</html>
