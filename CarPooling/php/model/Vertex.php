<?php
class Vertex {

	private $id = 0;

	private $name = "";

	private $address = "";

	private $distance = 0;

	private $visited = false;

	/**	La coleccion de aristas del vertice **/
	private $edges = array();

	/** Tipo de Vertice: INICIAL, DESTINO, PASAJERO. **/
	private $type = 0;

	/**
	 * 
	 */
	public static function __empty() {
		$instance = new self();
		return $instance;
	}

	/**
	 * 
	 * @param int $id
	 * @param String $name
	 * @param String $address
	 * @param int $distance
	 */
	public function __construct($id, $name, $address) {

		$this->id = $id;
		$this->name = $name;
		$this->address = $address;
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

	public function getAddress() {
		return $this->address;
	}

	public function setAddress($address) {
		$this->address = $address;
	}

	public function getDistance() {
		return $this->distance;
	}

	public function setDistance($distance) {
		$this->distance = $distance;
	}

	/**
	 * @return array of Edges
	 */
	public function getEdges() {
		return $this->edges;
	}

	/**
	 * @param Array $edges
	 */
	public function setEdges($edges) {
		$this->edges = $edges;
	}

	public function addEdge($edge) {
		array_push($this->edges, $edge);
	}

	public function getType() {
		return $this->type;
	}

	public function setType($type) {
		$this->type = $type;
	}

	/**
	 * @return the unknown_type
	 */
	public function getVisited() {
		return $this->visited;
	}

	/**
	 * @param unknown_type $visited
	 */
	public function setVisited($visited) {
		$this->visited = $visited;
	}

}
