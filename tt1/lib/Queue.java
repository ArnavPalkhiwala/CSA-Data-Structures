package tt1.lib;

import java.util.Iterator;

/**
 * Queue: custom implementation
 * @author     John Mortensen
 *
 * 1. Uses custom LinkedList of Generic type T
 * 2. Implements Iterable
 * 3. "has a" LinkedList for head and tail
 */
public class Queue<T extends Comparable<T>> implements Iterable<T> {
    LinkedList<T> head, tail;
    int size = 0;

    /**
     *  Add a new object at the end of the Queue,
     *
     * @param  data,  is the data to be inserted in the Queue.
     */
    public void add(T data) {
        // add new object to end of Queue
        LinkedList<T> tail = new LinkedList<>(data, null);

        if (head == null)  // initial condition
            this.head = this.tail = tail;
        else {  // nodes in queue
            this.tail.setNextNode(tail); // current tail points to new tail
            this.tail = tail;  // update tail
        }

        size++;

        System.out.println("Enqueued data: " + data);
        System.out.print("Words count: " + this.size + ", data: ");
        for (T t : this) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public void delete() {
        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.println("Dequeued data: " + head.getData());
        head = head.getNext();
        size--;

        System.out.print("Words count: " + this.size + ", data: ");
        for (T t : this) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public void mergeOrderedQueue(Queue<T> qq) {
        LinkedList<T> q1 = this.head;
        LinkedList<T> q2 = qq.head;

        // add q1 to q2
        while (q1 != null && q2 != null) {
            if (q1.getData().compareTo(q2.getData()) < 0) {
                LinkedList<T> temp = q1.getNext();
                LinkedList<T> q2cur = q2;

                q1.setNextNode(q2cur);
                q2cur.setNextNode(temp);

                q2 = q2.getNext();
                q1 = temp;

            } else {
                q1 = q1.getNext();
            }
        }

        if (q2 != null) {
            this.tail.setNextNode(q2);
            this.tail = qq.tail;
        }

        this.size += qq.size;

        System.out.print("Data: ");
        for (T t : this) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    /**
     *  Returns the head object.
     *
     * @return  this.head, the head object in Queue.
     */
    public LinkedList<T> getHead() {
        return this.head;
    }

    /**
     *  Returns the tail object.
     *
     * @return  this.tail, the last object in Queue
     */
    public LinkedList<T> getTail() {
        return this.tail;
    }

    /**
     *  Returns the iterator object.
     *
     * @return  this, instance of object
     */
    public Iterator<T> iterator() {
        return new QueueIterator<>(this);
    }
}

/**
 * Queue Iterator
 *
 * 1. "has a" current reference in Queue
 * 2. supports iterable required methods for next that returns a data object
 */
class QueueIterator<T extends Comparable<T>> implements Iterator<T> {
    LinkedList<T> current;  // current element in iteration

    // QueueIterator is intended to the head of the list for iteration
    public QueueIterator(Queue<T> q) {
        current = q.getHead();
    }

    // hasNext informs if next element exists
    public boolean hasNext() {
        return current != null;
    }

    // next returns data object and advances to next position in queue
    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

/**
 * Queue Manager
 * 1. "has a" Queue
 * 2. support management of Queue tasks (aka: titling, adding a list, printing)
 */
class QueueManager<T extends Comparable<T>> {
    // queue data
    private final String name; // name of queue
    private int count = 0; // number of objects in queue
    public final Queue<T> queue = new Queue<>(); // queue object

    /**
     *  Queue constructor
     *  Title with empty queue
     */
    public QueueManager(String name) {
        this.name = name;
    }

    /**
     *  Queue constructor
     *  Title with series of Arrays of Objects
     */
    public QueueManager(String name, T[]... seriesOfObjects) {
        this.name = name;
        this.addList(seriesOfObjects);
    }

    /**
     * Add a list of objects to queue
     */
    public void addList(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                this.queue.add(data);
                this.count++;
            }
    }

    /**
     * Print any array objects from queue
     */
    public void printQueue() {
        System.out.println(this.name + " count: " + count);
        System.out.print(this.name + " data: ");
        for (T data : queue)
            System.out.print(data + " ");
        System.out.println();
    }
}