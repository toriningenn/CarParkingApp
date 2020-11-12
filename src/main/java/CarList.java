public interface CarList extends CarCollection {
    Car get(int index);
    // возвращает машину по индексу
    boolean add(Car car);
    boolean add(Car car, int index);
    boolean remove (Car car);
    boolean removeAt (int index);
    //сколько автомобилей
    int size();
    //удаляет все автомобили из коллекции
    void clear();

}
