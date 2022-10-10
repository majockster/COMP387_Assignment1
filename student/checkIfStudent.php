<?php
    $studentType = "student";

    if (!isset($_COOKIE["personID"])) {
        die("Could not retrieve user cookie. </body></html>");
    }

    if (!isset($_COOKIE["userType"])) {
        die("Could not retrieve user's type cookie. </body></html>");
    }

    if ($_COOKIE["userType"] != $studentType) {
        die("You are not a student, and thus cannot view this page. Sorry! </body></html>");
    }
?>