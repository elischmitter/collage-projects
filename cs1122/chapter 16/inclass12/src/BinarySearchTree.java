/*
Eli Schmitter

Eric Chandler
Wesley Grove
 */
import java.util.PriorityQueue;
public class BinarySearchTree<E extends Comparable<E>> {
    public class Node {
        E value;
        Node leftChild;
        Node rightChild;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    private Node root = null;
    private int size = 0;

    public int size( ) {
        return size;
    }

    public boolean isEmpty( ) {
        return size == 0;
    }

    public void add(Node node, E value) {
        if ( root == null ) {
            root = new Node();
            root.setValue(value);
            size++;
            return;
        }
        if ( value.compareTo(node.getValue()) < 0 ) {
            if ( node.getLeftChild() == null ) {
                node.setLeftChild( new Node() );
                node.getLeftChild().setValue( value );
                size++;
                return;
            } else {
                add( node.getLeftChild(), value );
                return;
            }
        }
        if ( value.compareTo(node.getValue()) > 0 ) {
            if ( node.getRightChild() == null ) {
                node.setRightChild( new Node() );
                node.getRightChild().setValue( value );
                size++;
                return;
            } else {
                add( node.getRightChild(), value );
                return;
            }
        }
}

    public Node find( E value ) {
        if ( isEmpty() ) {
            return null;
        }
        return find( root, value );
    }

    private Node find( Node target, E value) {
        if ( target == null ) {
            return null;
        }
        if ( target.getValue().equals( value) ) {
            return target;
        }
        Node result = find( target.getLeftChild(), value);
        if ( result == null ) {
            result = find( target.getRightChild(), value);
        }
        return result;
    }
    public  interface Visitor{
        public void visit(BinarySearchTree.Node n);
    }
    public void preoder (Node n,Visitor v) {
        if (n==null){
            return;
        }
        v.visit(n);
        preoder(n.getLeftChild(),v);
        preoder(n.getRightChild(),v);
    }
    public void postoder (Node n,Visitor v) {
        if (n==null){
            return;
        }
        preoder(n.getLeftChild(),v);
        preoder(n.getRightChild(),v);
        v.visit(n);
    }
    public void inorder (Node n,Visitor v){
        if (n==null){
            return;
        }
        preoder(n.getLeftChild(),v);
        v.visit(n);
        preoder(n.getRightChild(),v);
    }
    public void bfs(Node n, Visitor v){
        PriorityQueue<Node> q = new PriorityQueue<>();
        while (!q.isEmpty()){
            Node node=q.poll();
           if(node.getLeftChild()!=null) q.add(node.getLeftChild());
            if(node.getRightChild()!=null) q.add(node.getRightChild());
            v.visit(node);
        }
    }
    public static void  main(String[] args){
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add(tree.root,"D");
        tree.add(tree.root,"3");
        tree.add(tree.root,"f");
        tree.add(tree.root,"c");
        tree.add(tree.root,"a");
        tree.preoder(tree.root,n-> System.out.print(n.getValue().toString()));
       System.out.println("");
        tree.postoder(tree.root,n-> System.out.print(n.getValue().toString()));
        System.out.println("");
        tree.inorder(tree.root,n-> System.out.print(n.getValue().toString()));
        tree.bfs(tree.root,n-> System.out.print(n.getValue().toString()));
    }
}
