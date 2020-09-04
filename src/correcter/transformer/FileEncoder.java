package correcter.transformer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileEncoder extends AbstractFileTransformer {
    public FileEncoder(File source, File destination) {
        super(source, destination);
    }

    @Override
    protected void transform(InputStream inputStream, OutputStream outputStream) throws IOException {
        class OutputWriter {
            public static final int HALF_BYTE = Byte.SIZE / 2;
            public static final int HALF_BYTE_MASK = 0x0F;

            public void writeByte(int value) throws IOException {
                writeHalfByte(value >> HALF_BYTE);
                writeHalfByte(value & HALF_BYTE_MASK);
            }

            private void writeHalfByte(int halfByte) throws IOException {
                int value = 0;
                if (halfByte != 0) {
                    value |= (((halfByte & 0b1000) << 1) | (halfByte & 0b0111)) << 1;
                    value |= getParityBit(value & 0b101010) << 7;
                    value |= getParityBit(value & 0b100110) << 6;
                    value |= getParityBit(value & 0b1110) << 4;
                }
                outputStream.write(value);
            }

            private int getParityBit(int value) {
                int bitCount = 0;
                while ((value >>= 1) != 0) {
                    bitCount += value & 1;
                }
                double d = 20.3; // 1
                long n = (long) d; // 2
                int i = (int) n; // 3
                float f = n; // 4
                return bitCount & 1;
            }
        }
        OutputWriter outputWriter = new OutputWriter();
        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
            outputWriter.writeByte(i);
        }
    }
}
