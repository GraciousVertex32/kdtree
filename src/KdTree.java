import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.LinkedList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import static edu.princeton.cs.algs4.Point2D.X_ORDER;
import static edu.princeton.cs.algs4.Point2D.Y_ORDER;
public class KdTree
{
    int size;
    boolean isempty;
    Node root;
    public KdTree()                                       // construct an empty set of points
    {
    }
    public boolean isEmpty()                      // is the set empty?
    {
        return isempty;
    }
    public int size()                         // number of points in the set
    {
        return size;
    }
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (isEmpty())
        {
            root = new Node(p, true);
            root.isred = false;
        }
        else
        {
            boolean sorted = false;
            Node current = root;
            while (!sorted) // loop until inserted
            {
                if (current.byX) // when sort by x
                {
                    if (p.x() < current.point.x()) //
                    {
                        if (current.left != null)
                        {
                            current = current.left;
                        }
                        else
                        {
                            current.left = new Node(p, false);
                        }
                    }
                    else
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            current.right = new Node(p, false);
                        }
                    }
                }
                else
                {
                    if (p.y() < current.point.y())
                    {
                        if (current.left != null)
                        {
                            current = current.left;
                        }
                        else
                        {
                            current.left = new Node(p, true);
                        }
                    }
                    else
                    {
                        if (current.right != null)
                        {
                            current = current.right;
                        }
                        else
                        {
                            current.right = new Node(p, true);
                        }
                    }
                }
            }
        }
    }
    public boolean contains(Point2D p)            // does the set contain point p?
    {
    }
    public void draw()                         // draw all points to standard draw
    {
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
    }
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
    }
    private class Node
    {
        public Node left;
        public Node right;
        public Node predecessor;                 // questionable
        public Point2D point;
        public boolean isred;
        public boolean byX;
        public Node(Point2D p, Node predecessor, boolean byx)
        {
            this.point = p;
            this.isred = true;
            this.predecessor = predecessor;
            this.byX = byx;
        }
        public Node(Point2D p, boolean byx)
        {
            this.point = p;
            this.isred = true;
            this.byX = byx;
        }
    }
    private void RotateLeft(Node leftNode)
    {
        Node rightNode = leftNode.right;
        leftNode.right = rightNode.left;
        rightNode.left = leftNode;
        leftNode.isred = rightNode.isred;
        rightNode.isred = true;
    }
    private void RotateRight(Node rightNode)
    {
        Node leftNode = rightNode.left;
        rightNode.left = leftNode.right;
        leftNode.right = rightNode;
        rightNode.isred = leftNode.isred;
        leftNode.isred = true;
    }
}
