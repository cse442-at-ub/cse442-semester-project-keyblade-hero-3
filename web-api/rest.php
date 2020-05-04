<?php
    require 'database_connect.php';
    date_default_timezone_set('America/New_York');
    // the response will be a JSON object
    header('Content-Type: application/json');
    header('Access-Control-Allow-Origin: *');
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    $json_params = file_get_contents('php://input');
    $decoded_params = json_decode($json_params, TRUE);
    $conn = null;
    $rest = decoded_params[restaurant];
    $conn = connect();
    //$sql = "SELECT * FROM Champa_Sushi";
    $sql = "SELECT * FROM $rest";
    $menu = $conn->query($sql);
    $menu = $menu->fetchall();
    print_r($menu);
    //$conn2 = null;
    //$conn2 = connect();
    //$sql2 = "SELECT * FROM Jamba_Juice";
    //$jamba_menu = $conn->query($sql2);
    //$jamba_menu = $jamba_menu->fetchall();
    //print_r($jamba_menu);
    //$conn2 = null;
    $conn = null;

?>