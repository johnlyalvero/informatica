<?php
    function visualizzaMenu($menu){
        echo '<h2>MENU Quinstainformatica</h2>';

        echo '<table>';
        echo '<tr>';
        echo "<th>PIZZA</th>";
        echo "<th>PREZZO in euro</th>";
        echo '</tr>';
        foreach($menu as $tipo => $prezzo){
            echo '<tr>';
            echo "<td>$tipo</td>";
            echo "<td>$prezzo</td>";
            echo '</tr>';
        }
        echo '</table>';
    }

    function calcolaPrezzo($menu, $ordine): float{ //con ordine = array() con chiave = tipo pizza e valore = quantita
        $prezzoTotale = 0;

        foreach($ordine as $tipo => $quantita){
            $prezzoTotale += ($menu[$tipo] * $quantita);
        }

        return $prezzoTotale;
    }

    function omaggio($menu, $ordine){
        $omaggio = null;
        $prezzoTotale = calcolaPrezzo($menu, $ordine);
        
        if($prezzoTotale >= 35) $omaggio = "pizzabibita";
        else if($prezzoTotale >= 25) $omaggio = "pizza";
        else if($prezzoTotale >= 15) $omaggio = "bibita";

        if(isset($omaggio)) echo "<img src='./img/$omaggio.jpg' alt='omaggio $omaggio'><br>";
    }

    function visualizzaPrezzo($menu, $ordine){
        echo "<p><strong>Prezzo totale: " . calcolaPrezzo($menu, $ordine) . " euro</p>";
        omaggio($menu, $ordine);
    }

    function visualizzaOrdine($menu, $ordine){
        echo "<h2>Ordine:</h2>";
        
        foreach($ordine as $tipo => $quantita){
            echo "<p> $quantita $tipo</p>";
        }
    }
?>