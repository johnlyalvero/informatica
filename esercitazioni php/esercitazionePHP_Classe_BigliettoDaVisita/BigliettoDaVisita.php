<?php

    class BigliettoDaVisita{
        private $nome;
        private $cognome;
        private $classe;

        function __construct($nome, $cognome, $classe){
            $this->nome = $nome;
            $this->cognome = $cognome; 
            $this->classe = $classe;
        }

        public function visualizza(){
            $linea = "";
            $frase1 = "* " . $this->nome . " " . $this->cognome;
            $frase2 = "* Studente in classe " . $this->classe;
            

            if(strlen($frase1) > strlen($frase2)){
                $lunghezza = strlen($frase1) + 2;
                $frase1 = $frase1 . " *";

                for( $i = strlen($frase2); $i < strlen($frase1) - 1 ; $i++ ){
                    $frase2 = $frase2 . " ";
                }

                $frase2 = $frase2 . "*";
            } else {
                $lunghezza = strlen($frase2) + 2;
                $frase2 = $frase2 . " *";
                
                for( $i = strlen($frase1); $i < strlen($frase2) - 1 ; $i++ ){
                    $frase1 = $frase1 . " ";
                }

                $frase1 = $frase1 . "*";
            }

            for($i = 0; $i < $lunghezza; $i++){
                $linea = $linea . "*";
            }

            echo $linea . "\n";
            echo $frase1 . "\n";
            echo $frase2 . "\n";
            echo $linea . "\n";
        }
    }
