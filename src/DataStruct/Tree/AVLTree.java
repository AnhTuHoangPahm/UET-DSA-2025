package DataStruct.Tree;
// simple avl tree
public class AVLTree {
    static class Node {
        int data;
        int ht;
        Node left;
        Node right;
    }

    static Node newNode(int data) {
        Node t = new Node();
        t.data = data;
        // others: defaulted
        return t;
    }

    private Node root;

    public Node getRoot() { return root; }

    public AVLTree() {
         root = null;
    }


    static int heightUpdate(Node curr) { // height = max(left child's height, right child's height)
        if (curr == null) return 0;
        int lh, rh;
        lh = curr.left == null ? 0 : 1 + curr.left.ht;
        rh = curr.right == null ? 0 : 1 + curr.right.ht;
        return Math.max(lh, rh);
    }

    static int BF(Node curr) { // balance factor := lh - rh
        if (curr == null) return 0;
        int lh, rh;
        lh = curr.left == null ? 0 : 1 + curr.left.ht;
        rh = curr.right == null ? 0 : 1 + curr.right.ht;
        return lh - rh;
    }
    // if bf in[-1, 1] for every node then tree is balanced

    static Node rotateLeft(Node curr) {
        /*
         curr
            \
            tmp
            / \
           y   x
         */
        Node tmp = curr.right;
        curr.right = tmp.left;
        tmp.left = curr;
        curr.ht = heightUpdate(curr);
        tmp.ht = heightUpdate(tmp);
        return tmp;
    }

    static Node rotateRight(Node curr) {
        /*
             curr
              /
            tmp
            / \
           y   x
         */
        Node tmp = curr.left;
        curr.left = tmp.right;
        tmp.right = curr;
        curr.ht = heightUpdate(curr);
        tmp.ht = heightUpdate(tmp);
        return tmp;
    }

    static Node LL(Node curr) {
        return rotateRight(curr);
    }
    static Node RR(Node curr) {
        return rotateLeft(curr);
    }
    static Node LR(Node curr) {
        curr.left = rotateLeft(curr.left);
        return rotateRight(curr);
    }
    static Node RL(Node curr) {
        curr.right = rotateRight(curr.right);
        return rotateLeft(curr);
    }

    public Node insert(int data) {
        root = insert(root, data);
        return root;
    }

    static Node insert(Node root, int data) {
        if (root == null) return newNode(data);

        if (data > root.data) {
            root.right = insert(root.right, data);
            if (BF(root) == -2) {
                if (data > root.right.data)
                    root = RR(root);
                else root = RL(root);
            }
        }
        else if (data < root.data) {
            root.left = insert(root.left, data);
            if (BF(root) == 2) {
                if (data < root.left.data)
                    root = LL(root);
                else root = LR(root);
            }
        }
        root.ht = heightUpdate(root);
        return root;
    }

    static Node delete(int data) {
        return null;
    } /* tough one, TODO later */

    // helpers and utils
    public void inorder() {
        inorder(root);
    }
    static void inorder(Node node)  {
        if (node != null)
        {
            inorder(node.left);
            System.out.print(node.data+"(BF="+BF(node)+") ");
            inorder(node.right);
        }
    }

}
