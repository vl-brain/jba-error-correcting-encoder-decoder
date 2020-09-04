package correcter.transformer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDecoder extends AbstractFileTransformer {
    public FileDecoder(File source, File destination) {
        super(source, destination);
    }

    @Override
    protected void transform(InputStream inputStream, OutputStream outputStream) throws IOException {
        class OutputWriter {
            private static final int DECODED_BIT_COUNT = Byte.SIZE / 2;
            private int buffer = 0;
            private int bufferBitCount = 0;

            public void write(int encodedBits) throws IOException {
                int decodedBits = decodeBits(encodedBits);
                writeDecodedBits(decodedBits);
            }

            private int decodeBits(int value) {
                int p1BitError = getParityBit(value & 0b10101010);
                int p2BitError = getParityBit(value & 0b01100110);
                int p4BitError = getParityBit(value & 0b00011110);
                value ^= 1 << (Byte.SIZE - (p1BitError | p2BitError << 1 | p4BitError << 2));
                return (((value & 0b100000) >> 1) | (value & 0b1110)) >> 1;
            }

            private int getParityBit(int value) {
                int bitCount = 0;
                while ((value >>= 1) != 0) {
                    bitCount += value & 1;
                }
                return bitCount & 1;
            }

            private void writeDecodedBits(int decodedBits) throws IOException {
                bufferBitCount += DECODED_BIT_COUNT;
                buffer <<= DECODED_BIT_COUNT;
                buffer |= decodedBits;
                if (bufferBitCount == Byte.SIZE) {
                    outputStream.write(buffer);
                    buffer = 0;
                    bufferBitCount = 0;
                }
            }

        }
        OutputWriter outputWriter = new OutputWriter();
        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
            outputWriter.write(i);
        }
    }
}
