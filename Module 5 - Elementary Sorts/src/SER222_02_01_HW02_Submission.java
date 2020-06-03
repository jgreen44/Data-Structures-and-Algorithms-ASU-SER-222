/**
 * An interface that defines a student's solution to the Section 02.01
 * programming homework. Contains methods that make up the structural core of a
 * sorting algorithm benchmarking tool.
 * 
 * @author Ruben Acuna, Robert Sedgewick
 * @version 1.0
 */
public interface SER222_02_01_HW02_Submission {
    
    /**
    * Generates an array of integers where half the data is 0s, half 1s.
    
     * @param size number of elements in the array.
     * @return generated test set.
    */
    public Integer[] generateTestDataBinary(int size);
    
    /**
     * Generates an array of integers where half the data is 0s, half the
     * remainder is 1s, half the reminder is 2s, half the reminder is 3s, and so
     * forth. 
     * 
     * @param size number of elements in the array.
     * @return generated test set.
     */
    public Integer[] generateTestDataHalfs(int size);
    
    /**
     * Generates an array of integers where half the data is 0s, and half random
     * int values.
     * @param size
     * @return 
     */
    public Integer[] generateTestDataHalfRandom(int size);
    
    /**
     * Computes the double formula value for two run times.
     * 
     * @param t1 first time
     * @param t2 second time
     * @return b value
     */
    public double computeDoublingFormula(double t1, double t2);
    
    /**
     * Computes an empirical b value for insertion sort by running it on a pair
     * of inputs and using the doubling formula.
     * 
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * @return b value
     */
    public double benchmarkInsertionSort(Integer[] small, Integer[] large);
    
    /**
     * Computes an empirical b value for shellsort sort by running it on a pair
     * of inputs and using the doubling formula.
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * 
     * @return b value
     */
    public double benchmarkShellsort(Integer[] small, Integer[] large);
    
    /**
     * Runs the two sorting algorithms on the three types of test data to
     * produce six different b values. B values are displayed to the user.
     * 
     * @param size size of benchmark array. to be doubled later.
     */
    public void runBenchmarks(int size);
}