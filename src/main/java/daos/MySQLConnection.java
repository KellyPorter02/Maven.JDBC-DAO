package daos;

import java.sql.*;

public class MySQLConnection {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_lab?useSSL=false";
    public static final String USER = "testUser";
    public static final String PASS = "testpassword";
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private CarCRUD carCRUD;
    private Car testCar;

    public MySQLConnection() {
//        this.carCRUD = new CarCRUD(this.getConnection());
    }

    public void readDataBase() throws Exception {

        System.out.println("-------- MySQL JDBC Connection ------------");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
        }

        System.out.println("MySQL JDBC Driver Registered!");

//        Connection connection = null;
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("SQL Connection to database established!");
            carCRUD = new CarCRUD(connection);
            testCar = carCRUD.findById(1);
            System.out.println(testCar.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection Failed! Check output console");
        }
        finally {
            try
            {
                if(connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
