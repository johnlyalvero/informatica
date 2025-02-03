<?php

require_once 'Linea.php';

class Rettangolo {
	private $linea;
	private $a;

	function __cunstruct($l, $a){
		$this->linea = new Linea($l);
		$this->a = $a;
	}

	function show(){
		for($i=0; $i<$this->a; $i++){
			$l = $this->linea;
			$l->show();
		}
	}
}
