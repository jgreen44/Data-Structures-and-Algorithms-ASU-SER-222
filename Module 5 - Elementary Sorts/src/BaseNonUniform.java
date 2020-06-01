/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author (your name), Acuna, Sedgewick
 * @version (a version number or a date)
 */


public class BaseNonUniform implements GreenNonUniform {
    // default constructor
    public BaseNonUniform() {
    }

    @Override
    public Integer[] generateTestDataBinary(int size) {
        return new Integer[0];
    }

    @Override
    public Integer[] generateTestDataHalfs(int size) {
        return new Integer[0];
    }

    @Override
    public Integer[] generateTestDataHalfRandom(int size) {
        return new Integer[0];
    }

    @Override
    public double computeDoublingFormula(double t1, double t2) {
        return 0;
    }

    @Override
    public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
        return 0;
    }

    @Override
    public double benchmarkShellsort(Integer[] small, Integer[] large) {
        return 0;
    }

    @Override
    public void runBenchmarks(int size) {

    }

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

    //TODO: implement interface methods.

    public static void main(String args[]) {
        GreenNonUniform me = new BaseNonUniform();
        int size = 4096;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
    }
}