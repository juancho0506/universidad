<?php
class Graph {

	private $id = 0;

	private $name = "";

	/**	La coleccion de aristas del grafo **/
	private $edges = array();

	/**	La coleccion de aristas del grafo **/
	private $vertexs = array();

	public static function __empty() {
		$instance = new self();
		return $instance;
	}

	/**
	 * Construcctor por parametros.
	 * @param int $id
	 * @param String $name
	 * @param int $length
	 */
	public function __construct($id, $name, $edges, $vertexs) {
		$this->id = $id;
		$this->name = $name;
		$this->edges = $edges;
		$this->vertexs = $vertexs;
	}

	/**
	 * @return the unknown_type
	 */
	public function getId() {
		return $this->id;
	}

	/**
	 * @param unknown_type $id
	 */
	public function setId($id) {
		$this->id = $id;
	}

	/**
	 * @return the unknown_type
	 */
	public function getName() {
		return $this->name;
	}

	/**
	 * @param unknown_type $name
	 */
	public function setName($name) {
		$this->name = $name;
	}

	/**
	 * @return the unknown_type
	 */
	public function getEdges() {
		return $this->edges;
	}

	/**
	 * @param unknown_type $edges
	 */
	public function setEdges($edges) {
		$this->edges = $edges;
	}

	/**
	 * @return the unknown_type
	 */
	public function getVertexs() {
		return $this->vertexs;
	}

	/**
	 * @param unknown_type $vertexs
	 */
	public function setVertexs($vertexs) {
		$this->vertexs = $vertexs;
	}
	
	public function addEdge($edge){
		array_push($this->edges, $edge);
	}
	
	public function addVertex($vertex){
		array_push($this->vertexs, $vertex);
	}

}
