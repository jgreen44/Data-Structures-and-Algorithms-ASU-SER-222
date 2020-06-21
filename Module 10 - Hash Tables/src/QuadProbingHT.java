public class QuadProbingHT <Key, Value> extends LinearProbingHT<Key, Value> implements SymbolTable<Key, Value>{
    private int M;

    public QuadProbingHT(){
        this(997);
    }

    public QuadProbingHT(int size){
        super(size);
        this.M = size;
    }

    public int hash(Key key, int i){
        return ((key.hashCode() & 0x7fffffff) + i * i) % M;
    }
}
