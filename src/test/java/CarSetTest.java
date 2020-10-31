import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarSetTest {
    private CarSet carSet;

    //обязательно инициализируем и заполняем коллекцию перед всеми тестами
    @Before
    public void setUp() throws Exception {
        //init
        for (int i= 0; i<100; i++) {
            carSet.add(new Car("Brand"+i,i));
        }
    }


    //не будет добавляться такая же машина, выдаст false и размер останется прежним
    @Test
    public void whenAddCarWontAddTheSame() {
        Assert.assertTrue(carSet.add(new Car("BMW",1)));//убедимся что первая добавлено
        Assert.assertFalse(carSet.add(new Car("BMW",1)));
        Assert.assertEquals(101,carSet.size());
    }

    @Test
    public void whenAdd3SimilarObjectsThenSizeIncreaseBy1() {
        Assert.assertEquals(100, carSet.size());
        Assert.assertTrue(carSet.add(new Car("BMW",1)));//убедимся что первая добавлено
        Assert.assertFalse(carSet.add(new Car("BMW",1)));//убедимся, что добавление не произошло
        Assert.assertFalse(carSet.add(new Car("BMW",1)));
        Assert.assertEquals(101,carSet.size());
    }

    //размер увеличится
    @Test
    public void whenAddedSizeIncrease() {
        carSet.add(new Car("BMW",10));
        Assert.assertEquals(101,carSet.size());
    }
    //размер уменьшится когда уберём машину
    @Test
    public void whenRemoveCarSizeDecreased() {
        Assert.assertTrue(carSet.remove(new Car("brand",30)));
        Assert.assertEquals(99,carSet.size());
    }

    //когда зачистим размер будет 0
    @Test
    public void whenClearSizeIsZero() {
        carSet.clear();
        Assert.assertEquals(0, carSet.size());
    }

}