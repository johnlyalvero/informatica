<?php
   class Dado{
        private $rand;
        private $imgPath;

        function throw(): int{
            // Scegli un indice casuale tra i 6 prodotti
            $this->rand = rand(1, 6);
            $this->imgPath = '../img/' . $this->rand . '.png';
            return $this->rand;
        }

        function print(){
            if(isset($this->rand)) echo "<img src='" . $this->imgPath . "' alt=' " . $this->rand . " '><br>";
            else echo "<p>ATTENZIONE: il dado non e' ancora stato tirato</p><br>";
        }
    }