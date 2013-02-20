<?php

$db = new SQLiteDatabase('frcs');
$sql = "SELECT * from user;";
$q=$db->query($sql);
$result = $q->fetchAll();
foreach ($result as $row) {
    echo "Entry: ".$row['email']." ".$row['pass']."<br />\n";
}
?>
