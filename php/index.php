<?php
	require_once __DIR__ . '/php-graph-sdk-5.0.0/src/Facebook/autoload.php';
	$access_token = "EAAUkamWGdZAkBAPF0xNMjT8qQF4NPaTY3joDSZAiNAOKXoxw52e1EcKsbkrktcwNwIwtL2RVBkhuNYS7Vs41EHsaNsI6FCZAoI4fZCjG8opBZATZAXzeP4KDfJnA0UMs4KZBMUbv8NQDfEdd6ZCE4RcChE3FcYqksbwZD";
	$fb = new Facebook\Facebook([
        'app_id' => '1447414271997337',
        'app_secret' => '60951f9e35b589f8a2c17fc6d4f7ae72',
        'default_graph_version' => 'v2.9',
      ]);
    $fb->setDefaultAccessToken($access_token);
    $q ="";
    $latitude="";
    $longitude="";
    $type ='';


    //search
    if (isset($_GET['q'])){
    	$q = urlencode($_GET["q"]);  
    	$type = $_GET["type"];
    	if (isset($_GET['latitude'])){
    		$latitude = $_GET["latitude"];
        	$longitude = $_GET["longitude"];	
    	}
        

        if($type =='place'){
        	if($latitude=="" ||$longitude==""){
        		$response = $fb->get("/search?q=$q&type=place&fields=id,name,picture.width(700).height(700)");
        		$json = $response->getBody();
        	}else{
        		$response = $fb->get("/search?q=$q&type=place&center=$latitude,$longitude&fields=id,name,picture.width(700).height(700)");
        		$json = $response->getBody();
        	}
        }else{
        	$response = $fb->get("/search?q=$q&type=$type&fields= id,name,picture.width(700).height(700)");
        	$json = $response->getBody();
        }
        //echo json_encode($json);
        echo $json;
    }

    //detail

    if (isset($_GET['detail'])){
        $id_data = $_GET["detail"];
        $detail_url ="/$id_data?fields=id,name,picture.width(700).height(700),albums.limit(5){name,photos.limit(2){name,picture}},posts.limit(5)";
        $response = $fb->get($detail_url);
        $json = $response->getBody();
        echo  $json;
    }

?>