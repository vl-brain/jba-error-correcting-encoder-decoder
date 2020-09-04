package correcter;

import correcter.transformer.FileDecoder;
import correcter.transformer.FileEncoder;
import correcter.transformer.FileSender;
import correcter.transformer.printer.*;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String mode = scanner.nextLine();
        File sendFile = new File("send.txt");
        File encodedFile = new File("encoded.txt");
        File receivedFile = new File("received.txt");
        File decodedFile = new File("decoded.txt");
        switch (mode) {
            case "encode":
                new FileEncoder(sendFile, encodedFile).run();
                new FilePrinter(sendFile,
                        new TextViewPrinter(sendFile),
                        new HexViewPrinter(sendFile),
                        new BinaryViewPrinter(sendFile)).run();
                new FilePrinter(encodedFile,
                        new ExpandBinaryViewPrinter(encodedFile),
                        new BinaryViewPrinter(encodedFile, "parity"),
                        new HexViewPrinter(encodedFile)).run();
                break;
            case "send":
                new FileSender(encodedFile, receivedFile).run();
                new FilePrinter(encodedFile,
                        new HexViewPrinter(encodedFile),
                        new BinaryViewPrinter(encodedFile)).run();
                new FilePrinter(receivedFile,
                        new BinaryViewPrinter(receivedFile),
                        new HexViewPrinter(receivedFile)).run();
                break;
            case "decode":
                new FileDecoder(receivedFile, decodedFile).run();
                new FilePrinter(receivedFile,
                        new HexViewPrinter(receivedFile),
                        new BinaryViewPrinter(receivedFile)).run();
                new FilePrinter(decodedFile,
                        new BinaryViewPrinter(decodedFile, "decode"),
                        new HexViewPrinter(decodedFile),
                        new TextViewPrinter(decodedFile)).run();
        }
    }

}
