package daos;

public class Car implements DTO {

    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private Integer vin;

    public Car() {
    }

    public Car(Integer id, String make, String model, Integer year, String color, Integer vin) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public Car(String make, String model, Integer year, String color, Integer vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car {" +
                " ID = " + id +
                ", MAKE = '" + make + '\'' +
                ", MODEL = '" + model + '\'' +
                ", YEAR = " + year +
                ", COLOR = '" + color + '\'' +
                ", VIN = " + vin +
                '}';
    }
}
