<?php
class Edge {

	private $id = 0;

	private $name = "";

	private $length = 0;

	private $from = null;
	private $to = null;
	
	/**
	 *
	 */
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
	public function __construct($id, $name, $length, $from, $to) {
		$this->id = $id;
		$this->name = $name;
		$this->length = $length;
		$this->to = $to;
		$this->from = $from;
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
	public function getLength() {
		return $this->length;
	}

	/**
	 * @param unknown_type $length
	 */
	public function setLength($length) {
		$this->length = $length;
	}

	public function getFrom() {
		return $this->from;
	}

	public function setFrom($from) {
		$this->from = $from;
	}

	public function getTo() {
		return $this->to;
	}

	public function setTo($to) {
		$this->to = $to;
	}

}
