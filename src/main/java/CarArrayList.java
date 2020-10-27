import java.util.Arrays;

public class CarArrayList implements CarList {
    private Car[] array = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void add(Car car) {
        increaseArray();
        array[size] = car;
        size++;
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
    public void add(Car car, int index) {
        increaseArray ();
  //checkIndex не подходит, потому что там индекс не может быть больше или равен size, но в этом методе мы можем добавить
        //объект с индексом size.
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size++;
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


