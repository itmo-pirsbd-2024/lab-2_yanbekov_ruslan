package btree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputCapturer {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void start() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    public void stop() {
        System.setOut(System.out);
    }

    public String getOutput() {
        return outputStreamCaptor.toString();
    }
}
