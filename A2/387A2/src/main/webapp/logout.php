<?php
    // Clearing cookie
    setcookie('firstName', null, -1, "/");
    setcookie('lastName', null, -1, "/");
    setcookie('personID', null, -1, "/");
    setcookie('userType', null, -1, "/");
    header('Location:./');
?>