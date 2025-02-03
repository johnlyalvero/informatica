<?php

require_once 'Rettangolo.php';
require_once 'Linea.php';

$l = new Linea(5);
$l->show();

$l = new Linea(2);
$l->show();

$l = new Linea(8);
$l->show();



$r = new Rettangolo(2,3);
$r->show();

$r = new Rettangolo(13,2);
$r->show();

$r = new Rettangolo(3,3);
$r->show();
