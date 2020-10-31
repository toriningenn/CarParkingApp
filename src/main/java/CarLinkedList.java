public class CarLinkedList implements CarList {

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(Car car) {
        if (size == 0) {
            Node node = new Node(null, car, null);
            first = node;
            last = node;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
    }

    @Override
    public void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        //вставляем в конец
        if (index == size) {
            add(car);
            return; //выйти из метода void!!!
        }
        //индекс в пределах коллекции, но не самый последний
        //получаем ссылки на соседние элементы
        Node nodeNext = getNode(index); //5
        Node nodePrevious = nodeNext.previous; //4
        Node newNode = new Node(nodePrevious, car, nodeNext); //создаём новый нод
        nodeNext.previous = newNode;
        if (nodePrevious != null) {
            nodePrevious.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    //возвращает нод по индексу
    private Node getNode(int index) {
        if (index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next; //если бы обращаемся к индексу 1, тогда цикл выполнится 1 раз
        }
        return node;
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if(node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNode(index);
        Node nextNode = node.next;
        Node previousNode = node.previous; // создаём, даже если null но добавляем проверку
        if (nextNode != null) {
            nextNode.previous = previousNode;
        } else {
            last = previousNode;
        }
        if (previousNode != null) {
            previousNode.next = nextNode;
        } else {
            first = nextNode;
        }

        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
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


