package daos;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    public Car findById(int id) throws SQLException;
    public List<Car> findAllCars() throws SQLException;
    public String updateCar(Car car) throws SQLException;
    public String createCar(Car car) throws SQLException;
    public String deleteCar(int id) throws SQLException;
}
