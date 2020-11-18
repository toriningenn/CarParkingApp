import java.util.Objects;

public class Car {
    public Car(String brand, int number) {
        this.brand = brand;
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public int getNumber() {
        return number;
    }

    private String brand;
    private int number;


    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number == car.number &&
                Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, number);
    }
}