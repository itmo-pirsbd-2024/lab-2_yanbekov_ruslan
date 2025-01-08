package btree;

import java.io.Serializable;

class BTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

    int t; // Минимальная степень 
    int[] keys; // Массив ключей
    int keyCount; // Текущее количество ключей
    BTreeNode[] children; // Массив дочерних узлов
    boolean leaf; // Является ли узел листом

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.keyCount = 0;
    }

    public void insertNonFull(int key) {
        int i = keyCount - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            keyCount++;
        } else {
            while (i >= 0 && keys[i] > key) {
                i--;
            }
            if (children[i + 1].keyCount == 2 * t - 1) {
                splitChild(i + 1, children[i + 1]);
                if (keys[i + 1] < key) {
                    i++;
                }
            }
            children[i + 1].insertNonFull(key);
        }
    }

    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.keyCount = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.keyCount = t - 1;

        for (int j = keyCount; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }

        children[i + 1] = z;

        for (int j = keyCount - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }

        keys[i] = y.keys[t - 1];
        keyCount++;
    }

    public BTreeNode search(int key) {
        int i = 0;
        while (i < keyCount && key > keys[i]) {
            i++;
        }

        if (keys[i] == key) {
            return this;
        }

        if (leaf) {
            return null;
        }

        return children[i].search(key);
    }

    public void traverse() {
        int i;
        for (i = 0; i < keyCount; i++) {
            if (!leaf) {
                children[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }

        if (!leaf) {
            children[i].traverse();
        }
    }
}