import java.util.*;

public class BinaryTreeDeletion {
    static class TreeNode{
        int data;
        TreeNode left, right;
        TreeNode(int val){
            data = val;
            left = right = null;
        }
    }

    static TreeNode delete(TreeNode root, int key){
        if (root==null) return null;
        if (root.left==null && root.right==null){
            return (root.data==key) ?null:root;
        }
        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);
        TreeNode keyNode = null, temp=null, last=null;
        while (!queue.isEmpty()){
            temp = queue.poll();
            if (temp.data==key) keyNode=temp;
            if (temp.left!=null){
                last=temp;
                queue.add(temp.left);
            }
            if (temp.right!=null){
                last=temp;
                queue.add(temp.right);
            }
        }
        if (keyNode!=null){
            keyNode.data = temp.data;
            if (last.right==temp) last.right=null;
            else last.left=null;
        }
        return root;
    }

    public static void inorder(TreeNode root){
        if (root==null) return;
        inorder(root.left);
        System.out.print(root.data+ " ");
        inorder(root.right);
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right = new TreeNode(50);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(70);
        System.out.println("Before Deletion");
        inorder(root);
        System.out.println();
        delete(root, 50);
        System.out.println("After Deletion");
        inorder(root);
    }
}