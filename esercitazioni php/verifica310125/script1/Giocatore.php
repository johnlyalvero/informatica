<?php
    class Giocatore{

    private $cognome;
    private $nome;
    private $numeroEstratto;

    function __construct($cognome, $nome){
        $this->cognome = $cognome;
        $this->nome = $nome;
    }

    public function estraiNumero(){
        $this->numeroEstratto = rand(1,1000);
    }

    public function  numeroEstrazione():int {
        if(isset($this->numeroEstratto))    //solo nel caso il numero sia stato estratto
            return $this->numeroEstratto;
        else
            return null;
    }

    public function visualizza(){
        if(isset($this->numeroEstratto)){
            echo '<p><i>' . $this->nome . ' ' . $this->cognome . '</i></p>' . "\n";
            echo '<p>' . 'ha estratto il numero ' . $this->numeroEstrazione() . '</p>' . "\n";
        } else {   
            echo '<p><i>' . $this->nome . ' ' . $this->cognome . '</i></p>' . "\n";
            echo '<p>' . 'Non ha estratto alcun numero' . '</p>' . "\n";
        }
    }

    }