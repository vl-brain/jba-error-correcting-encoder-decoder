package correcter.transformer.printer;

import java.io.*;
import java.util.Scanner;

public class TextViewPrinter extends AbstractFilePrinter {
    public TextViewPrinter(File source) {
        super(source, "text view");
    }

    @Override
    public void run() {
        super.run();
        try (Scanner scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                out.print(" " + scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
