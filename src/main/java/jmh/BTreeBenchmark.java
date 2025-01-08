package jmh;

import btree.BTree; // Импорт класса BTree
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BTreeBenchmark {

    private BTree bTree;

    @Param({"100", "1000", "10000", "100000", "1000000"})
    private int numberOfNodes;

    @Setup
    public void setup() {
        File directory = new File("btree_nodes_benchmark");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        bTree = new BTree(3, "btree_nodes_benchmark");
        for (int i = 0; i < numberOfNodes; i++) {
            bTree.insert(i);
        }
    }

    @Benchmark
    public void traverse() {
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BTreeBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
