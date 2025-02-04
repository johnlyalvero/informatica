<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Lancio Dadi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"], select, textarea {
            width: 100%;
            padding: 8px;
            margin: 10px 0 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="radio"], input[type="checkbox"] {
            margin-right: 10px;
        }

        .form-group {
            margin-bottom: 10px;
            text-align: center;
        }

        .form-group-inline {
            display: flex;
            justify-content: space-between;
        }

        .form-group-inline label {
            margin-right: 10px;
        }

        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button[type="reset"] {
            background-color: #f44336;
        }

        button:hover {
            opacity: 0.9;
        }
    </style>
<body>
    <div class="container">
        <h1>Modulo di Raccolta Dati</h1>
        <form action="script/script.php" method="post">

            <div class="form-group">
                <label for="numDadi">Numero di Dadi</label>
                <input type="number" id="numDadi" name="numDadi" min=1 required>
                
                <label for="minScore">Punteggio minimo</label>
                <input type="number" id="minScore" name="minScore" required>
            </div>

            <!-- Bottone per invio e reset -->
            <div class="form-group form-group-inline">
                <button type="submit">Invia</button>
                <button type="reset">Reset</button>
            </div>
        </form>
    </div>
</body>
</html>