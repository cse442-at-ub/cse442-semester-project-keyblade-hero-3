<?php
    require 'database_connect.php';
    date_default_timezone_set('America/New_York');
    // the response will be a JSON object
    header('Content-Type: application/json');
    header('Access-Control-Allow-Origin: *');
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    $conn = null;
    $conn = connect();
    $sql = "SELECT * FROM Champa_Sushi, Jamba_Juice";
    $champa_menu = $conn->query($sql);
    $champa_menu = $champa_menu->fetchall();
    $sql2 = "SELECT * FROM Jamba_Juice";
    $jamba_menu = $conn->query($sql2);
    $jamba_menu = $jamba_menu->fetchall();
    print_r($champa_menu);
    print_r($jamba_menu);
    $conn = null;
?>