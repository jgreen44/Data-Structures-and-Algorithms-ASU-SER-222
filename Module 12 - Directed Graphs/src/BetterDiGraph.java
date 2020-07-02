import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * The type Better di graph.
 */
public class BetterDiGraph implements EditableDiGraph {

    private int vertex;
    private int edge;
    private HashMap<Integer, LinkedList<Integer>> hashLink;

    /**
     * Instantiates a new Better di graph.
     */
    public BetterDiGraph(){
        this.vertex = 0;
        this.edge = 0;
        this.hashLink = new HashMap<>();
    }

    @Override
    public void addEdge(int v, int w) {
        if(!hashLink.containsKey(v)){
            addVertex(v);
        }

        if(!hashLink.containsKey(w)){
            addVertex(w);
        }

        hashLink.get(v).push(w);
        edge++;

    }

    @Override
    public void addVertex(int v) {
        if(!hashLink.containsKey(v)){
            hashLink.put(v, new LinkedList<>());
            vertex++;
        }
    }

    @Override
    public Iterable<Integer> getAdj(int v) {
        return hashLink.get(vertex);
    }

    @Override
    public int getEdgeCount() {
        return this.edge;
    }

    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        if(!hashLink.containsKey(v)){
            throw new NoSuchElementException();
        }
        int counter = 0;
        for (int i : vertices()) {
            if(i != v){
                if(hashLink.get(i).contains(v)){
                    counter++;
                }
            }
        }
    return counter;
    }

    @Override
    public int getVertexCount() {
       return this.vertex;
    }

    @Override
    public void removeEdge(int v, int w) {
        if(hashLink.containsKey(v)) {
            LinkedList<Integer> remove = hashLink.get(v);
            if (remove.contains(w)) {
                remove.remove((Integer) w);
                edge--;
            }
        }
    }

    @Override
    public void removeVertex(int v) {
        if(hashLink.containsKey(v)){
            for(Integer i : hashLink.keySet()){
                if(i != v){
                    if(hashLink.get(i).contains(v)){
                        removeEdge(i, v);
                    }
                }
            }
        }
        hashLink.remove(v);
        vertex--;
    }

    @Override
    public Iterable<Integer> vertices() {
       return hashLink.keySet();
    }
}
