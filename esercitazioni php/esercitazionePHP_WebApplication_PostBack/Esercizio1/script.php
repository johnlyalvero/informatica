<?php
// Esercizio1 - Somma di due numeri
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $num1 = isset($_POST['num1']) ? (int)$_POST['num1'] : 0;
    $num2 = isset($_POST['num2']) ? (int)$_POST['num2'] : 0;
    $somma = $num1 + $num2;
}
?>