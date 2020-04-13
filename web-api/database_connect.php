<?php
/*when you have a pure PHP file, you don't need to close the tag of PHP. Preferred practice.*/

$connections = array();

//Used to connect to the wandika database.
//Based on code provided by Dr. Alan Hunt
function connect(){
    $dbServername= "tethys.cse.buffalo.edu";
    $dbUsername= "chimaobi";
    $dbPassword= "50179050";
    $dbName= "cse442_542_2020_spring_teamq_db";
    global $connections;

    error_log("Connect to ".$dbname." as user ".$username, 0);

    $conn = null;

    //Create the connection
	try{
        $conn = new PDO("mysql:host=$servername; dbname=$dbname", $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        array_push($connections, $conn);
        return $conn;
    }catch(Exception $e){
        echo "connection error ".$servername;
        error_log("Error Connecting to ".$dbname." as user ".$username, 0);
    }   
}
?>