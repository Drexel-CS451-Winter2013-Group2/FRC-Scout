<?php
    include("scripts/php/header.php");
?>

<form action="scripts/php/login.php" method="POST">
<h1>Login:</h1>
    <table>
        <tr><td>Email:</td>
        <td><input id='email' name='email' type='text' /></td>
        </tr>

        <tr><td>Password:</td>
        <td><input id='pass' name='pass' type='password'/></td></tr>
        <tr><td><input type='submit' value='Sign In!'/></td></tr>
    </table>
</form>

</body>
</html>
