import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayQueue<E extends Cloneable> implements Queue<E> {
    private final E[] elements;
    private final int cap;
    private int front = 0;
    private int rear = 0;
    private int frontLoops = 0;
    private int rearLoops = 0;


    public ArrayQueue(int cap) {
        if (cap < 0)
            throw new NegativeCapacityException();
        this.cap = cap;
        this.elements = (E[]) new Cloneable[cap];
    }


    public void enqueue(Cloneable element) {
        if (size() == cap) {
            throw new QueueOverflowException();
        }
        this.elements[rear] = (E) element;
        if(rear == cap - 1) rearLoops++;
        rear = (rear + 1) % cap;
    }

    public E dequeue() {
        E element = peek();
        this.elements[front] = null;
        if(front == cap - 1) frontLoops++;
        front = (front + 1) % cap;
        return element;
    }

    public E peek() {
        if (size() == 0)
            throw new EmptyQueueException();
        return this.elements[front];
    }

    public int size() {
        if (front < rear || (front == rear && frontLoops == rearLoops))
            return rear - front;
        return rear + cap - front;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public ArrayQueue<E> clone() {
        try {
            super.clone();
            ArrayQueue<E> arrayQueue = new ArrayQueue<>(cap);
            for (Object element : this) {
                if (element == null) {
                    continue;
                }

                Method method = element.getClass().getMethod("clone");
                arrayQueue.enqueue((Cloneable) (method.invoke(element)));
            }
            return arrayQueue;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(elements, front, size());
    }

}
