package correcter.transformer.printer;

import java.io.File;

public class BinaryViewPrinter extends AbstractFileBytePrinter {
    public BinaryViewPrinter(File source) {
        this(source, "bin view");
    }

    public BinaryViewPrinter(File source, String title) {
        super(source, title);
    }

    @Override
    protected String getPrintByteView(int i) {
        return Integer.toBinaryString(i | 1 << Byte.SIZE)
                .substring(1);
    }
}
