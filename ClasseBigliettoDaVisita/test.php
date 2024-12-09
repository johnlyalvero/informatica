<?php

    require_once 'BigliettoDaVisita.php';

    echo "Test Biglietto da Visita\n";
    $Mario = new BigliettoDaVisita("Mario", "Rossi", "5Ai");
    $Mario->visualizza();
    $Lia = new BigliettoDaVisita("Lia", "Idi", "2AFE");
    $Lia->visualizza();
    $Pierferdinando = new BigliettoDaVisita("Pierferdinando", "Alessandrini", "4Ci");
    $Pierferdinando->visualizza();