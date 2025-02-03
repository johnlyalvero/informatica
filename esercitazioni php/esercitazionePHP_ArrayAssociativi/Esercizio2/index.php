<!DOCTYPE html>
<html>
<head>
    <title>Esercizio 2</title>
</head>
<body>


    <?php
        $materie = array('Italiano','Storia','Inglese','Matematica','GPOI','Informatica','Sistemi e Reti','TSPI','EducazioneFisica');
        shuffle($materie);

        echo "<ul>";
        foreach($materie as $materia){
            echo "<li> $materia </li>";
        }
        echo "</ul>";
    ?>


</body>
</html>

