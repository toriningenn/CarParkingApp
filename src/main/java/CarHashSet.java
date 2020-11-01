public class CarHashSet implements CarSet {
    private static final int INITIAL_CAPACITY =  16; //static final - константа.
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    @Override
    public boolean add(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            Entry entry = new Entry(car, null);
            array[position] = entry;
            size++;
            return true;
        } else {
            Entry existedElement = array[position]; //получаем то что уже лежит на этой позиции
            while (true) { //программа не зависнет потому что есть точки выхода из метода несмотря на бесконечный цикл
                if (existedElement.value.equals(car)) {
                    return false; //если такой уже существует выходим из метода
                } else if (existedElement.next == null) {
                    Entry entry = new Entry(car, null); //создаём новую энтри и ссылку в существующем последнем
                    existedElement.next = entry;
                    size++;
                    return true;
                } else {  //если есть ещё следующий то записываем его в existed
                    existedElement = existedElement.next;
                }
            }
        }
    }


    //если найденный элемент соответствует тому, что мы хотим удалить, то удаляем его, переписываем ссылку
    //и возвращаем true
    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if(array[position] == null) {
            return false;
        }
        Entry secondLast = array[position]; //нам нужно вынести удаляемый, чтобы сохранить ссылку на СЛЕДУЮЩИЙ
        Entry last = secondLast.next;
        if(secondLast.value.equals(car)) {
            array[position] = last; //даже в ячейке лежало всего одно значение, то last = null и всё будет работать
            size--;
            return true;
        }  if (last.value.equals(car)) {

        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

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
