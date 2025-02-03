<?php

    //non è key-sensitive
    class Anagrafica{
        private static $nome;
        private static $cognome;
        private static $email;
        private static $registrata;
        
        //default: public
        function __construct(string $nome, string $cognome){
            $this->nome = $nome;
            $this->cognome = $cognome;
            $this->registrata = false;
        }

        public function registraMail(string $email){
            $this->email = $email;
            $registrata = true;
        }

        public function visualizzaDati():string {
            if($registrata)
                return 'nome:' . $nome . 'cognome:' . $cognome . 'email:' . $email; 
            else
                return 'utente non registrato';
        }
    }

