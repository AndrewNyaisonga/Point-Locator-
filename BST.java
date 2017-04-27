/**
 * Created by andrewnyaisonga on 3/30/17.
 */
public class BST {

    private MyTreeNode root;
    private final static int COUNTERCLOCKWISE = 1;
    private final static int CLOCKWISE = -1;
    private final static int COLINEAR = 0;    //Same line

    
    public void insert(Segment line) {
        if (root == null) {
            root = new MyTreeNode(line);
        } else {
            root = insert(line, root);
        }
    }

    public MyTreeNode insert(Segment line, MyTreeNode n) {
        if (Segment.interseptionFinder(line, n.line)) {
            if (n.leftchild != null) {
                n.leftchild = insert(line, n.leftchild);
            }
            else {
                n.leftchild = new MyTreeNode(line);
                if (n.rightchild != null) {
                    n.rightchild = insert(line, n.rightchild);
                }
                else {
                    n.rightchild = new MyTreeNode(line);
                }
            }
        }

        else if (ccw(line.p1, n.line.p1, n.line.p2) < 0) {
            if (n.rightchild != null){
                n.rightchild = insert(line, n.rightchild);
            }
            else
                n.rightchild = new MyTreeNode(line);
        }

        else if (ccw(line.p1, n.line.p1, n.line.p2) > 0) {
            if (n.leftchild != null){
                n.leftchild = insert(line, n.leftchild);
            }
            else
                n.leftchild = new MyTreeNode(line);
        }
        return n;
    }



    public static int ccw (Point p0, Point p1, Point p2) {
        double dx1 = Math.abs(p1.x - p0.x);
        double dy1 = Math.abs(p1.y - p0.y);
        double dx2 = Math.abs(p2.x - p0.x);
        double dy2 = Math.abs(p2.y - p0.y);
        if (dx1*dy2 > dy1*dx2) return COUNTERCLOCKWISE;
        if (dx1*dy2 < dy1*dx2) return CLOCKWISE;
        if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return CLOCKWISE;
        if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return COUNTERCLOCKWISE;
        return COLINEAR;
    }


    public void printInOrder(){
        root.printInOrder();
    }
    public MyTreeNode getNode() {
        return root;
    }
    public MyTreeNode search(MyTreeNode node, Point p1, Point p2) {
        int one = ccw(p1, node.line.p1, node.line.p2);
        int two = ccw(p2, node.line.p1, node.line.p2);

        if (one != two)
            return node;
        if (one == -1) {
            if (node.rightchild == null)
                return null;
            else
                search(node.rightchild, p1, p2);
        }
        if (one == 1) {
            if (node.leftchild==null)
                return null;
            else
                search(node.leftchild, p1, p2);
        }
        return null;
    }
}
