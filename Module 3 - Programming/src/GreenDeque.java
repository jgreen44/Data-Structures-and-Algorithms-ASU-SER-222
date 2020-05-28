import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 * 
 * @author Jason Green, jgreen44@asu.edu
 * @version 1.0
 */


public class GreenDeque<Item> implements Deque<Item> {

    //
    private Node head;
    private Node tail;
    private int length;

    // Node class
    class Node{
        Item data;
        Node previous;
        Node next;

        Node(Item element){
            this.data = element;
            this.previous = null;
            this.next = null;
        }
    }

    // constructor
    public GreenDeque() {
//        this.head = null;
//        this.tail = null;
//        this.length = 0;
    }

    @Override
    public void enqueueFront(Item element) {
        Node node = new Node(element);
        node.next = this.head;
        if(isEmpty()){
            this.tail = node;
        }else{
            this.head.previous = node;
        }
        this.head = node;

        length++;
    }

    @Override
    public void enqueueBack(Item element) {
        Node node = new Node(element);

        node.previous = this.tail;
        if(isEmpty()){
            this.head = node;
        }else{
            this.tail.next = node;
        }
        this.tail = node;
        length++;
    }

    @Override
    public Item dequeueFront() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item item = this.head.data;
        this.head = this.head.next;

        if(this.head == null){
            this.tail = null;
        }else{
            this.head.previous = null;
            length--;

        }
        return item;
    }

    @Override
    public Item dequeueBack() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item item = this.tail.data;
        this.tail = this.tail.previous;

        if(this.tail == null){
            this.head = null;
        }else{
            this.tail.next = null;
            length--;
        }
        return item;
    }

    @Override
    public Item first() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    @Override
    public Item last() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return this.tail.data;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node node = this.tail; node != null; node = node.previous) {
            str.append(node.data.toString()).append(" ");
        }
        return str.toString();
    }

    /**
     * Program entry point for deque. 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        GreenDeque<Integer> deque = new GreenDeque<>();
        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
        System.out.println(deque.dequeueFront());        
        deque.enqueueFront(1);
        deque.enqueueFront(11);                         
        deque.enqueueFront(3);                 
        deque.enqueueFront(5);         
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());        
        System.out.println(deque.last());                
        deque.dequeueFront();
        deque.dequeueFront();        
        System.out.println(deque.first());        
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());            
    }
} 