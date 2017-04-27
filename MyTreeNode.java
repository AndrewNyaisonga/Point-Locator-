/**
 * Created by andrewnyaisonga on 3/30/17.
 */
public class MyTreeNode {

    public Segment line;
    public MyTreeNode leftchild;
    public MyTreeNode rightchild;
    public MyTreeNode parent;
    public MyTreeNode root;

    public MyTreeNode(Segment line){
        this.line = line;
        this.leftchild = null;
        this.rightchild = null;
        this.parent = null;
    }

    public void printInOrder() {


        if (leftchild != null)
            leftchild.printInOrder();

        System.out.println(line.p1.x + " " + line.p1.y + " " + line.p2.x + " " + line.p2.y);

        if (rightchild != null)
            rightchild.printInOrder();
    }

    public int count() {
        int c = 1;
        if (leftchild != null)
            c += leftchild.count();
        if (rightchild != null)
            c += rightchild.count();
        return c;
    }


    public int externalPathLength() {
        return externalPath(root, 0);
    }

    private int externalPath(MyTreeNode node, int depth) {
        System.out.println("Okay: "+ node);
        if (node.rightchild == null && node.leftchild == null)
            return depth;
        else
            return externalPath(node.leftchild, depth+1) + externalPath(node.rightchild, depth+1);
    }
}

