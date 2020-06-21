import java.util.LinkedList;

public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {

    private int N;
    private final int M;
    private final LinearProbe<Key, Value>[] addContents;

    public static class LinearProbe<Key, Val> {
        private final Key key;
        private Val value;

        public LinearProbe(Key key, Val value) {
            this.key = key;
            this.value = value;
        }
    }

    public LinearProbingHT() {
        this(997);
    }

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
        for (int j = hash(key); addContents[i] != null; i = i + 1) {
            if (addContents[i].key.equals(key)) {
                addContents[i].value = val;
            }
        }
        addContents[i] = new LinearProbe<>(key, val);
        N++;

    }

    @Override
    public Value get(Key key) {
        for (int i = hash(key); addContents[i] != null; i = i + 1) {
            if (addContents[i].key.equals(key)) {
                return addContents[i].value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        for (int i = hash(key); addContents[i] != null; i = i + 1) {
            for (int j = i + 1; addContents[i] != null ; j = j + 1) {
                addContents[i] = addContents[j];
                i = i + 1;
            }
            addContents[i] = null;
            N--;
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
