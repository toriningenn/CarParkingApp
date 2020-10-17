public interface CarList {
    Car get(int index);
    // возвращает машину по индексу
    void add(Car car);
    boolean remove (Car car);
    boolean removeAt (int index);
    //сколько автомобилей
    int size();
    //удаляет все автомобили из коллекции
    void clear();

}
