import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CarMapTest {
    private CarHashMap map;

    @Before
    public void setUp() throws Exception {
        map = new CarHashMap();
    }

    @Test
    //не даст добавлять одинаковые ключи, только 10 разных
       public void whenPut100ElementsWith10DifferentKeysThenSize10() {
        for (int i = 0; i < 100; i++) {
            int index = i % 10; //числа от 0 до 9 (10 элементов) потом снова начнёт повторяться 0,1,2...
            CarOwner carOwner = new CarOwner(index,"Name"+index,"LastName"+index);
            Car car = new Car("Brand"+index,index);
            map.put(carOwner,car);
        }
        assertEquals(10,map.size());
    }

    @Test
    public void whenPut100Size100() {
        for (int i = 0; i < 100; i++) {
            map.put(new CarOwner(i,"Name"+i,"LastName"+i), new Car("Brand"+i,i));
        }
        assertEquals(100,map.size());
    }

    @Test
    public void removeReturnTrueOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            map.put(new CarOwner(i,"Name"+i,"LastName"+i), new Car("Brand"+i,i));
        }
        assertEquals(10,map.size());
        CarOwner forDeleting = new CarOwner(5,"Name"+5,"LastName"+5);
        assertTrue(map.remove(forDeleting));
        assertEquals(9,map.size());
        assertFalse(map.remove(forDeleting));
    }

    @Test
    public void countOfKeysMustBeEqualToCountOfValues () {
        for (int i = 0; i < 100; i++) {
            map.put(new CarOwner(i,"Name"+i,"LastName"+i), new Car("Brand"+i,i));
        }
        assertEquals(100,map.size());
        assertEquals(100,map.keySet().size());
        assertEquals(100,map.values().size());

    }
    @Test
    public void methodGetMustReturnRightValue() {
        for (int i = 0; i < 100; i++) {
            map.put(new CarOwner(i,"Name"+i,"LastName"+i), new Car("Brand"+i,i));
        }
        CarOwner key = new CarOwner(5,"Name"+5,"LastName"+5);
        Car value = map.get(key);
        String expectedCarBrand = "Brand5";
        assertEquals(expectedCarBrand,value.getBrand());
    }

}