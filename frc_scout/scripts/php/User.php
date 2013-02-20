<?php
class User
{
    public $email;
    public $password;
    public $first;
    public $last;
    public $role;

    public static function auth($email, $password)
    {
        global $db;
        $sql = "SELECT * from user WHERE email='$email' AND password='$password' LIMIT 1;";
        $q=$db->query($sql);

        if ($q->numRows()) {
            $row = $q->fetch();
            return (object) $row;
        }
        else {
            return NULL;
        }
    }

    public static function report($col='*')
    {
        global $db;
        $sql = "SELECT $col from user;";
        $q=$db->query($sql);

        $result = $q->fetchAll(SQLITE_ASSOC);
        foreach ($result as &$row) {
            $row = (object) $row;
        }

        return $result;
    }

    public static function reportJSON($col='*')
    {
        global $db;
        $sql = "SELECT $col from user;";
        $q=$db->query($sql);

        $result = $q->fetchALL(SQLITE_ASSOC);
        return json_encode($result);
    }
}
?>
