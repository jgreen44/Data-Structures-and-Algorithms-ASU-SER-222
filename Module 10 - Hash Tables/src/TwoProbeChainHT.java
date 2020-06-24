import java.util.LinkedList;

/**
 * The type Two probe chain ht.
 *
 * @param <Key>   the type parameter
 * @param <Value> the type parameter
 */
public class TwoProbeChainHT<Key, Value> implements SymbolTable<Key, Value> {

    private int N; // number of key-value pairs
    private int M; // hash table size
    private LinkedList<Entry>[] st;

    /**
     * The type Entry.
     *
     * @param <Key> the type parameter
     * @param <Val> the type parameter
     */
    public static class Entry<Key, Val> {
        private final Key key;
        private Val value;

        /**
         * Instantiates a new Entry.
         *
         * @param key   the key
         * @param value the value
         */
        public Entry(Key key, Val value) {
            this.key = key;
            this.value = value;
        }

    }

    /**
     * Instantiates a new Two probe chain ht.
     */
    public TwoProbeChainHT() {
        this(997);
    }

    /**
     * Instantiates a new Two probe chain ht.
     *
     * @param size the size
     */
    public TwoProbeChainHT(int size) {
        this.M = size;
        this.N = 0;

        st = (LinkedList<Entry>[]) new LinkedList[M];
        for (int i = 0; i < M; i++) {
            st[i] = new LinkedList<>();
        }

    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }


    @Override
    public void put(Key key, Value val) {
        for (int i = 0; i < st[hash(key)].size(); i++) {
            if (st[hash(key)].get(i).key.equals(key)) {
                st[hash(key)].get(i).value = val;
               return;
            }
        }

        for (int i = 0; i < st[hash2(key)].size(); i++) {
            if (st[hash2(key)].get(i).key.equals(key)) {
                st[hash2(key)].get(i).value = val;
               return;
            }
        }

        if (st[hash(key)].size() < st[hash2(key)].size()) {
            st[hash(key)].add(new Entry(key, val));
        } else {
            st[hash2(key)].add(new Entry(key, val));
        }
        N++;

    }



    @Override
    public Value get(Key key) {
        for (int i = 0; i < st[hash(key)].size(); i++) {
            if (st[hash(key)].get(i).key.equals(key)) {
                return (Value) st[hash(key)].get(i).value;
            }
        }

        for (int i = 0; i < st[hash2(key)].size(); i++) {
            if (st[hash2(key)].get(i).key.equals(key)) {
                return (Value) st[hash2(key)].get(i).value;
            }

        }
        return null;

    }

    @Override
    public void delete(Key key) {
        for (int i = 0; i < st[hash(key)].size(); i++) {
            if (st[hash(key)].get(i).key.equals(key)) {
                st[hash(key)].remove(i);
                N--;
            }
        }

        for (int i = 0; i < st[hash2(key)].size(); i++) {
            if (st[hash2(key)].get(i).key.equals(key)) {
                st[hash2(key)].remove(i);
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
            for (int j = 0; j < st[i].size(); j++) {
                list.add((Key) st[i].get(j).key);
            }
        }
        return list;
    }
}
