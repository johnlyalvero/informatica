<?php
/*for($i = $argv[1]; $i <= $argv[2]; $i++){
    if($i%2 == 0)
        echo $i . " ";
}*/
for($i = $argv[1]; $i <= $argv[2]; $i+=2){
    if($i%2 != 0)
        $i++;
    echo $i . " ";
}
?>