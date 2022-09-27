	
		<!DOCTYPE html">
		<?php
		// Setting user cookie.
		$cookie_name = "user";
		$cookie_value = "1";
		setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
		?>
		<html >
	   <head>
		      <title>Courses Management</title>
		      <style type = "text/css">
		         body  { background-color: #F0E68C } 
		         h2    { font-family: arial, sans-serif;
		                 color: blue }
		         input { background-color: blue;
		                 color: yellow;
		                 font-weight: bold }
		      </style>
		   </head>
		   <body>
			  <h2><a href="manageCourses.php">Manage Courses</a></h2>	  
		   </body>
		</html>
