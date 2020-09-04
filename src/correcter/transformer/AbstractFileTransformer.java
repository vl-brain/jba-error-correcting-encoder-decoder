package correcter.transformer;

import java.io.*;

public abstract class AbstractFileTransformer implements Transformer {
    private final File source;
    private final File destination;

    public AbstractFileTransformer(File source, File destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void run() {
        if (!destination.exists()) {
            try {
                destination.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(source));
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination))) {
            transform(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void transform(InputStream inputStream, OutputStream outputStream) throws IOException;
}
