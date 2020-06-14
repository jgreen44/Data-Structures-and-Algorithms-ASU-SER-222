public class main {

    public static void main(String[] args) {
        int [] x = {0, 0, 0};
        int sum = 0;
        sum = lastZero(x);
        System.out.println(sum);
    }

    public static int lastZero (int[] x)
    {
        //Effects: if x==null throw NullPointerException
        // else return the index of the LAST 0 in x.
        // Return -1 if 0 does not occur in x
        for (int i=x.length - 1; i >= 0; i--)
        {
            if (x[i] == 0)
            {
                return i;
            }
        }
        return -1;
    }
}
