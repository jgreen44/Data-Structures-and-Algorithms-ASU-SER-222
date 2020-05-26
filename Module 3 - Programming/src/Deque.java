import java.util.NoSuchElementException;

/**
 * Deque defines the interface to a deque ADT.
 *
 * @author Acuna, Lewis et al.
 * @version 1.0
 * @param <Item> contained type
 */
public interface Deque<Item>
{
    /**  
     * Adds one element to the front of this deque. 
     * @param element the element to be added to the front of the deque  
     */
    public void enqueueFront(Item element);
    
    /**  
     * Adds one element to the back of this deque. 
     * @param element the element to be added to the back of the deque  
     */
    public void enqueueBack(Item element);

    /**  
     * Removes and returns the element at the front of this deque.
     * Throws an exception if the deque is empty.
     * @return the element at the front of this deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueFront() throws NoSuchElementException;
    
    /**  
     * Removes and returns the element at the back of this deque.
     * Throw an exception if the deque is empty.
     * @return the element at the back of the deque. 
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueBack() throws NoSuchElementException;

    /**  
     * Returns, without removing, the element at the front of this deque.
     * Should throw an exception if the deque is empty.
     * @return the first element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item first() throws NoSuchElementException;
    
    /**  
     * Returns, without removing, the element at the back of this deque.
     * Should throw an exception if the deque is empty.
     * @return the last element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item last() throws NoSuchElementException;
   
    /**  
     * Returns true if this deque is empty and false otherwise.
     * @return if deque empty
     */
    public boolean isEmpty();

    /**  
     * Returns the number of elements in this deque. 
     * @return the number of elements in the deque
     */
    public int size();

    /**  
     * Returns a string representation of this deque. The back element
     * occurs first, and each element is separated by a space. If the
     * deque is empty, returns "empty".
     * @return the string representation of the deque
     */
    @Override
    public String toString();
}