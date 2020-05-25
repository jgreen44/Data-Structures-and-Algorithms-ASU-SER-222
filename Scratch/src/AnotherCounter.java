public class AnotherCounter implements IncrementCounter {

    private String id;

    public AnotherCounter(String id) {
        this.id = id;
    }

    @Override
    public void increment() {
        this.id += 1;
    }

    @Override
    public int tally() {
        return 0;
    }

    public String toString() {
        return "ID = " + this.id;
    }

    public static void main(String[] args) {
        IncrementCounter counter = new AnotherCounter("test");
        counter.increment();
        System.out.println(counter);
    }
}
