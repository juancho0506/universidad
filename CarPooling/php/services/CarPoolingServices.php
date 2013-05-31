<?php
include '../php/model/Edge.php';
//include 'D:\Files\workspaceUniversidad\CarPooling\php\model\Edge.php';
include '../php/model/Graph.php';
include '../php/model/Map.php';
include '../php/model/Vertex.php';
class CarPoolingServices {

	private $map = null;
	private $visitadosDijkstra = array();

	public function loadMap() {
		$this->map = new Map(1, "Ciudad de Prueba 1", "Ciudad de Prueba 1");
		$this->map->setGraph($this->loadDefaultGraph());
		return $this->map;
	}

	private function loadDefaultGraph() {
		//EL grafo a crear...
		$graph = null;

		//Arreglos de aristas y vertices para el grafo...
		$vertexs = array();
		$edges = array();
		//Se inicializan los vertices y aristas del grafo.
		$vertexA = new Vertex(1, "a", "");
		//array_push($vertexs, $vertexA);
		$vertexs[$vertexA->getName()] = $vertexA;
		$vertexB = new Vertex(2, "b", "");
		//array_push($vertexs, $vertexB);
		$vertexs[$vertexB->getName()] = $vertexB;
		$vertexC = new Vertex(3, "c", "");
		//array_push($vertexs, $vertexC);
		$vertexs[$vertexC->getName()] = $vertexC;
		$vertexD = new Vertex(4, "d", "");
		//array_push($vertexs, $vertexD);
		$vertexs[$vertexD->getName()] = $vertexD;
		$vertexE = new Vertex(5, "e", "");
		//array_push($vertexs, $vertexE);
		$vertexs[$vertexE->getName()] = $vertexE;

		//Se agregan las aristas para cada vertice
		//aristas para A...
		array_push($edges, new Edge(1, "a-b", 10, $vertexA, $vertexB));
		$vertexA->addEdge($edges[0]);
		array_push($edges, new Edge(2, "a-d", 5, $vertexA, $vertexD));
		$vertexA->addEdge($edges[1]);
		//aristas para B
		array_push($edges, new Edge(3, "b-c", 1, $vertexB, $vertexC));
		$vertexB->addEdge($edges[2]);
		array_push($edges, new Edge(4, "b-d", 2, $vertexB, $vertexD));
		$vertexB->addEdge($edges[3]);
		//aristas para C
		array_push($edges, new Edge(5, "c-e", 4, $vertexC, $vertexE));
		$vertexC->addEdge($edges[4]);
		//aristas D
		array_push($edges, new Edge(6, "d-b", 3, $vertexD, $vertexB));
		$vertexD->addEdge($edges[5]);
		array_push($edges, new Edge(7, "d-e", 2, $vertexD, $vertexE));
		$vertexD->addEdge($edges[6]);
		//aristas de e
		array_push($edges, new Edge(8, "e-a", 7, $vertexE, $vertexA));
		$vertexE->addEdge($edges[7]);

		//se completa el grafo
		$graph = new Graph(1, "Grafo CiudadPrueba", $edges, $vertexs);

		return $graph;
	}

	public function getVertexByName($name) {
		$vertexs = $this->map->getGraph()->getVertexs();
		return $vertexs[$name];

	}

	public function calcularRutaOptima($nameStart, $nameEnd, $stops) {

		if ($nameStart != null && $nameEnd != null) {

			$start = $this->getVertexByName($nameStart);
			$end = $this->getVertexByName($nameEnd);

			if ($stops != null) {
				//TODO: Implementar la recogida de pasajeros....	
			} else {
				$this->dijkstra($start);
				echo '<br/>';
				var_dump($this->visitadosDijkstra);
			}
		} else {
			return null;
		}
	}

	/**
	 * Calcula la distancia del nodo inicio a todos los nodos del grafo.
	 * @param Vertex $start
	 */
	public function dijkstra(Vertex $start) {

		$queue = array();
		$start->setDistance(0);
		array_push($this->visitadosDijkstra, $start);
		array_push($queue, $start);

		while (sizeof($queue) != 0) {
			//Nodo a visitar
			$v = array_shift($queue);
			$v->setVisited(true);
			foreach ($v->getEdges() as $e) {
				$temp = $e->getTo();
				echo '<br/>Evaluando: temp=' , $temp->getName() , ' v=', $v->getName();
				if ($temp->getVisited() == false) {
					array_push($queue, $temp);
					echo '<br/>Evaluando: temp=' , $temp->getName() , ' v=', $v->getName() , ' valores: ' , $temp->getDistance() , '-' , ($v->getDistance() + $e->getLength()) , '<br/>';
					//Revisamos que no tenga los vertices con la distancia por defecto....
					if($temp->getDistance()==0){
						$temp->setDistance($v->getDistance() + $e->getLength());
						echo '<br/>Distancia asignada : ' . $temp->getDistance();
						array_push($this->visitadosDijkstra, $temp);
					}else{
						if ($temp->getDistance() > ($v->getDistance() + $e->getLength())) {
							$temp->setDistance($v->getDistance() + $e->getLength());
							array_push($this->visitadosDijkstra, $temp);
							echo '<br/>entra al if....';
							echo '<br/>Distancia asignada : ' . $temp->getDistance();
						}
					}					
					
				}else{
					if ($temp->getDistance() > ($v->getDistance() + $e->getLength())) {
						$temp->setDistance($v->getDistance() + $e->getLength());
						//array_push($this->visitadosDijkstra, $temp);
						array_push($queue, $temp);
						echo '<br/>entra al if segundo....';
						echo '<br/>Distancia asignada : ' . $temp->getDistance();
					}
				}
			}
			
		}
	}

	public function getMap() {
		return $this->map;
	}

	public function setMap($map) {
		$this->map = $map;
	}

}
