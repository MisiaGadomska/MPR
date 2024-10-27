import org.example.Car;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void carTurnsOnWhenItHasBatteryAndFuel(){
        Car car = new Car(20, 50, 50, 0.8f);

        boolean result = car.turnOn();

        assertTrue(result);
    }

    @Test
    public void carDoesntTurnOnWhenItHasBatteryAndNoFuel(){
        Car car = new Car(20, 0, 50, 0.8f);

        boolean result = car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carHasMoreFuelWhenRefuelledButNoMoreThanFuelCapacity(){
        Car car = new Car(20,0,50,0.8f);

        car.refuel(60);

        assertEquals(car.getFuelCapacity(), car.getFuelAmount()); //czy pojemność zbiornika paliwa (fuelCapacity) jest równa aktualnej ilości paliwa (fuelAmount).
    }

    @Test
    public void carIsNotRefuelledWhenFuelAmountIs0OrLess(){
        Car car = new Car(20, 0, 50, 0.8f);

        car.refuel(-10);

        assertEquals(0, car.getFuelAmount()); //fuelAmount w samochodzie (car.getFuelAmount()) pozostał na poziomie 0, ponieważ metoda refuel nie powinna była zmienić ilości paliwa po otrzymaniu niepoprawnej wartości
    }

    @Test
    public void carIncreasesDistanceTravelledWhenItHasEnoughFuel(){
        Car car = new Car(20,40,50, 0.8f);

        boolean result;
        car.drive(10);
        car.drive(10);
        result = car.drive(15);

        assertTrue(result);
        assertEquals(12, car.getFuelAmount());
        assertEquals(35, car.getDistanceTravelled());
    }

    @Test
    public void carIncreasesDistanceTravelledWhenItHasEnoughFuelButStopsWhenFuelRunsOut(){
        Car car = new Car(20, 40, 50, 0.8f);

        boolean result;
        car.drive(10);
        car.drive(30);
        result = car.drive(15); // tu juz nie ma paliwa

        assertFalse(result);
        assertEquals(8, car.getFuelAmount());
        assertEquals(40, car.getDistanceTravelled());
    }
}
