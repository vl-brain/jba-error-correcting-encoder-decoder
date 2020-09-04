package correcter.transformer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class FileSender extends AbstractFileTransformer {
    public FileSender(File source, File destination) {
        super(source, destination);
    }

    @Override
    protected void transform(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i;
        Random random = new Random();
        while ((i = inputStream.read()) != -1) {
            outputStream.write(i ^ 1 << random.nextInt(8));
        }
    }
}
