import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarListTest {
private CarList carLinkedList;

    @Before
    public void setUp() throws Exception {
       carLinkedList = new CarLinkedList();
       for(int i = 0; i < 100; i++) {
           carLinkedList.add(new Car("Brand"+i,i));
       }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100() {
         Assert.assertEquals(100, carLinkedList.size());
    }

    @Test
    public void whenElementRemovedByIndexThenSizeMustBeDecreased() {
        Assert.assertTrue(carLinkedList.removeAt(5)); //возвращает булиан
        Assert.assertEquals(99, carLinkedList.size());
    }
    @Test
    public void containsMethodReturnsTrueIfElementExists () {
        Car newcar = new Car ("BMW",554);
        carLinkedList.add(newcar);
        Assert.assertTrue(carLinkedList.contains(newcar));

    }

    @Test
    public void containsMethodReturnsFalseIfElementDoesNotExist () {
        Car newcar = new Car ("BMW",554);
        Assert.assertFalse(carLinkedList.contains(newcar));
    }

    @Test
    public void whenElementRemovedThenSizeMustBeDecreased() {
        Car car = new Car("Toyota",598);
        carLinkedList.add(car);
        Assert.assertEquals(101, carLinkedList.size());
        carLinkedList.remove(car);
        Assert.assertEquals(100, carLinkedList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        Car car = new Car("Toyota",598);
        Assert.assertFalse(carLinkedList.remove(car));
        Assert.assertEquals(100, carLinkedList.size());
    }

    @Test
    public void whenListClearedThenSizeMustBe0 () {
        carLinkedList.clear();
        Assert.assertEquals(0, carLinkedList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfCollectionBoundsThenThrowException() {
        carLinkedList.removeAt(100);
        //последний индекс 99, значит 100 элементов
    }
    @Test
    public void methodGetReturnedRightValue() {
        Car car = carLinkedList.get(4);
        Assert.assertEquals("Brand4", car.getBrand());
    }

    @Test
    public void InsertIntoMiddle () {
        Car car = new Car("BMW",1);
        carLinkedList.add(car,50);
        Car carFromList = carLinkedList.get(50);
        Assert.assertEquals("BMW", carFromList.getBrand());
    }

    @Test
    public void InsertIntoLastPosition () {
        Car car = new Car("BMW",1);
        carLinkedList.add(car,100);
        Car carFromList = carLinkedList.get(100);
        Assert.assertEquals("BMW", carFromList.getBrand());
    }

@Test
public void InsertIntoFirstPosition () {
        Car car = new Car("BMW",1);
        carLinkedList.add(car,0);
        Car carFromList = carLinkedList.get(0);
        Assert.assertEquals("BMW", carFromList.getBrand());
        }
}