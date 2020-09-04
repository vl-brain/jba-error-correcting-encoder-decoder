package correcter.transformer.printer;

import correcter.transformer.Transformer;

import java.io.File;
import java.io.PrintStream;

public abstract class AbstractFilePrinter implements Transformer {
    protected final File source;
    protected final PrintStream out;
    private final String title;

    public AbstractFilePrinter(File source, PrintStream out, String title) {
        this.source = source;
        this.out = out;
        this.title = title;
    }

    public AbstractFilePrinter(File source, String title) {
        this(source, System.out, title);
    }

    @Override
    public void run() {
        out.print(title + ":");
    }
}
