/**
 * QueueADT defines the interface to a queue collection.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public interface QueueInterface<T>
{
    /**  
     * Adds one element to the rear of this queue. 
     * @param element  the element to be added to the rear of the queue  
     */
    public void enqueue(T element) throws QueueFullException;

    /**
     * Removes and returns the element at the front of this queue.
     * @return the element at the front of the queue
     */
    public T dequeue() throws QueueEmptyException;

    /**  
     * Returns without removing the element at the front of this queue.
     * @return the first element in the queue
     */
    public T peek() throws QueueEmptyException;
   
    /**  
     * Returns true if this queue contains no elements.
     * @return true if this queue is empty
     */
    public boolean isEmpty();

    /**  
     * Returns true if this queue contains the maximum number of elements.
     * @return true if this queue is full
     */
    public boolean isFull();

    /**  
     * Returns the number of elements in this queue. 
     * @return the integer representation of the size of the queue
     */
    public int size();

    /**  
     * Returns a string representation of this queue. 
     * @return the string representation of the queue
     */
    public String toString();
}
