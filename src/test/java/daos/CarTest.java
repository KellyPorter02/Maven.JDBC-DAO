package daos;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void instanceOfDTO() {
        // arrange
        Car testCar = new Car();
        // act

        // assert
        Assert.assertTrue(testCar instanceof DTO);
    }

    @Test
    public void getId() {
        // arrange
        Car testCar = new Car(1, "Mazda", "3 Hatchback", 2010, "Black", 123);
        int expectedID = 1;
        // act
        int returned = testCar.getId();
        // assert
        Assert.assertEquals(expectedID, returned);
    }

    @Test
    public void testToString() {
        // arrange
        Car testCar = new Car(1, "Mazda", "3 Hatchback", 2010, "Black", 123);
        String expected = "Car { ID = 1, MAKE = 'Mazda', MODEL = '3 Hatchback', YEAR = 2010, COLOR = 'Black', VIN = 123}";
        // act
        String returned = testCar.toString();
        // assert
        Assert.assertEquals(expected, returned);
    }
}