<?php
    $level=2;
    include("scripts/php/header.php");

    $users = User::report();
    foreach ($users as $user) {
        echo "$user->first $user->last $user->email $user->role $user->password<br />\n";
    }

    echo "<br /><br />\n";
    echo User::reportJSON("email, first, last");
?>
