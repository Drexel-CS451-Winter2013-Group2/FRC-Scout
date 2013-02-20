<?php
    include("header.php");

    $email=$_POST['email'];
    $pass=md5($_POST['pass']);


    $authUser = User::auth($email, $pass);

    if ($authUser) {
        $_SESSION['user']=$authUser;

        if (isset($_SESSION['ref']))
        {
            redirect($_SESSION['ref']);
        }
        else {
            redirect("../../auth0.php");
        }
    }
    else {
        echo "Could not log in";
    }

?>
</body>
</html>
