<!DOCTYPE html>
<html>
    <head>
        <title>Script 1</title>
    </head>
    <body>
        <?php
            require_once 'Giocatore.php';

            $giocatore = new Giocatore('Alvero', 'Jay');
            $giocatore->estraiNumero();
            $giocatore->visualizza();

            $giocatore = new Giocatore('Alfalso', 'Yaj');
            $giocatore->visualizza();
        ?>
    </body>
</html>

