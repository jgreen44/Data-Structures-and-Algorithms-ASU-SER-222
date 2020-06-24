/**
 * The type Quad probing ht.
 *
 * @param <Key>   the type parameter
 * @param <Value> the type parameter
 */
public class QuadProbingHT <Key, Value> extends LinearProbingHT<Key, Value> implements SymbolTable<Key, Value>{
    private int M;

    /**
     * Instantiates a new Quad probing ht.
     */
    public QuadProbingHT(){
        this(997);
    }

    /**
     * Instantiates a new Quad probing ht.
     *
     * @param size the size
     */
    public QuadProbingHT(int size){
        super(size);
        this.M = size;
    }

    /**
     * Hash int.
     *
     * @param key the key
     * @param i   the
     * @return the int
     */
    public int hash(Key key, int i){
        return ((key.hashCode() & 0x7fffffff) + i * i) % M;
    }
}
