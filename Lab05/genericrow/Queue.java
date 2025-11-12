package Lab05.genericrow;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private void enqueue(T element) {
        if (isEmpty()) {
            head = new Node<>(element);
            tail = head;
        } else {
            tail.setNext(new Node<>(element));
            tail = tail.getNext();
        }
        count++;
    }

    private T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Node<T> node = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        count--;
        return node.getElement();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return head.getElement();
    }

    public T inspect() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        return tail.getElement();
    }

    public int count() {
        return count;
    }
}
