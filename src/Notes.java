import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notes {
    private static final String NOTE_DIRECTORY = "notes";

    static {
        File dir = new File(NOTE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void createNote(String username, String noteContent) {
        try (FileWriter writer = new FileWriter(NOTE_DIRECTORY + "/" + username + ".txt", true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + noteContent + System.lineSeparator());
            System.out.println("SAVED");
        } catch (IOException e) {
            System.err.println("Trouble writing file: " + e.getMessage());
        }
    } 

    public static void readNotes(String username) {
        File file = new File(NOTE_DIRECTORY + "/" + username + ".txt");
        if (!file.exists()) {
            System.out.println("No notes found for user: " + username);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--------------------\n");
        } catch (IOException e) {
            System.err.println("Error reading note file: " + e.getMessage());
        }
    }
}