package btree;

import java.io.File;

public class BTreeBenchmark {
    public static void main(String[] args) {

        long pid = ProcessHandle.current().pid();
        System.out.println("PID " + pid);

        int[] tValues = {1000, 500, 100, 50, 10};

        String filePath = "btree_data";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        int[] sizes = {100, 1000, 5000, 10000, 20000};

        for (int t : tValues) {
            System.out.println("\nБенчмаркинг для t = " + t);

            for (int size : sizes) {
                System.out.println("\nТестирование для размера: " + size);

                BTree bTree = new BTree(t, filePath);

                long startTime = System.currentTimeMillis();

                for (int i = 0; i < size; i++) {
                    bTree.insert(i);
                }

                long endTime = System.currentTimeMillis();
                System.out.println("Вставка " + size + " элементов: " + (endTime - startTime) + " ms");

                startTime = System.currentTimeMillis();

                for (int i = 0; i < size; i++) {
                    BTreeNode node = bTree.search(i);
                    if (node == null) {
                        System.out.println("Не найден " + i);
                    }
                }

                endTime = System.currentTimeMillis();
                System.out.println("Поиск " + size + " элементов: " + (endTime - startTime) + " ms");
            }
        }

        deleteDirectory(dir);
    }

    private static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            directory.delete();
        }
    }
}
