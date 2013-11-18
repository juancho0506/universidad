<!DOCTYPE HTML>
<?php 
require_once '../php/services/CarPoolingServices.php';
?>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Car Pooling</title>
</head>

<body>

	<header>
		<nav>
			<ul>
				<li>Tu Ruta tercer intento Miguelon</li>
			</ul>
		</nav>
	</header>
	
	<section>
	
		<article>
			<header>
				<h2>Grafo Generado</h2>
			</header>
			<p> <?php $service = new CarPoolingServices(); 
				  $map = $service->loadMap();
				  var_dump($map);
				  //echo 'Buscando el nodo a.. <br/>';
				  var_dump($service->getVertexByName('d'));
				  echo 'Ejecutando Dijkstra.. <br/>';
				  $service->calcularRutaOptima('c', 'e', null);
				  //echo 'Estado del grafo despues de Dijkstra <br/>';
				  //var_dump($service->getMap());
				  
			?> </p>
		</article>
		
		
	</section>

	<aside>
		<h2>About section</h2>
		<p>Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
	</aside>

	<footer>
		<p>Copyright 2013 Juan Rodrigo Torres Miguel Perdomo y Christian Arevalo</p>
	</footer>

</body>

</html>
