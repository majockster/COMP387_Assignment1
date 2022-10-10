<?php
	$email = $_POST['email'];
	$password = $_POST['password'];
	

	$servername = "localhost";
	$username = "root";
	$password1 = "";
	$database = "soen387a1";

	

	// Database connection
	$conn = new mysqli($servername, $username, $password1, $database);
	if($conn->connect_error){
		echo "$conn->connect_error";
		die("Connection Failed : ". $conn->connect_error);
	} else {
		$stmt = $conn->prepare("select * from person where email = ?");
		$stmt->bind_param("s", $email);
		$stmt->execute();
		$stmt_result = $stmt->get_result();
		if ($stmt_result->num_rows > 0) {
			$data = $stmt_result->fetch_assoc();
			if ($data['password'] === $password) {
				$id = $data['personID'];
                $sql = $conn->prepare("select * from administrator where personID = ?");
                $sql->bind_param("s", $data['personID']);
                $sql->execute();
                $sql_result = $sql->get_result();
                if ($sql_result->num_rows > 0) {
					setcookie('personID', $id, time()+86400, "/");
					header('Location:adminIndex.php');
				}
				else{
					setcookie('personID', $id, time()+86400, "/");
					header('Location:studentIndex.php');
				}
			}
            else{
				echo "<h2>Invalid email or password</h2>";
			}
		}else{
			echo "<h2>Invalid email or password</h2>";
		}

	}
?>
