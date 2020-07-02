import java.util.NoSuchElementException;

/**
 * Implements an editable graph with sparse vertex support.
 * 
 * @author Acuna
 */
public interface EditableDiGraph {
    
    /**
     * Adds an edge between two vertices, v and w. If vertices do not exist,
     * adds them first.
     *
     * @param v source vertex
     * @param w destination vertex
     */
    void addEdge(int v, int w);

    /**
     * Adds a vertex to the graph. Does not allow duplicate vertices.
     *
     * @param v vertex number
     */
    void addVertex(int v);

    /**
     * Returns the direct successors of a vertex v.
     *
     * @param v vertex
     * @return successors of v
     */
    Iterable<Integer> getAdj(int v);
    
    /**
     * Number of edges.
     *
     * @return edge count
     */
    int getEdgeCount();
    
    /**
     * Returns the in-degree of a vertex.
     * @param v vertex
     * @return in-degree.
     * @throws NoSuchElementException exception thrown if vertex does not exist.
     */
    int getIndegree(int v) throws NoSuchElementException;
    
    /**
     * Returns number of vertices.
     * @return vertex count
     */
    int getVertexCount();
    
    /**
     * Removes edge from graph. If vertices do not exist, does not remove edge.
     *
     * @param v source vertex
     * @param w destination vertex
     */
    void removeEdge(int v, int w);

    /**
     * Removes vertex from graph. If vertex does not exist, does not try to
     * remove it.
     *
     * @param v vertex
     */
    void removeVertex(int v);

    /**
     * Returns iterable object containing all vertices in graph.
     *
     * @return iterable object of vertices
     */
    Iterable<Integer> vertices();
}