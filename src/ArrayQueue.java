import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * This class represents a generic queue using an array
 *
 * @param <E> class of items in queue
 */
public class ArrayQueue<E extends Cloneable> implements Queue<E> {
    private E[] elements;
    private final int cap;
    private int front = 0;
    private int rear = 0;
    private int frontLoops = 0;
    private int rearLoops = 0;

    /**
     * constructor
     *
     * @param cap int of maximum size of array
     */
    public ArrayQueue(int cap) {
        if (cap < 0)
            throw new NegativeCapacityException();
        this.cap = cap;
        this.elements = (E[]) new Cloneable[cap];
    }


    /**
     * This function inserts an element to emd of queue
     *
     * @param element Cloneable element being inserted into queue
     */
    public void enqueue(E element) {
        if (size() == cap) {
            throw new QueueOverflowException();
        }
        this.elements[rear] = element;
        if (rear == cap - 1) rearLoops++;
        rear = (rear + 1) % cap;
    }

    /**
     * This function removes the top element in queue and returns it
     *
     * @return the top element in queue
     */
    public E dequeue() {
        E element = peek();
        this.elements[front] = null;
        if (front == cap - 1) frontLoops++;
        front = (front + 1) % cap;
        return element;
    }

    /**
     * This function returns the top element in queue
     *
     * @return the top element in queue
     */
    public E peek() {
        if (size() == 0)
            throw new EmptyQueueException();
        return this.elements[front];
    }

    /**
     * This function calculates size of queue and returns it
     *
     * @return size of queue
     */
    public int size() {
        if (front < rear || (front == rear && frontLoops == rearLoops))
            return rear - front;
        return rear + cap - front;
    }

    /**
     * This function checks if queue is empty
     *
     * @return true if queue is empty false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This function creates a clone of this object with cloned elements
     *
     * @return clone of this object
     */
    @Override
    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> arrayQueue = (ArrayQueue<E>) super.clone();
            arrayQueue.elements = arrayQueue.elements.clone();
            for (int i = 0; i < arrayQueue.elements.length; i++) {
                if (arrayQueue.elements[i] == null) continue;
                Method method = arrayQueue.elements[i].getClass().getMethod("clone");
                arrayQueue.elements[i] = ((E) method.invoke(arrayQueue.elements[i]));
            }
            return arrayQueue;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This function returns iterator(enables foreach)
     *
     * @return iterator of this object
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(elements, front, size());
    }

}
