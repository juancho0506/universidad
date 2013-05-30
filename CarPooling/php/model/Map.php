<?php
class Map {

	private $id = 0;

	private $name = "";

	private $description = "";

	private $graph = null;

	public static function __empty() {
		$instance = new self();
		return $instance;
	}

	public function __construct($id, $name, $description) {
		$this->id = $id;
		$this->name = $name;
		$this->description = $description;
	}

	public function getId() {
		return $this->id;
	}

	public function setId($id) {
		$this->id = $id;
	}

	public function getName() {
		return $this->name;
	}

	public function setName($name) {
		$this->name = $name;
	}

	public function getDescription() {
		return $this->description;
	}

	public function setDescription($description) {
		$this->description = $description;
	}

	public function getGraph() {
		return $this->graph;
	}

	public function setGraph($graph) {
		$this->graph = $graph;
	}

}
