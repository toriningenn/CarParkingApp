import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList implements CarList {
    private Car[] array = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean contains(Car car) {
        for (Car i : array) {
            if (i.equals(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Car car) {
        increaseArray();
        array[size] = car;
        size++;
        return true;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator <Car> () {
            int index = 0; //индекс первого 0

            @Override
            public boolean hasNext() {
                //элементы есть пока индекс меньше чем размер коллекции
                return index < size; //возврвщает true пока он меньше
            }

            @Override
            public Car next() {
                return array[index++]; //!!!!возьми array[index] и когда вернется объект увеличь его!!!!!!постинкремент
                //[++index] – сначала увеличь на 1, потом делай.
            }
        };
    }

    @Override
        public boolean remove (Car car) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(car)) {
                    return removeAt(i);
                }
            } return false;
        }

        @Override
        public boolean removeAt (int index) {
            checkIndex(index);
            //нет элемента с индексом 10, поэтому нужно дойти только до 9
            if (size - 1 - index >= 0) {
                System.arraycopy(array, index + 1, array, index, size - 1 - index);
            }
            size--;
            return true;
        }

        @Override
        public int size () {
            return size;
        }

        @Override
        public void clear () {
        array = new Car[10];
        size = 0;
        }

    @Override
    public boolean add(Car car, int index) {
        increaseArray ();
  //checkIndex не подходит, потому что там индекс не может быть больше или равен size, но в этом методе мы можем добавить
        //объект с индексом size.
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size++;
        return true;
    }

    private void checkIndex (int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
        }

        private void increaseArray () {
            if (size >= array.length) {
                array = Arrays.copyOf(array, array.length * 2);
            }
        }
    }


