<?php
    echo"indovina il numero da " . $argv[1] . " a " . $argv[2] . "\n";
    $toguess = rand($argv[1], $argv[2]);
    while(true) {
        $guess = readline("inserisci: ");
        if($guess == $toguess) {
            echo "Indovinato";
            break;
        } else if($guess > $toguess) {
            echo "troppo alto\n";
        } else {
            echo "troppo basso\n";
        }
    }
?>