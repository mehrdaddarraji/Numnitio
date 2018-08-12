<?php
    $con = mysqli_connect("numnitio.000webhostapp.com", "id6695715_numnitioregister", "Bd789789!", "id6695715_numnitioregister");

    $email = $_POST["email"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $email, $password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["email"] = $email;
        $response["password"] = $password;
    }

    echo json_encode($response);
?>
