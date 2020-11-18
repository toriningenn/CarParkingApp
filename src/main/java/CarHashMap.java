import java.util.*;

public class CarHashMap implements CarMap {
    private static final int INITIAL_CAPACITY =  16; //static final - константа.
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private static final double LOAD_FACTOR = 0.75;

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= (array.length*LOAD_FACTOR)) {
            increaseArray();
        }
       boolean added = add(key,value,array);
        if (added) {
        size++;
        }
    }

    public boolean add (CarOwner key, Car value, Entry[] dst) {
        Entry entry = new Entry(key,value,null);
        int index = getElementPosition(key);
        if (dst[index] == null) {
            dst[index] = entry;
            return true;
        } else {
            Entry existedEntry = dst[index];
            while (true) {
                if (existedEntry.key.equals(entry.key)) {
                    existedEntry.value = entry.value;
                    return false;
                } else {
                    if (existedEntry.next != null) {
                        existedEntry = existedEntry.next;
                    } else {
                        existedEntry.next = entry;
                        return true;
                    }
                }
            }
        }
    }



public int getElementPosition(CarOwner key) {
    return Math.abs(key.hashCode() % INITIAL_CAPACITY);
}

    public void increaseArray() {
        Entry[] newArray = new Entry[INITIAL_CAPACITY*2];
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                add(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    @Override
    public Car get(CarOwner key) {
        int index = getElementPosition(key);
        Entry existedElement = array[index];
        while (true) {
            if (existedElement.key.equals(key)) {
                return existedElement.value;
            } else {
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keySet = new HashSet<>();
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                keySet.add(existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return keySet;
    }

    @Override
    public List<Car> values() {
        List<Car> carList = new LinkedList<Car>();
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                carList.add(existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return carList;
    }

    @Override
    public boolean remove(CarOwner key) {
        int index = getElementPosition(key);
        if (array[index]==null) {
            return false;
        } else {
            Entry existedElement = array[index];
            while (existedElement != null) {
                if (existedElement.key.equals(key)) {
                    existedElement = existedElement.next;
                    array[index] = existedElement;
                    size--;
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            } return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Entry[] cleanArray = new Entry[INITIAL_CAPACITY];
        array = cleanArray;
        size = 0;
    }

    private static class Entry {
        private CarOwner key;
        private Car value;
        Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
