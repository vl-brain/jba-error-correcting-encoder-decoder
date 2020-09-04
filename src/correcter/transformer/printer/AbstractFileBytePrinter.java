package correcter.transformer.printer;

import java.io.*;

public abstract class AbstractFileBytePrinter extends AbstractFilePrinter {
    public AbstractFileBytePrinter(File source, PrintStream out, String title) {
        super(source, out, title);
    }

    public AbstractFileBytePrinter(File source, String title) {
        super(source, title);
    }

    @Override
    public void run() {
        super.run();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(source))) {
            int i;
            while ((i = inputStream.read()) != -1) {
                out.print( " " + getPrintByteView(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getPrintByteView(int i);
}
