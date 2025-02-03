<?php
    echo"Qual'è il range?\n";
    $min = readline("Minimo: ");
    $a = $min;
    $max = readline("Massimo: ");
    $b = $max;
    while(true){
        if($a > $b) {
            echo "Siamo fuori strada";
            break;
        }
        $guess = (int)(($a+$b)/2);
        echo"Per caso è... " . $guess . "\n";
        $risposta = readline("1 (troppo alto) o 0 (troppo basso) o 10 (indovinato)\n");
        if($risposta == 1){
            $b = $guess;
        } else if($risposta == 0){
            $a = $guess;
        } else if($risposta == 10){
            echo"Sono proprio un indovino";
            break;
        } 
    }
?>