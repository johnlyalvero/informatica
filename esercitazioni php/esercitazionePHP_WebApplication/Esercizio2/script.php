<?php
    $num = $_POST["tabellina"];
    
    for($i = 1; $i<=10; $i++){
        echo "<p> $num x $i = " . $num * $i . "</p>";
    }