<?php
    $result = match($_POST["operazione"]) {
        "+" => $_POST["num1"] + $_POST["num2"],
        "-" => $_POST["num1"] - $_POST["num2"],
        "*" => $_POST["num1"] * $_POST["num2"],
        "/" => $_POST["num1"] / $_POST["num2"],
    };

    echo $_POST["num1"] . ' ' . $_POST["operazione"] . ' ' . $_POST["num2"] . ' = ' . $result;