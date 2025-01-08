import btree.BTree;
import btree.OutputCapturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class BTreeTest {
    private BTree bTree;
    private String filePath;
    private OutputCapturer outputCapturer;

    @BeforeEach
    public void setUp() {
        filePath = "btree_nodes_test";
        new File(filePath).mkdirs();
        bTree = new BTree(3, filePath); // Минимальная степень 3
        outputCapturer = new OutputCapturer();
    }

    @Test
    public void testInsertAndTraverse() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        outputCapturer.start();
        bTree.traverse();
        outputCapturer.stop();

        String expectedOutput = "5 6 7 10 12 17 20 30 ";
        String actualOutput = outputCapturer.getOutput().trim();

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testSearch() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        assertNotNull(bTree.search(6));
        assertNull(bTree.search(15));
    }

    @Test
    public void testLoadRoot() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        BTree loadedTree = new BTree(3, filePath);
        loadedTree.loadRoot();

        outputCapturer.start();
        loadedTree.traverse();
        outputCapturer.stop();

        String expectedOutput = "5 6 7 10 12 17 20 30 ";
        String actualOutput = outputCapturer.getOutput().trim();

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testInsertAfterLoad() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        BTree loadedTree = new BTree(3, filePath);
        loadedTree.loadRoot();
        loadedTree.insert(25);
        loadedTree.insert(128);

        outputCapturer.start();
        loadedTree.traverse();
        outputCapturer.stop();

        String expectedOutput = "5 6 7 10 12 17 20 25 30 128 ";
        String actualOutput = outputCapturer.getOutput().trim();

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
