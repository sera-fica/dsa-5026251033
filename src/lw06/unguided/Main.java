package lw06.unguided;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree tree = new BinarySearchTree();

        ArrayList<Integer> numbers = new ArrayList<>();
        while (read.hasNextInt()) {
            numbers.add(read.nextInt());
        }

        int target = numbers.get(numbers.size() - 1);
        for (int i = 0; i < numbers.size() - 1; i++) {
            tree.insert(numbers.get(i));
        }

        System.out.print("Preorder : ");
        tree.preOrder();

        System.out.print("Inorder : ");
        tree.inOrder();

        System.out.print("Postorder : ");
        tree.postOrder();

        System.out.println("Search " + target + " : " + tree.search(target));
        System.out.println("Nodes : " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());

        read.close();
    }
}
