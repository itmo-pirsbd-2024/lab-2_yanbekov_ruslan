package btree;

import java.io.*;


public class BTree {
    private BTreeNode root;
    private int t; // Минимальная степень
    private String filePath;

    public BTree(int t, String filePath) {
        this.root = new BTreeNode(t, true);
        this.t = t;
        this.filePath = filePath;
    }

    public void insert(int key) {
        BTreeNode r = root;
        if (r.keyCount == 2 * t - 1) {
            BTreeNode s = new BTreeNode(t, false);
            root = s;
            s.children[0] = r;
            s.splitChild(0, r);
            s.insertNonFull(key);
        } else {
            r.insertNonFull(key);
        }
        saveNode(root, "root");
    }

    public BTreeNode search(int key) {
        return root.search(key);
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    private void saveNode(BTreeNode node, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath + File.separator + fileName))) {
            oos.writeObject(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BTreeNode loadNode(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath + File.separator + fileName))) {
            return (BTreeNode) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadRoot() {
        root = loadNode("root");
    }
}


