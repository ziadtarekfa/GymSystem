package mainClass;

import java.sql.*;
public class con1 {
    private String user="root";
    private String password="tony147258369";
    private String address="jdbc:mysql://localhost:3306/gym_manegment_system";
    private String c="?characterEncoding=latin1";
    private static Connection con;

    public con1() throws SQLException {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(address,user,password);
            System.out.println("Connecting to database ...... ");

        } catch(SQLException | ClassNotFoundException e)
        {
            System.out.println("database error in connection");
            System.out.println(" Couldnot connect     error:" + e);
        }

    }

    public ResultSet displayData_db(String query) throws SQLException {

        System.out.println("Function sends query to display data from  database and return rs");
        Statement stat = con1.getCon1().createStatement();
        ResultSet rs = stat.executeQuery(query)  ;                                        //execute query to display

       // stat.close();
        return rs;

    }

    public static Connection getCon1() {

        return con;
    }

    public int alterData_db(String query) throws SQLException {

        int rs;
        System.out.println("Function sends query to display data from  database and return rs");
        Statement stat = con1.getCon1().createStatement();
        rs = stat.executeUpdate(query);

        //execute update to alter


        return rs;
    }

//    public static void commit_method() throws SQLException {
//        System.out.println("Commit sent to database");
//    }

    public static void close_connection() throws SQLException {
        con.close();
    }


}


