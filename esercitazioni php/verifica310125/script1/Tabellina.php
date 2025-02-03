<?php
    class Tabellina{

    private $numero;
    private $moltiplicatore;

    function __construct($numero){
        $this->numero = $numero;
        $this->moltiplicatore = 10;
    }

    public function cambiaMoltiplicatore($moltiplicatore){
        $this->moltiplicatore = $moltiplicatore;
    }

    public function  randomizzaMoltiplicatore($max){
        $this->moltiplicatore = rand(1, $max);
    }

    public function visualizza(){
        echo "<p><strong>Tabellina del $this->numero con valore del moltipliciatore $this->moltiplicatore</strong></p>";
        for($i = 1; $i <= $this->moltiplicatore; $i++ ){
            echo "<p><strong>$this->numero X $i = " . $this->numero * $i . '</strong></p>';
        }
    }

    }