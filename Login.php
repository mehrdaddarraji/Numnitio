<?php
    if($_SERVER['REQUEST_METHOD' == 'POST']) {
    	$email = $_POST["email"];
      	$password = $_POST["password"];

        $conn = mysqli_connect("localhost", "root", "root", "numnitio");
        
        $sql = "SELECT * FROM user WHERE email = '$email'";

        $response = mysqli_query($conn, $sql);

        $result = array();
        $result['signin'] = array();

        if(mysql_num_rows($response) === 1) {
            $row = mysql_fetch_assoc($response);

            if(password_verify($password, $row['password'])) {
                $index['name'] = $row['name'];
                $index['email'] = $row['email'];
                array_push($result['signin'], $index);

                $result['success'] = "1";
      	        $result['message'] = "success";
                echo json_encode($result);

                mysql_close($conn);
            } else {
                $result['success'] = "0";
      	        $result['message'] = "error";
                echo json_encode($result);

                mysql_close($conn);
            }
        }
        
    }
?>
