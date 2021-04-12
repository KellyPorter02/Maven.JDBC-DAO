package daos;

import org.junit.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarCRUDTest {

    Connection connection;
    CarCRUD testCarCRUD;
    PreparedStatement testPS;
    ResultSet testRS;
    Car car1;
    Car car2;
    Car car3;
    Car car4;
    Car car5;

    @Before
    public void setUp() throws Exception {
        String URL = "jdbc:mysql://localhost:3306/jdbc_lab?useSSL=false";
        String USER = "testUser";
        String PASS = "testpassword";
        connection = DriverManager.getConnection(URL, USER, PASS);
        testCarCRUD = new CarCRUD(connection);
        car1 = new Car(1, "Mazda", "3 Hatchback", 2010, "Black", 123);
        car2 = new Car(2, "Honda", "Accord", 2020, "Grey", 456);
        car3 = new Car(3, "Septa", "Bus", 2009, "White", 789);
        car4 = new Car(4, "Honda", "Accord", 2014, "Black", 147);
        car5 = new Car(5, "Hyndai", "Sonata", 2004, "Pink", 258);
    }

    @After
    public void tearDown() throws Exception {
        testPS.close();
        connection.close();
//        testRS.close();
//        car1 = null;
//        car2 = null;
//        car3 = null;
//        car4 = null;
//        car5 = null;
    }


    @Test
    public void constructorNulleryTest() {
        // arrange
        CarCRUD test = new CarCRUD();
        Car expected = null;
        // act
        Car returned = test.getCar();
        // assert
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void constructorConnectionTest() throws SQLException {
        // arrange
        Connection expected = connection;
        // act
        Connection returned = testCarCRUD.getConnect();
        // assert
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void findById1Test() throws SQLException {
        // arrange
        int givenID = 1;
        Car testCar = new Car(givenID, "Mazda", "3 Hatchback", 2010, "Black", 123);
        String expectedCarString = testCar.toString();
        // act
        Car returnedCar = testCarCRUD.findById(givenID);
        String returnedCarString = returnedCar.toString();
        // assert
        Assert.assertEquals(expectedCarString, returnedCarString);
    }

    @Test
    public void findById2Test() throws SQLException {
        // arrange
        int givenID = 2;
        Car testCar = new Car(givenID, "Honda", "Accord", 2020, "Grey", 456);
        String expectedCarString = testCar.toString();
        // act
        Car returnedCar = testCarCRUD.findById(givenID);
        String returnedCarString = returnedCar.toString();
        // assert
        Assert.assertEquals(expectedCarString, returnedCarString);
    }

    @Test (expected = NullPointerException.class)
    public void findByIdFailTest() throws SQLException {  // no Car identified in table with ID 100
        // arrange
        int givenID = 100;
        Car testCar = new Car(givenID, "Mazda", "3 Hatchback", 2010, "Black", 123);
        // act
        Car returnedCar = testCarCRUD.findById(givenID);
        // assert
        String returnedCarString = returnedCar.toString();
    }

    @Test
    public void findAllCars() throws SQLException {
        // arrange
        List<Car> expectedCarList = new ArrayList<Car>();
        expectedCarList.add(car1);
        expectedCarList.add(car2);
        expectedCarList.add(car3);
        expectedCarList.add(car4);
        expectedCarList.add(car5);
        String expectedListString = expectedCarList.toString();
        // act
        List<Car> returnedCarList = testCarCRUD.findAllCars();
        String actualListString = returnedCarList.toString();
        // assert
        Assert.assertEquals(expectedListString, actualListString);
    }

    @Test
    public void createCar() throws SQLException {
        // arrange
        Car car6 = new Car(6, "Subaru", "Impreza", 2013, "Grey", 369);
        String expected = "Car { ID = 6, MAKE = 'Subaru', MODEL = 'Impreza', YEAR = 2013, COLOR = 'Grey', VIN = 369}";
        // act
        String returned = testCarCRUD.createCar(car6);
        // assert
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void updateCar() throws SQLException {
        // arrange
        car1 = new Car(1, "Subaru", "Impreza", 2013, "Grey", 369);
        String expected ="Car { ID = 1, MAKE = 'Subaru', MODEL = 'Impreza', YEAR = 2013, COLOR = 'Grey', VIN = 369}";
        // act
        String returned = testCarCRUD.updateCar(car1);
        // assert
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void deleteCar() throws SQLException {
        // arrange
        String expected = "";//"Car has been deleted from table:  Car { ID = 5, MAKE = 'Hyndai', MODEL = 'Sonata', YEAR = 2004, COLOR = 'Pink', VIN = 258}";
        // act
        String returned = testCarCRUD.deleteCar(car5.getId());
        // assert
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void extractCarFromResultSet() {
        // arrange

        // act

        // assert
//        Assert.assertEquals();
    }
}