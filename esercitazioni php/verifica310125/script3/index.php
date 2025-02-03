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
            background-color: yellow;
            color: black;
        }
    </style>
</head>
<body>

        <?php
            require_once 'script/Pizzeria.php';
            
        //PUNTO 1
            $menu = array(
                "ASPARAGI" => 6.5,
                "BOLOGNESE" => 8,
                "BRESAOLA" => 7.5,
                "DIAVOLA" => 6.5,
                "CALZONE NAPOLETANO" => 6.5,
                "CAPRICCIOSA " => 7,
                "QUATTRO FORMAGGI " => 6.5,
                "AI PEPERONI" => 6.5,
                "MARINARA" => 4,
                "AL TONNO" => 6.5,
                "AI PORCINI" => 7.5,
                "AL PROSCIUTTO" => 6.5,
                "PROSCIUTTO E FUNGHI" => 7,
                "PUGLIESE" => 6,
                "TIROLESE" => 7,
                "MARGHERITA" => 4.5,
            );

            $ordine = array(
                "MARGHERITA" => 1,
                "MARINARA" => 1,
                "PUGLIESE" => 2,
                "DIAVOLA" => 3,
            );

            $Quintainformatica = new Pizzeria($menu);
            
            //$Quintainformatica->visualizzaMenu();

        //PUNTO 2
            $Quintainformatica->aggiornaInflazione(0.5);
            $Quintainformatica->visualizzaMenu();
        
        //PUNTO 3
            //$Quintainformatica->visualizzaOrdineCrescentePrezzo();
            //$Quintainformatica->visualizzaOrdineAlfabetico();
        
        //PUNTO 4 | PUNTO 5
            echo "<h2>Ordine:</h2>";
            foreach($ordine as $tipo => $quantita){
                echo "<p> $quantita $tipo</p>";
            }

            $Quintainformatica->visualizzaPrezzo($ordine);

        ?>

</body>
</html>