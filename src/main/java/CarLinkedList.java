public class CarLinkedList implements CarList {

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return null;
    }

    @Override
    public void add(Car car) {
        if (size == 0) {
            Node node = new Node(null,car,null);
            first = node;
            last = node;
        } else {
            Node secondLast = last;
            last = new Node(secondLast,car,null);
            secondLast.next = last;
        }
        size++;
    }

    @Override
    public void add(Car car, int index) {

    }

    @Override
    public boolean remove(Car car) {
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
    }

    private static class Node {
        private Node previous;
        private Car value;
        private Node next;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}


