package assignment.team._387a2.helperObjects;
import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLConnection {
    public ResultSet currentResultSet;
    public Connection currentConnection;
    public Statement currentStatement;

    public SQLConnection()
    {
        if(!TestConnection())
        {
            System.exit(1);
        }
    }

    public boolean TestConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/soen387a1","root","");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from person LIMIT 2");
            statement.close();
            connection.close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public ResultSet ExecuteQuery(String pQuery)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/soen387a1","root","");

            currentConnection = connection;
            Statement statement = connection.createStatement();
            currentStatement = statement;
            ResultSet result = statement.executeQuery(pQuery);
            currentResultSet = result;
            return result;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            try
            {
                currentStatement.close();
                currentConnection.close();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            return null;
        }
    }

    public ResultSet ExecuteQuery(String pQuery, int concurrencyValue)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/soen387a1","root","");

            currentConnection = connection;
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, concurrencyValue);
            currentStatement = statement;
            ResultSet result = statement.executeQuery(pQuery);
            currentResultSet = result;
            return result;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            try
            {
                currentStatement.close();
                currentConnection.close();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            return null;
        }
    }

    public void ExecuteNoReturn(String pQuery)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/soen387a1","root","");

            currentConnection = connection;
            Statement statement = connection.createStatement();
            currentStatement = statement;
            statement.execute(pQuery);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            try
            {
                currentStatement.close();
                currentConnection.close();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public void Close()
    {
        // Closes the current connection
        try
        {
            currentStatement.close();
            currentConnection.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static int NumberOfRows(ResultSet pResult)
    {
        try
        {
            if(pResult.last())
            {
                int numOfRows = pResult.getRow();
                // Move back to the start
                pResult.beforeFirst();

                return numOfRows;
            }
            else
            {
                return 0;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Unhandled exception: " + ex.getMessage());
            return -1;
        }
    }
}
