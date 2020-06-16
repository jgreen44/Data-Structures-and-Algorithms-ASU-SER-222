/**
 * Ordered symbol table interface.
 * 
 * @author Sedgewick and Wayne, Acuna
 * @param <Key> search key
 * @param <Value> return type
 */
public interface OrderedSymbolTable<Key, Value> extends SymbolTable<Key, Value> {
    
    //smallest key
    Key min();
    
    //largest key
    Key max();
    
    //largest key less than or equal to key
    Key floor(Key key);
    
    //smallest key greated than or equal to key
    Key ceiling(Key key);
    
    //number of keys less than key
    int rank(Key key);
    
    //key of rank k
    Key select(int k);
    
    //delete smallest key
    void deleteMin();
    
    //delete largest key
    void deleteMax();
    
    //number of keys in [lo..hi]
    int size(Key lo, Key hi);
    
    //keys in [lo..hi] in sorted order
    Iterable<Key> keys(Key lo, Key hi);
}