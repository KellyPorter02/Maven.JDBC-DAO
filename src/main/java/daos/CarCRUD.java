package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarCRUD implements CarDAO {

    protected Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    protected List<Car> carList = new ArrayList<Car>();
    private Car car = null;


    public CarCRUD() {
    }

    public CarCRUD(Connection connect) {
        this.connect = connect;
    }


    public Car findById(int id) throws SQLException {  // make it return whatever i want a string to test
        System.out.println("Creating statement: Find Car By ID");
        String findByIDQuery = "SELECT * FROM jdbc_lab.cars WHERE id IS ?";
        try {
            preparedStatement.setInt(1, id);
            preparedStatement = connect.prepareStatement(findByIDQuery);
            resultSet = preparedStatement.executeQuery(findByIDQuery);
            // Extract data from result set
            while (resultSet.next()) {
                // Retrieve by column name (taken care of in this helper method)
                car = extractCarFromResultSet(resultSet);
                //Display values
                System.out.print(car.toString());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
//            resultSet.close();
        }
//        resultSet.close();
        return car;
    }

    public List<Car> findAllCars () throws SQLException {
        System.out.println("Creating statement: Find All Cars in Cars Table");
        String findAllCarsQuery = "SELECT * FROM jdbc_lab.cars";
        try {
            preparedStatement = connect.prepareStatement(findAllCarsQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery(findAllCarsQuery);
            // Extract data from result set
            System.out.println("Contents of the Cars table: \n");
            resultSet.beforeFirst();
            while(resultSet.next()) {
                // Retrieve by column name
                car = extractCarFromResultSet(resultSet);
                carList.add(car);
                //Display values
                System.out.print(carList.toString());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            resultSet.close();
        }
        resultSet.close();
        return carList;
    }

    public String createCar (Car car) throws SQLException {  // creating row to insert into table
        System.out.println("Creating statement: Create new Car entry in database");
        String insertCarEntry = "INSERT INTO jdbc_lab.cars (id, make, model, year, color, vin) VALUES (default, ?, ?, ?, ?, ?)";
        String resultOfInsert = "";
        int numRowsChanged = 0;
        try {
            preparedStatement = connect.prepareStatement(insertCarEntry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setInt(5, car.getVin());
            numRowsChanged = preparedStatement.executeUpdate();
            // Extract data from result set
            while (resultSet.next()) {
                // Retrieve by column name
                this.car = extractCarFromResultSet(resultSet);
                //Display values
                resultOfInsert = this.car.toString();
                System.out.print(resultOfInsert);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            resultOfInsert = "Attempt to insert new car entry into table failed";
            System.out.println(resultOfInsert);
        }
        resultSet.close();
        return resultOfInsert;
    }

    public String updateCar (Car car) throws SQLException {  // makes changes to a car that already exists in the table
        String updateCar = "UPDATE jdbc_lab.cars SET make = ?, model = ?, year = ?, color = ?, vin = ? WHERE id = ?";
        String initialRowString = findById(car.getId()).toString();
        String changedRowString = "";
        int numOfRowsUpdated = 0;
        try {
            PreparedStatement ps = connect.prepareStatement(updateCar, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, car.getMake());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setInt(5, car.getVin());
            ps.setInt(6, car.getId());
            numOfRowsUpdated = preparedStatement.executeUpdate();
            changedRowString = findById(car.getId()).toString();
            System.out.println("Initial Car value:  " + initialRowString + "\nUpdated Car value:  " + changedRowString);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            changedRowString = "Attempt to update Car entry into table failed";
        }
        resultSet.close();
        return changedRowString;
    }

    public String deleteCar (int id) throws SQLException {
        String deleteCarUpdate = "DELETE FROM jdbc_lab.cars WHERE id = ?";
        String initialRowString = findById(id).toString();
        String deleteResult = "";
        int numOfRowsUpdated = 0;
        try {
            preparedStatement.clearParameters();
            preparedStatement = connect.prepareStatement(deleteCarUpdate, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1, id);
            numOfRowsUpdated = preparedStatement.executeUpdate();
            deleteResult = "Car has been deleted from table:  " + initialRowString;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            deleteResult = "Attempt to delete Car entry from table unsuccessful";
        }
        resultSet.close();
        return deleteResult;
    }


    public Car extractCarFromResultSet (ResultSet resultSet) throws SQLException {
        Car result;  // returns DTO Car object if action successful, null if error thrown
        try {
            int id = resultSet.getInt("id");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            int year = resultSet.getInt("year");
            String color = resultSet.getString("color");
            int vin = resultSet.getInt("vin");
            result = new Car(id, make, model, year, color, vin);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("Retrieval of car data unsuccessful at returnResultSet method.");
            result = null;
        }
        return result;
    }


}
