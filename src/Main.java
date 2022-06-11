import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Consumer;


class MyCloneable implements Cloneable {
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "A: " + num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        testPartA();
    }

    /**
     * Tests for part A.
     */
    private static void testPartA() {
        try {
            new ArrayQueue<>(-25);
        } catch (NegativeCapacityException e) {
            System.out.println("Negative capacity!");
        }

        Queue<MyCloneable> q1 = new ArrayQueue<>(5);
        ArrayQueue<MyCloneable> q2 = (ArrayQueue<MyCloneable>) q1;

        try {
            q1.peek();
        } catch (EmptyQueueException e) {
            System.out.println("The queue is empty");
        }

        try {
            q2.peek();
        } catch (QueueException e) {
            System.out.println("The queue is empty");
        }
        System.out.println();

        iterateQueue(q1, "q1");
        MyCloneable c1 = new MyCloneable(1);
        q1.enqueue(c1);
        iterateQueue(q1, "q1");
        System.out.println("Peek: " + q1.peek());
        System.out.println("Deque: " + q1.dequeue());
        System.out.println();
        iterateQueue(q1, "q1");

        MyCloneable c2 = new MyCloneable(2);
        q1.enqueue(c1);
        q1.enqueue(c2);

        iterateQueue(q2, "q2");

        ArrayQueue<MyCloneable> q3 = q2.clone();
        Queue<MyCloneable> q4 = q1.clone();

        System.out.println("Is q1 == q3? " + (q1 == q3));
        System.out.println("Is q2 == q3? " + (q2 == q3));
        System.out.println("Is q1 == q4? " + (q1 == q4));
        System.out.println("Is q2 == q4? " + (q2 == q4));
        System.out.println();
        iterateQueue(q3, "q3");

        c1.setNum(15);
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        q1.enqueue(new MyCloneable(3));
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        q1.enqueue(new MyCloneable(4));
        iterateQueue(q1, "q1");

        q1.enqueue(new MyCloneable(5));
        iterateQueue(q1, "q1");
        iterateQueue(q2, "q2");
        iterateQueue(q3, "q3");
        iterateQueue(q4, "q4");

        try {
            q1.enqueue(c1);
        } catch (QueueOverflowException e) {
            System.out.println("The queue reached its full capacity.");
        }

        try {
            q2.enqueue(c1);
        } catch (QueueException e) {
            System.out.println("The queue reached its full capacity.");
        }

        System.out.println("\nTesting of part A is over!\n\n");

    }

    /**
     * Iterates over a given queue.
     */
    private static void iterateQueue(Queue<?> q, String name) {
        System.out.println("Starts iterating " + name + "...");
        System.out.println("Queue size: " + q.size());
        System.out.println("Is empty? " + q.isEmpty());

        for (Object obj : q) {
            System.out.println(obj);
        }

        System.out.println("Done iterating");
        System.out.println("");
    }


}
