public class thing {
    public int count(BinaryTree b){
        if (b==null){
            return 0;
        }
        if(b.isLeaf()){
            return 1;
        }
        return  count(b.getLeftChild())+count(b.getRightChild());
    }
}
