<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Modulo di Raccolta Dati</title>
    <style>
    
    </style>
</head>
<body>
    <h2>Operazioni matematiche</h2>
    <form method="post">
        Operando 1: <input type="text" name="op1" required><br>
        Operando 2: <input type="text" name="op2" required><br>
        Operazione:
        <select name="operazione">
            <option value="somma">Somma</option>
            <option value="sottrazione">Sottrazione</option>
            <option value="moltiplicazione">Moltiplicazione</option>
            <option value="divisione">Divisione</option>
        </select><br>
        <input type="submit" value="Calcola">
    </form>
    <?php if (isset($risultato)) { echo "<p>Risultato: $risultato</p>"; } ?>
</body>
</html>