import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarSetTest {

    private CarHashSet carSet;

    //обязательно инициализируем и заполняем коллекцию перед всеми тестами
    @Before
    public void setUp() throws Exception {
        carSet = new CarHashSet();
        for (int i= 0; i<100; i++) {
            carSet.add(new Car("Brand" + i,i));
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
    public void containsMethodReturnsTrueIfElementExists () {
        Car newcar = new Car ("BMW",554);
        carSet.add(newcar);
        Assert.assertTrue(carSet.contains(newcar));
    }

    @Test
    public void containsMethodReturnsFalseIfElementDoesNotExist () {
        Car newcar = new Car ("BMW",554);
        Assert.assertFalse(carSet.contains(newcar));
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
        Assert.assertTrue(carSet.remove(new Car("Brand2",2)));
        Assert.assertEquals(99,carSet.size());
        Assert.assertFalse(carSet.remove(new Car("Brand2",2))); //пробуем удалить ещё раз
        //чтобы убедиться, что его там не было
        Assert.assertEquals(99,carSet.size());
    }

    //когда зачистим размер будет 0
    @Test
    public void whenClearSizeIsZero() {
        carSet.clear();
        Assert.assertEquals(0, carSet.size());
    }

}
