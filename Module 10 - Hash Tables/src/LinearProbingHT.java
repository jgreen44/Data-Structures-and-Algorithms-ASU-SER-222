import java.util.LinkedList;

/**
 * The type Linear probing ht.
 *
 * @param <Key>   the type parameter
 * @param <Value> the type parameter
 */
public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {

    private int N;
    private int M;
    private final LinearProbe<Key, Value>[] addContents;

    /**
     * The type Linear probe.
     *
     * @param <Key> the type parameter
     * @param <Val> the type parameter
     */
    public static class LinearProbe<Key, Val> {
        private Key key;
        private Val value;

        /**
         * Instantiates a new Linear probe.
         *
         * @param key   the key
         * @param value the value
         */
        public LinearProbe(Key key, Val value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Instantiates a new Linear probing ht.
     */
    public LinearProbingHT() {
        this(997);
    }

    /**
     * Instantiates a new Linear probing ht.
     *
     * @param size the size
     */
    public LinearProbingHT(int size) {
        this.M = size;
        this.N = 0;
        this.addContents = new LinearProbe[M];

    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public void put(Key key, Value val) {
        int i = 0;
        for (i = hash(key); addContents[i] != null; i = (i + 1) % M) {
            if (addContents[i].key.equals(key)) {
                addContents[i].value = val;
                return;
            }
        }
        addContents[i] = new LinearProbe<Key, Value>(key, val);
        N++;

    }

    @Override
    public Value get(Key key) {
        for (int i = hash(key); addContents[i] != null; i = (i + 1) % M) {
            if (addContents[i].key.equals(key)) {
                return addContents[i].value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        for (int i = hash(key); addContents[i] != null; i = (i + 1) % M) {
            if (addContents[i].key.equals(key)){
                for (int j = i + 1; addContents[i] != null ; j = (j + 1) % M) {
                    addContents[i] = addContents[j];
                    i = (i + 1) % M;
                }
                addContents[i] = null;
                N--;
            }
        }
    }


    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
       LinkedList<Key> list = new LinkedList<Key>();
        for (int i = 0; i < M; i++) {
            if(addContents[i] != null){
                list.add(addContents[i].key);
            }
        }
        return list;
    }
}
