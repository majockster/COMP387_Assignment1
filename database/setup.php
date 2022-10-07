<?php
// Connect to MySQL Server
// mysqli_connect( Hostname, username, password)
if (!($conn = mysqli_connect("localhost", "root", ""))) {
    die("Could not connect to database </body>

</html>");
}

// Open database
// mysqli_select_db( connection, database_name
if (!mysqli_select_db($conn, "soen387a1")) {
    die("Could not open SOEN387A1 database </body>

</html>");
}
