<!DOCTYPE html>
<html>
    <head>
        <title>Script 1</title>
        <style>
            body {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <?php
            require_once 'Tabellina.php';

            $tabellina = new Tabellina(7);
            $tabellina->visualizza();

            /*
            $tabellina1 = new Tabellina(7);
            $tabellina1->cambiaMoltiplicatore(20);
            $tabellina1->visualizza();
            

            $tabellina1 = new Tabellina(7);
            $tabellina1->randomizzaMoltiplicatore(100);
            $tabellina1->visualizza();
            */
        ?>
    </body>
</html>

