import java.util.HashMap;
import java.util.LinkedList;

/**
 * The type Intuitive topological.
 */
public class IntuitiveTopological implements TopologicalSort {

    private Iterable<Integer> order;


    /**
     * Instantiates a new Intuitive topological.
     *
     * @param betterDiGraph the better di graph
     */
    public IntuitiveTopological(BetterDiGraph betterDiGraph) {
        topOrder(betterDiGraph);
    }


    private void topOrder(BetterDiGraph betterDiGraph) {
        LinkedList<Integer> topOrder = new LinkedList<Integer>();
        while (betterDiGraph.getVertexCount() > 0) {
            HashMap<Integer, Integer> hashOrder = new HashMap<Integer, Integer>();

            for (Integer i : betterDiGraph.vertices()) {
                hashOrder.put(i, betterDiGraph.getIndegree(i));
            }

            for (Integer i : hashOrder.keySet()) {
                if (hashOrder.get(i) == 0) {
                    topOrder.add(i);
                    betterDiGraph.removeVertex(i);
                }
            }
        }
        order = topOrder;
    }

    @Override
    public Iterable<Integer> order() {
        return order;
    }

    @Override
    public boolean isDAG() {
        return false;
    }
}
