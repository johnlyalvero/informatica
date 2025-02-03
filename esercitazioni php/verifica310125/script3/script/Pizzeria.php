<?php
    class Pizzeria{

        private $menu;

        function __construct($menu){
            $this->menu = $menu;
        }

        function visualizzaMenu(){
            echo '<h2>MENU Quinstainformatica</h2>';
    
            echo '<table>';
            echo '<tr>';
            echo "<th>PIZZA</th>";
            echo "<th>PREZZO in euro</th>";
            echo '</tr>';
            foreach($this->menu as $tipo => $prezzo){
                echo '<tr>';
                echo "<td>$tipo</td>";
                echo "<td>$prezzo</td>";
                echo '</tr>';
            }
            echo '</table>';
        }

        function aggiornaInflazione($aumento){
            foreach($this->menu as $tipo => $prezzo){
                $this->menu[$tipo] = $this->menu[$tipo] + $aumento;
            }

            echo "<p>ATTENZIONE: Prezzo aumentato!</p>";
        }

        function visualizzaOrdineCrescentePrezzo() {
            asort($this->menu);
            $this->visualizzaMenu();
            echo '<p>In ordine crescente di prezzo</p>';
        }

        function visualizzaOrdineAlfabetico() {
            ksort($this->menu);
            $this->visualizzaMenu();
            echo '<p>In ordine alfabetico</p>';
        }

        private function calcolaPrezzo($ordine): float{ //con ordine = array() con chiave = tipo pizza e valore = quantita
            $prezzoTotale = 0;

            foreach($ordine as $tipo => $quantita){
                $prezzoTotale += ($this->menu[$tipo] * $quantita);
            }

            return $prezzoTotale;
        }

        private function omaggio($ordine){
            $omaggio = null;
            $prezzoTotale = $this->calcolaPrezzo($ordine);
            
            if($prezzoTotale >= 45) $omaggio = "pizzabibita";
            else if($prezzoTotale >= 25) $omaggio = "pizza";
            else if($prezzoTotale >= 14) $omaggio = "bibita";

            if(isset($omaggio)) echo "<img src='./img/$omaggio.jpg' alt='omaggio $omaggio'><br>";
        }

        function visualizzaPrezzo($ordine){
            echo "<p><strong>Prezzo totale: " . $this->calcolaPrezzo($ordine) . " euro</p>";
            $this->omaggio($ordine);
        }
    }
    ?>