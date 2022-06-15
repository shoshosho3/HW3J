import java.util.Iterator;

/**
 * This class represents an iterator of elements in queue
 *
 * @param <E> class of elements in queue
 */
public class ArrayQueueIterator<E> implements Iterator<E> {

    private final int front; // index of top of queue
    private final int size; // number of elements currently in queue
    private int index = 0; // number of iterations done
    private final E[] elements; // queue elements

    /**
     * constructor
     *
     * @param elements array of elements in queue
     * @param front    index of current first elements in queue in the array
     * @param size     size of queue
     */
    public ArrayQueueIterator(E[] elements, int front, int size) {
        this.front = front;
        this.elements = elements;
        this.size = size;
    }

    /**
     * This function checks if index in queue is in bounds
     *
     * @return true if index in queue is in bounds false otherwise
     */
    @Override
    public boolean hasNext() {
        return index < size;
    }

    /**
     * This function returns next element in queue
     *
     * @return next element in queue
     */
    @Override
    public E next() {
        if (!hasNext())
            return null;
        E element = elements[(index + front) % elements.length];
        index++;
        return element;
    }

}
