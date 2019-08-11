import java.lang.reflect.Array;

public class Queue<T> implements QueueInterface<T> {
    private T[] arr;
    private int front, rear, count;

    public Queue(int sizeLimit) {
        front = rear = count = 0;
        arr = (T[]) new Object[sizeLimit - 1];
    }

    @Override
    public void enqueue(T element) throws QueueFullException {
        if (size() == arr.length) {
            throw  new QueueFullException();
        }
        arr[rear] = element;
        rear = (rear+1) % arr.length;
    }


    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        T temp = arr[front];
        arr[front] = null;
        front=(front+1) % arr.length;
        return temp;
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return arr[front];
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean isFull() {
        if(front==0 && rear==size()-1)
        {
            return true;
        }
        else if (rear==front-1)
        {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if(rear<front){
            return rear+front;
        }
        return rear-front;
    }
}
