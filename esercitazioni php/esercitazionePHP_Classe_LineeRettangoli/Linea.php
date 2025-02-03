<?php

class Linea {
	private $l;

	function __construct($l){
		$this->l = $l;
	}

	function show(){
		for($i=0; $i<$this->l; $i++){
			echo "*";
		}
		echo "\n";
	}
}
