import java.util.ArrayList;

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {
    private E data;
    private BinaryTreeNode<E> Parent;
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;
    public int size;
    private BinaryTreeNode<E> root;
    public LinkedBinaryTreeNode(E data) {
        if(root==null){
            root = this;
        }
        else{
            root=getParent().getRoot();
        }
        setData(data);
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    @Override
    public BinaryTreeNode<E> getParent() {
       return getParentHelper(root,this);

    }


    public BinaryTreeNode getParentHelper(BinaryTreeNode N,BinaryTreeNode p){
        if (getRoot()==this || N==null){
            return null;
        }
        else{
            if(N.getLeft()==p || N.getRight()==p)
                return N;
            else {
                BinaryTreeNode r=getParentHelper(N.getRight(),p);
                BinaryTreeNode l=getParentHelper(N.getLeft(),p);
                if (r!=null){
                    return l;
                }
                }
            }
        }
    @Override
    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BinaryTreeNode<E> child) {
        left = child;
    }

    @Override
    public BinaryTreeNode<E> getRight() {
        return right;
    }

    @Override
    public void setRight(BinaryTreeNode<E> child) {
        right = child;
    }

    @Override
    public boolean isParent() {
        return (right != null || left != null);
    }

    @Override
    public boolean isLeaf() {
        return (right == null && left == null);
    }

    @Override
    public boolean hasLeftChild() {
        return left != null;
    }

    @Override
    public boolean hasRightChild() {
        return right != null;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void removeFromParent() {
        if (getParent().getLeft() == this) {
            BinaryTreeNode<E> b = this;
            while (!b.isLeaf()) {
                b = b.getRight();
            }
            b.setLeft(getLeft());
            getParent().setLeft(b);
        } else {
            BinaryTreeNode<E> b = this;
            while (!b.isLeaf()) {
                b = b.getLeft();
            }
            b.setRight(getRight());
            getParent().setRight(b);
        }
    }

    @Override
    public ArrayList<BinaryTreeNode<E>> pathTo(BinaryTreeNode<E> descendant) {
        return null;
    }

    @Override
    public ArrayList<BinaryTreeNode<E>> pathFrom(BinaryTreeNode<E> ancestor) {
        return null;
    }

    @Override
    public void traversePreorder(Visitor visitor) {
        if
        traversePreorder();
    }
    private static void preorder(BinaryTreeNode n, Visitor v)

    {

        if (n == null) { return; }

        v.visit(n.getLeft);
        preorder(c, v); }

    }
    @Override
    public void traversePostorder(Visitor visitor) {

    }

    @Override
    public void traverseInorder(Visitor visitor) {

    }
}
