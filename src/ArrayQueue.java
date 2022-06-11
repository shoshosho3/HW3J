import java.util.Iterator;

public class ArrayQueue<E extends Cloneable> implements Queue {
    private E[] elements;
    private final int cap;
    private int front = 0;
    private int rear = 0;


    public ArrayQueue(int cap) {
        if (cap < 0)
            throw new NegativeCapacityException();
        this.cap = cap;
        this.elements = (E[])new Cloneable[cap];
    }


    public void enqueue(Cloneable element){
        if (size() == cap) {
            System.out.println(size());
            System.out.println(cap);
            System.out.println(this);
            throw new QueueOverflowException();
        }
        this.elements[rear] = (E) element;
        rear = (rear + 1) % cap;
    }

    public E dequeue() {
        E element = peek();
        this.elements[front] = null;
        front = (front + 1) % cap;
        return element;
    }

    public E peek() {
        if (size() == 0)
            throw new EmptyQueueException();
        E element = (E) this.elements[front];
        return element;
    }

    public int size() {
        if (front <= rear)
            return rear - front;
        return cap - (front - rear);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public ArrayQueue clone() {
        ArrayQueue<E> arrayQueue;
        try {
            arrayQueue = new ArrayQueue<E>(cap);
            for (Object element: this) {
                if (element == null)
                {
                    arrayQueue.enqueue(null);
                    continue;
                }


                // re-check
                arrayQueue.enqueue( ((MyCloneable)element).clone() );
            }
        } catch (Exception e) {
            return null;
        }
        return arrayQueue;
    }

    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator<E>((E[]) elements, front, size());
    }

}
