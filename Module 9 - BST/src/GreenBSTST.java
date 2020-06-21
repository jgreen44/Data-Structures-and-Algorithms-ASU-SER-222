/**
 * A binary search tree based implementation of a symbol table.
 *
 * @author Jason Green, jgreen44@asu.edu
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class GreenBSTST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {
    private Node root;

    private class Node {
        private final Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    @Override
    public boolean contains(Key key) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
//        throw new UnsupportedOperationException("Not supported yet.");
        return size() == 0;

    }

    @Override
    public Key ceiling(Key key) {
//        throw new UnsupportedOperationException("Not supported yet.");
        Node node = ceiling(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;

        int compare = key.compareTo(node.key);

        if (compare == 0) return node;
        if (compare < 0) return ceiling(node.right, key);

        Node newNode = ceiling(node.left, key);

        return Objects.requireNonNullElse(newNode, node);
    }

    @Override
    public void deleteMax() {
//        throw new UnsupportedOperationException("Not supported yet.");
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public int size(Key lo, Key hi) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public void balance() {
//        throw new UnsupportedOperationException("Not supported yet.");
        LinkedList<Node> node = new LinkedList<Node>();
        orderNodes(node, root);
        root = balance(node, 0, size() - 1);
    }

    private void orderNodes(LinkedList<Node> node, Node x) {
        if (x == null) return;
        orderNodes(node, x.left);
        node.add(x);
        orderNodes(node, x.right);
    }

    private Node balance(LinkedList<Node> node, int start, int finish) {
        if (start > finish) return null;
        int mid = (start + finish) / 2;
        if ((start + finish) % 2 == 1) {mid++;}
        Node midNode = node.get(mid);

        midNode.left = balance(node, start, mid - 1);
        midNode.right = balance(node, mid + 1, finish);

        return midNode;
    }

    public void printLevel(Key key) {
//        throw new UnsupportedOperationException("Not supported yet.");
        Queue<Node> queue = new LinkedList<Node>();
        Node node = root;
        if (node == null) return;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare > 0) node = node.right;
            else if (compare < 0) node = node.left;
            else break;
        }
        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.val);
            if (node.right != null) queue.add(node.right);
            if (node.left != null) queue.add(node.left);

        }
    }

    public Value getFast(Key key) {
        Node node = root;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }
        return null;
    }

    public void putFast(Key key, Value value) {
        if(root == null){
            root = new Node(key, value, 1);
        }else {
            Node node = root;
            Node firstNode = null;
            while (node != null) {
                firstNode = node;
                if (key.compareTo(node.key) > 0) {
                    node = node.right;
                } else if (key.compareTo(node.key) < 0) {
                    node = node.left;
                } else {
                    node.val = value;

                }
            }
            node = root;
            while (node != null) {
                node.N = node.N + 1;
                if (key.compareTo(node.key) > 0) {
                    node = node.right;
                } else if (key.compareTo(node.key) < 0) {
                    node = node.left;
                }
            }

            Node secondNode = new Node(key, value, 1);
            if (key.compareTo(firstNode.key) < 0) {
                firstNode.left = secondNode;
            } else {
                firstNode.right = secondNode;
            }
        }
    }

    /**
     * entry point for testing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GreenBSTST<Integer, String> bst = new GreenBSTST();

        bst.putFast(10, "TEN");
        bst.putFast(3, "THREE");
        bst.putFast(1, "ONE");
        bst.putFast(5, "FIVE");
        bst.putFast(2, "TWO");
        bst.putFast(7, "SEVEN");

        System.out.println("Before balance:");
        bst.printLevel(10); //root

        System.out.println("After balance:");
        bst.balance();
        bst.printLevel(5); //root
    }
}