import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayQueueIterator<E> implements Iterator<E> {

    private int front;
    private int size;
    private int index = 0;
    private E[] elements;

    public ArrayQueueIterator(E[] elements, int front, int size) {
        this.front = front;
        this.elements = elements;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public E next() {
        if (!hasNext())
            return null;
        E element = elements[(index + front) % elements.length];
        index++;
        return element;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
