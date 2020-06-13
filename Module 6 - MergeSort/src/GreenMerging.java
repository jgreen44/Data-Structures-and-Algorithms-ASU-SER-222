import java.util.Random;

/**
 * Implements various merge style algorithms.
 * <p>
 * Completion time: (your completion time)
 *
 * @author Jason Green, jgreen44@asu.edu, Acuna, Sedgewick and Wayne
 * @verison 1.0
 */

public class GreenMerging {

    /**
     * Entry point for sample output.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<Comparable> q1 = new ListQueue<>();
        q1.enqueue("T");
        q1.enqueue("R");
        q1.enqueue("O");
        q1.enqueue("L");
        q1.enqueue("E");
        Queue<Comparable> q2 = new ListQueue<>();
        q2.enqueue("X");
        q2.enqueue("S");
        q2.enqueue("P");
        q2.enqueue("M");
        q2.enqueue("E");
        q2.enqueue("A");
        Queue<Comparable> q3 = new ListQueue<Comparable>();
        q3.enqueue(20);
        q3.enqueue(17);
        q3.enqueue(15);
        q3.enqueue(12);
        q3.enqueue(5);
        Queue<Comparable> q4 = new ListQueue<>();
        q4.enqueue(18);
        q4.enqueue(16);
        q4.enqueue(13);
        q4.enqueue(12);
        q4.enqueue(4);
        q4.enqueue(1);

        //Q1 - sample test cases
        Queue<Comparable> merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue<Comparable> merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());

        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);

        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);

        shuffle(b);
        show(b);
    }

    public static Queue<Comparable> mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
        Queue<Comparable> queue = new ListQueue<Comparable>();

        do{
            if(less(q1.front(), q2.front())){
                queue.enqueue(q2.dequeue());
            }else{
                queue.enqueue(q1.dequeue());
            }

        }while(!q1.isEmpty() && !q2.isEmpty());

        while(!q1.isEmpty()){
            queue.enqueue(q1.dequeue());
        }

        while(!q2.isEmpty()){
            queue.enqueue(q2.dequeue());
        }

        return queue;


    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = mergesort(a);
    }

    public static Comparable[] mergesort(Comparable[] a){
        assert isSorted(a);
        int middle = a.length / 2;
        Comparable[] auxFirst = new Comparable[middle];
        Comparable[] auxSecond = new Comparable[a.length - middle];

        for (int i = 0; i < middle; i++) {
            auxFirst[i] = a[i];
        }
        int j = 0;
        for (int i = middle; i < a.length; i++) {
            auxSecond[j] = a[i];
            j++;
        }

            if(a.length > 2) {
                auxFirst = mergesort(auxFirst);
                auxSecond = mergesort(auxSecond);
            }

            a = merge(auxFirst, auxSecond);

        return a;


    }
//
    public static Comparable[] merge(Comparable[] a, Comparable[] b){
        Comparable[] merged = new Comparable[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        assert isSorted(a);
        do{
            if(i < a.length && j < b.length){
                if(less(a[i], b[j])){
                    merged[k] = a[i];
                    i++; k++;
                }else if((less(b[j], a[i]))){
                    merged[k] = b[j];
                    j++; k++;
                }else{
                    merged[k] = a[i];
                    i++; k++;
                    merged[k] = b[j];
                    j++; k++;
                }
            }else if(i < a.length){
                merged[k] = a[i];
                i++;
            }else if(j < b.length){
                merged[k] = b[j];
                j++;
            }
        }while(i < a.length || j < b.length);

        return merged;
    }

    public static void shuffle(Object[] a) {
       shuffle(a, 0, a.length - 1);
    }

    public static void shuffle(Object[] a, int first, int last){
        int randInt = new Random().nextInt(a.length);
        int middle = (first + last) / 2;

        Object mid = a[middle];
        a[middle] = a[randInt];
        a[randInt] = mid;

        if(first < last){
            shuffle(a, first, middle - 1);
            shuffle(a, middle + 1, last);
        }

    }

    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }

    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;

        return true;
    }
}