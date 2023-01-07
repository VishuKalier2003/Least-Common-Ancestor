/* You are given the root of a binary tree with unique values, and an integer start... At minute 0, an infection starts from the node with value start... Each minute, a node becomes infected if:
    * The node is currently uninfected...
    * The node is adjacent to an infected node...
Return the number of minutes needed for the entire tree to be infected... 
* Eg 1: Nodes = [1, 5, -1, 4, 9, -1, -1, 2, -1, -1, 3, 10, -1, -1, 6, -1, -1]   Infected = 3  Output = 4   Reason: It will take 4 minutes to infect the entire tree... */
import java.util.*;
public class Infection
{
    public class Node
    {
        public int data;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int val)
        {
            this.data = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    public class Binary
    {
        static int index = -1;
        public Node InsertNode(int nodes[])
        {
            index++;
            if(nodes[index] == -1)
                return null;
            Node node = new Node(nodes[index]);
            node.left = InsertNode(nodes);
            node.right = InsertNode(nodes);
            return node;
        }
        public void PrintTree(Node root)
        {
            if(root == null)
                return;
            Node temp = root;
            PrintTree(root.left);
            System.out.println("Node: "+root.data);
            if(root.left != null)
                root.parent = ParentNode(root, root.left.data);    // We check the parent of Left Child...
            if(root.right != null)
                root.parent = ParentNode(root, root.right.data);  // We check the parent of Right Child...
            if(root.left != null && root.parent != null)
            {
                System.out.println("\t"+temp.left.data+" ---> "+temp.parent.data+"\t Parent ( Present )");
                System.out.println("\t"+temp.data+" ---> "+temp.left.data+"\t Left ( Occupied )");
            }
            else
            {
                System.out.println("\t"+temp.data+"\t\t Parent Right ( Empty )");
                System.out.println("\t"+temp.data+"\t\t Left ( Empty )");
            }
            if(root.right != null && root.parent != null)
            {
                System.out.println("\t"+temp.right.data+" ---> "+temp.parent.data+"\t Parent ( Present )");
                System.out.println("\t"+temp.data+" ---> "+temp.right.data+"\t Right ( Occupied )");
            }
            else
            {
                System.out.println("\t"+temp.data+"\t\t Parent Right ( Empty )");
                System.out.println("\t"+temp.data+"\t\t Right ( Empty )");
            }
            PrintTree(root.right);
            return;
        }
        public Node ParentNode(Node root, int key)    // Important Code for finding the Parent...
        {
            if(root == null || root.data == key)
                return null;     // Base condition...
            if((root.left != null && root.left.data == key) || (root.right != null && root.right.data == key))    // If left child is desired child, then current node is the parent, similarly for the right child case...
                return root;
            Node l = ParentNode(root.left, key);    // We move in PreOrder fashion...
            if(root.left != null)
                return l;
            l = ParentNode(root.right, key);
            return l;     // Returning the parent node...
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of Nodes ( including the null nodes ) : ");
        x = sc.nextInt();
        int tree[] = new int[x];
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the data of "+(i+1)+" th Node : ");
            tree[i] = sc.nextInt();
        }
        Infection infection = new Infection();      // Superclass object created...
        Binary binary = infection.new Binary();     // Subclass object created...
        Node root = binary.InsertNode(tree);
        binary.PrintTree(root);         // Calling function...
        sc.close();
    }
}
