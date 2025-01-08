package btree;

import java.io.File;
import java.util.Random;

public class BTreeExample {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("PID " + pid);

        String filePath = "btree_nodes";
        new File(filePath).mkdirs();

        BTree bTree = new BTree(3, filePath);

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int value = random.nextInt(1000000);
            bTree.insert(value);
        }

        System.out.println("Traversal of the constructed tree is ");
        bTree.traverse();

        int key = 6;
        if (bTree.search(key) != null) {
            System.out.println("\nKey " + key + " есть в дереве");
        } else {
            System.out.println("\nKey " + key + " нет в дереве");
        }

        key = 15;
        if (bTree.search(key) != null) {
            System.out.println("\nKey " + key + " есть в дереве");
        } else {
            System.out.println("\nKey " + key + " нет в дереве");
        }

        BTree loadedTree = new BTree(3, filePath);
        loadedTree.loadRoot();
        System.out.println("\nTraversal of the loaded tree is ");
        loadedTree.traverse();

        loadedTree.insert(25);
        loadedTree.insert(128);
        System.out.println("\nTraversal after inserting new nodes is ");
        loadedTree.traverse();

        BTree loadedTreenext = new BTree(3, filePath);
        loadedTreenext.loadRoot();
        System.out.println("\nTraversal of the reloaded tree is ");
        loadedTreenext.traverse();
    }
}
