<?php
    require_once "Dado.php";
    $numDadi = $_POST["numDadi"];
    $minScore = $_POST["minScore"];

    //CONTROLLO INPUT
    if($minScore > $numDadi){

        $count = 0;
        $dadi;

        //CREO I DADI
        for($i = 0; $i<$numDadi;$i++)
            $dadi[] = new Dado();

        //MAIN LOOP
        do{
            $result = 0;
            $count++;

            echo "<h1>Lancio $count</h1><br>";
            foreach($dadi as $dado){
                $result += $dado->throw();
                $dado->print();
            }
            echo "<p>Somma dei dadi = $result</p>";

        }while($result < $minScore);

    } else {
        echo "<p>ATTENZIONE INPUT: la somma dei dadi raggiungerà sicuramente $minScore</p>";
    }

    ?>
    