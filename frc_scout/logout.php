<?php
    include("scripts/php/header.php");
    unset($_SESSION['user']);
    unset($_SESSION['ref']);
    redirect("login.php");
?>
