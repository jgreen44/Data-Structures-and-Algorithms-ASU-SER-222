import java.util.LinkedList;

public class TwoProbeChainHT<Key, Value> implements SymbolTable<Key, Value> {

    private int N;
    private int M;
    private LinkedList<TwoProbeChain>[] st;

    public static class TwoProbeChain<Key, Val> {
        private final Key key;
        private Val value;

        public TwoProbeChain(Key key, Val value) {
            this.key = key;
            this.value = value;
        }

    }

    public TwoProbeChainHT() {
        this(997);
    }

    public TwoProbeChainHT(int size) {
        this.M = size;
        this.N = 0;

        st = (LinkedList<TwoProbeChain>[]) new LinkedList[M];
        for (int i = 0; i < M; i++) {
            st[i] = new LinkedList<TwoProbeChain>();
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
        LinkedList<TwoProbeChain> smallLinkedList;
        for (int i = 0; i < st[hash(key)].size(); i++) {
            if (st[hash(key)].get(i).key.equals(key)) {
                st[hash(key)].get(i).value = val;

            }
        }

        for (int i = 0; i < st[hash2(key)].size(); i++) {
            if (st[hash2(key)].get(i).key.equals(key)) {
                st[hash2(key)].get(i).value = val;
            }

        }
        smallLinkedList(key).add(new TwoProbeChain<>(key, val));
        N++;
    }

    private LinkedList<TwoProbeChain> smallLinkedList(Key key) {
        if (st[hash(key)].size() < st[hash2(key)].size()) {
            return st[hash2(key)];
        }
        return st[hash(key)];
    }

    @Override
    public Value get(Key key) {
        for (int i = 0; i < st[hash(key)].size(); i++) {
            if (st[hash(key)].get(i).equals(key)) {
                return (Value) st[hash(key)].get(i).value;
            }
        }

        for (int i = 0; i < st[hash2(key)].size(); i++) {
            if (st[hash2(key)].get(i).equals(key)) {
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
            if (st[hash2(key)].get(i).equals(key)) {
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
