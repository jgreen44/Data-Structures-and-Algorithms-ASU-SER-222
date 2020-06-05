import java.util.NoSuchElementException;

/**
 * A simple queue implementation. Uses list as internal representation. All 
 * operations are O(1).
 * 
 * @author Acuna
 * @version 1.0
 * @param <Item> item type
 */
public class ListQueue<Item> implements Queue<Item> {
    LinearNode<Item> tail; //back
    LinearNode<Item> head; //front
    private int count;

    public ListQueue() {
        tail = null;
        count = 0;
    } 

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void enqueue(Item item) {
     LinearNode newNode = new LinearNode(item);

        if(isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        
        tail = newNode;
        count++;
    }

    @Override
    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();

        Item result = head.getElement();
        head = head.getNext();
        count--;
        
        if(isEmpty())
            tail = null;

        return result;
    }

    @Override
    public Item front() {
        if(isEmpty())
            throw new NoSuchElementException();

        return head.getElement();
    }
    
    @Override
    public String toString()
    {
        LinearNode iter = head;
        String result = "";
        
        while(iter != null) {
            result = iter.getElement() + " " + result;
            iter = iter.getNext();
        }
            
        return result + "(front)";
    }

    @Override
    public int size() {
        return count;
    }
}