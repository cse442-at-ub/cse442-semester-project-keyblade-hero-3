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

    //echo json_encode($_POST);
    $json_params = file_get_contents('php://input');
    $decoded_params = json_decode($json_params, TRUE);
    $conn = null;
    if(is_array($decoded_params) && array_key_exists('post_id', $decoded_params)){
        $post_id = $decoded_params['post_id'];
    }
    if(is_array($decoded_params) && array_key_exists('content', $decoded_params)){
        $post_id = $decoded_params['content'];
    }
    //$_SESSION['user_id'] = 99999;
    if(isset($_POST['action']) && !empty($_POST['action'])) {
        $action = $_POST['action'];
    if(isset($_POST['postid']) && !empty($_POST['postid'])) {
       $post_id = $_POST['postid'];
    }
    // if(isset($_POST['tag']) && !empty($_POST['tag'])) {
    //    $tag = $_POST['tag'];
    // }
   $user_id=$_SESSION['u_id'];
            switch($action) {
                case 'on-load': on_load_menus(); break;
                case 'get-menu': get_menu(); break;
                default: echo "No such function";
            }
        
    }