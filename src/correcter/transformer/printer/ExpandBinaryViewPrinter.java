package correcter.transformer.printer;

import java.io.File;

public class ExpandBinaryViewPrinter extends BinaryViewPrinter {
    public ExpandBinaryViewPrinter(File source) {
        super(source, "expand");
    }

    @Override
    protected String getPrintByteView(int i) {
        final StringBuilder builder = new StringBuilder(super.getPrintByteView(i));
        builder.replace( 0, 2, "..");
        builder.replace( 3, 4, ".");
        builder.replace( 7, 8, ".");
        return builder.toString();
    }
}
