import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static String readByBienMethod(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static long countLinesNumber() throws IOException {
        return Files.lines(Paths.get("logs.txt")).count();
    }

    public static long countErrorsByStreams() throws IOException {
        return Files.lines(Paths.get("logs.txt")).filter(line -> line.contains("ERROR")).count();
    }

    public static long countErrorsByStringSplitting(String content) {
        long errorNumber = 0;
        String [] lines = content.split("\n");
        for (String i: lines) {
            if(i.contains("ERROR")) {
                errorNumber++;
            }
        }
        return errorNumber;
    }

    public static void extractAllLogsToFile(String content, String filePath) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String [] args) throws IOException {
        String content = readByBienMethod("logs.txt");
        extractAllLogsToFile(content, "out.txt");
        System.out.println("Total number of logs (lines): " + countLinesNumber());
        System.out.println("Total number of ERROR logs (String.split): " + countErrorsByStringSplitting(content));
        System.out.println("Total number of ERROR logs (Files.lines): " + countErrorsByStreams());
    }

}
