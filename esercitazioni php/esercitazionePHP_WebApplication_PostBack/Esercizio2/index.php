<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Modulo di Raccolta Dati</title>
    <style>
    </style>
</head>
<body>
    <h2>Tabelline</h2>
    <form action="script.php"  method="post">
        Numero: <input type="text" name="tabelline_num" required><br>
        <input type="submit" value="Calcola Tabelline">
    </form>
    <?php if (isset($tabelline_result)) {
        echo "<ul>";
        foreach ($tabelline_result as $res) {
            echo "<li>$res</li>";
        }
        echo "</ul>";
    } ?>
</body>
</html>