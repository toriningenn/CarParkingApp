import java.util.Iterator;

public class CarHashSet implements CarSet {
    private static final int INITIAL_CAPACITY =  16; //static final - константа.
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private static final double LOAD_FACTOR = 0.75;


    @Override
    public boolean contains(Car car) {
        int pos = getElementPosition(car, array.length);
        if (array[pos] == null) {
            return false;
        }
        Entry entry = array[pos];
        while(entry != null) {
            if (entry.value.equals(car)) {
                return true;
            } else {
                entry = entry.next;
            }
        } return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return null;
    }

    @Override
    public boolean add(Car car) {
        //добавляет в массив машину, увеличивает размер если успешно добавлено. проверяет на забитость
        if (size >= (array.length*LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    //если найденный элемент соответствует тому, что мы хотим удалить, то удаляем его, переписываем ссылку
    //и возвращаем true
    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length); //получаем номер позиции
        if (array[position] == null) {
            return false; //если там ничего нет выходим и возвращаем false
        }
        Entry secondLast = array[position]; //нам нужно найти и вынести удаляемый, чтобы сохранить ссылку на СЛЕДУЮЩИЙ
        Entry last = secondLast.next;
        if (secondLast.value.equals(car)) {
            array[position] = last; //даже в ячейке лежало всего одно значение, то last = null и всё будет работать.
            //ЗАПИСЫВАЕМ НА ЕГО МЕСТО СЛЕДУЮЩИЙ ЛИБО NULL ЕСЛИ СОВПАДАЕТ
            size--;
            return true;
        }
        while (last != null) {
            if (last.value.equals(car)) {
                secondLast.next = last.next; //теперь на last никто не ссылается и он исчезнет
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;

    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                add(existedElement.value, newArray);
                existedElement = existedElement.next; //в каждой ячейке может лежать ещё цепочка объектов
            }
        }
        array = newArray;
    }

    private boolean add(Car car, Entry[] dst) {
        // просто добавляет всё в массив дестинации
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.value.equals(car)) {
                    return false;
                } else if (existedElement.next == null) {
                    Entry entry = new Entry(car, null);
                    existedElement.next = entry;
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    private int getElementPosition(Car car,int arrayLength) {
        return Math.abs(car.hashCode() % arrayLength);
    }

    private static class Entry {
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}

