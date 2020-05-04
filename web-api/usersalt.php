<?php
    
    if (!isset($_SESSION)) 
    {
     session_start();
    }

   require 'database_connect.php';
   date_default_timezone_set('America/New_York');
   // the response will be a JSON object
   header('Content-Type: application/json');
   header('Access-Control-Allow-Origin: *');
   header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
   // pull the input, which should be in the form of a JSON object
   $json_params = file_get_contents('php://input');
   $decoded_params = json_decode($json_params, TRUE);
   //print_r($decoded_params);
   $conn = null;
   $conn = connect();
   $username = null;
   $pass = null;
   $sql = "SELECT salt_value FROM Users WHERE (username = :username)";
   $stmt = $conn->prepare($sql);
   $stmt->bindParam(':username', $username);
    
   $username = $decoded_params[username];
   
   $count = $stmt->execute();
   $count = $stmt->fetch();
   print_r($count[0]);
   $conn = null;

?>