<?php
    session_start();
    include('connect.php');
    include('User.php');

function redirect($page)
{
    echo "<script> window.location='$page';</script>";
}

?>
<html>
<head>
</head>
<body>

<?php
    $uri=$_SERVER['REQUEST_URI'];
    if(!strpos($uri,"login") && !strpos($uri,"logout"))
        $_SESSION['ref']=$uri;

    if (isset($_SESSION['user'])) {
        $user = $_SESSION['user'];

        echo "Logged in as $user->last, $user->first ($user->role)";
        echo "<a href='logout.php'>Log Out </a>";

        switch ($user->role) {
            case 2:
                echo "<a href='auth2.php'>Auth 2 </a>";
            case 1:
                echo "<a href='auth1.php'>Auth 1 </a>";
            case 0:
                echo "<a href='auth0.php'>Auth 0 </a>";
        }

        if (isset($level) && $user->role < $level) {
            redirect("login.php");
        }
    }
    else if (isset($level)) {
        redirect("login.php");
    }
?>
<br />
