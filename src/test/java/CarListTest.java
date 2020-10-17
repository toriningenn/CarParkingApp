import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarListTest {
private CarList carList;

    @Before
    public void setUp() throws Exception {
        carList = new CarArrayList();
        for (int i= 0; i<100; i++) {
            carList.add(new Car("Brand"+i,i));
        }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100() {
         Assert.assertEquals(100, carList.size());
    }

    @Test
    public void whenElementRemovedByIndexThenSizeMustBeDecreased() {
        Assert.assertTrue(carList.removeAt(5)); //возвращает булиан
        Assert.assertEquals(99, carList.size());
    }

    @Test
    public void whenElementRemovedThenSizeMustBeDecreased() {
        Car car = new Car("Toyota",598);
        carList.add(car);
        Assert.assertEquals(101, carList.size());
        carList.remove(car);
        Assert.assertEquals(100, carList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        Car car = new Car("Toyota",598);
        Assert.assertFalse(carList.remove(car));
        Assert.assertEquals(100, carList.size());
    }

    @Test
    public void whenListClearedThenSizeMustBe0 () {
        carList.clear();
        Assert.assertEquals(0, carList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfCollectionBoundsThenThrowException() {
        carList.removeAt(100);
        //последний индекс 99, 100 элементов
    }
    @Test
    public void methodGetReturnedRightValue() {
        Car car = carList.get(4);
        Assert.assertEquals("Brand4", car.getBrand());
    }
}