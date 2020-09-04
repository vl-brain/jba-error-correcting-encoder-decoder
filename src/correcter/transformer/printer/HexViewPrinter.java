package correcter.transformer.printer;

import java.io.*;

public class HexViewPrinter extends AbstractFileBytePrinter {
    public HexViewPrinter(File source) {
        super(source, "hex view");
    }

    @Override
    protected String getPrintByteView(int i) {
        return Integer.toHexString(i | 1 << Byte.SIZE)
                .toUpperCase()
                .substring(1);
    }

}
