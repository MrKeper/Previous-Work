import java.util.*;
import java.io.*;
import java.sql.*;


public class project4_kpp4878
{
    public static void main(String args[])
    {
        System.out.println("From Airport: ");
        //get input
        System.out.println("To Airport: ");
        //get input
        System.out.println("Seat Class: ");
        //get input
        try
    	{
    		Class.forName ("com.mysql.jdbc.Driver");
    	}
    	catch(Exception e)
    	{
            System.out.println("JDBC MySQL driver failed to load");
    	}

        
    }
}