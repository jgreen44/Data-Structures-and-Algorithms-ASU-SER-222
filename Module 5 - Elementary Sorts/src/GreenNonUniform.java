import java.util.Random;

/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author (your name), Acuna, Sedgewick
 * @version (a version number or a date)
 */


public class GreenNonUniform implements SER222_02_01_HW02_Submission {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }



    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/
    private double smallTime;
    private double largeTime;
    static Random random = new Random(15L);

    //TODO: implement interface methods.
    public GreenNonUniform() {
        this.smallTime = 0.0d;
        this.largeTime = 0.0d;
    }

    @Override
    public Integer[] generateTestDataBinary(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
           if(i <= array.length / 2){
               array[i] = 0;
           }else{
               array[i] = 1;
           }
        }
       return array;
    }

    @Override
    public Integer[] generateTestDataHalfs(int size) {
        Integer[] array = new Integer[size];
        int middle = (size / 2);
        int index = middle;
        int count = 0;

        for (int i = 0; i < size; i++) {
            if(i == index){
                middle = (middle / 2) + (middle % 2);
                index += middle;
                count++;
            }
            array[i] = count;
        }
        return array;
    }

    @Override
    public Integer[] generateTestDataHalfRandom(int size) {
        Integer[] array = new Integer[size];
        int test;
        for (int i = 0; i < array.length; i++) {
            if(i <= array.length / 2){
                array[i] = 0;
            }else{
                array[i] =  random.nextInt();
            }
        }
        return array;
    }

    @Override
    public double computeDoublingFormula(double t1, double t2) {
        return (Math.log(t2/t1));
    }

    @Override
    public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
        Stopwatch t = new Stopwatch();
        insertionSort(small);
        this.smallTime = t.elapsedTime();

        Stopwatch t2 = new Stopwatch();
        insertionSort(large);
        this.largeTime = t2.elapsedTime();

        return computeDoublingFormula(this.smallTime, this.largeTime);

    }

    @Override
    public double benchmarkShellsort(Integer[] small, Integer[] large) {
        Stopwatch t = new Stopwatch();
        shellsort(small);
        this.smallTime = t.elapsedTime();

        Stopwatch t2 = new Stopwatch();
        shellsort(large);
        this.largeTime = t2.elapsedTime();

        return computeDoublingFormula(this.smallTime, this.largeTime);
    }

    @Override
    public void runBenchmarks(int size) {
        Integer[]  smallBinary, largeBinary, smallHalf, largeHalf, smallRandom, largeRandom;
        int dblSize = size * 2;

        smallBinary = generateTestDataBinary(size);
        largeBinary = generateTestDataBinary(dblSize);

        smallHalf = generateTestDataHalfs(size);
        largeHalf = generateTestDataHalfs(dblSize);

        smallRandom = generateTestDataHalfRandom(size);
        largeRandom = generateTestDataHalfRandom(dblSize);

        System.out.printf("%-15.15s %-15.15s  %-15.15s%n", "", "Insertion", "ShellSort");
        System.out.printf("%-15.10s  %-15.5f %-15.5f%n", "Bin" ,
                benchmarkInsertionSort(smallBinary, largeBinary) ,
                benchmarkShellsort(smallBinary, largeBinary)
        );
        System.out.printf("%-15.10s  %-15.5f %-15.5f%n", "Half" ,
                benchmarkInsertionSort(smallHalf, largeHalf) ,
                benchmarkShellsort(smallHalf, largeHalf)
        );
        System.out.printf("%-15.10s  %-15.5f %-15.5f%n", "RanInt" ,
                benchmarkInsertionSort(smallRandom, largeRandom) ,
                benchmarkShellsort(smallRandom, largeRandom)
        );



    }


//    public static int randInt(int min, int max) {
////        random.setSeed(15L);
//        return random.nextInt(max - min);
//
//    }

    public static void main(String args[]) {
        SER222_02_01_HW02_Submission me = new GreenNonUniform();
        int size = 4096 * 4;


        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);

    }
}