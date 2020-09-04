package correcter.transformer.printer;

import correcter.transformer.Transformer;

import java.io.File;

public class FilePrinter extends AbstractFilePrinter {
    private final Transformer[] transformers;
    public FilePrinter(File source, Transformer... transformers) {
        super(source, source.getName());
        this.transformers = transformers;
    }

    @Override
    public void run() {
        out.println();
        super.run();
        out.println();
        for (Transformer transformer : transformers) {
            transformer.run();
            out.println();
        }
    }

}
