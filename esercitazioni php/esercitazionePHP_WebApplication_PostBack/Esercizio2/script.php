<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['tabelline_num'])) {
        $tabelline_num = (int)$_POST['tabelline_num'];
        $tabelline_result = [];
        for ($i = 1; $i <= 10; $i++) {
            $tabelline_result[] = "$tabelline_num x $i = " . ($tabelline_num * $i);
        }
    }
    