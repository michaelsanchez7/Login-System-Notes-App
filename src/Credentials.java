import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Credentials {
    private static int count = 0;
    public static boolean validCreds(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("login.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        if (fileUsername.equals("michael")) {
                            System.out.println("\nLogin successful!");
                            System.out.println("\nHello ADMIN, Welcome Back!");
                            Menus.printAdminMenu();
                        } else {
                            System.out.println("\nLogin successful!");
                            System.out.println("\nHello " + fileUsername + ", Welcome Back!");
                        }
                        return true;
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
        System.err.println("\nInvalid username or password.");
        count += 1;
        System.out.println("Login attempts: " + count);
        if (count == 2) {
            System.out.println("You Have One More Attempt");
        } else if (count >= 3) {
            System.out.println("Too Many Failed Attempts. Exiting Program.");
            System.exit(0);
        }
        return false;

    }


    public static int registerCount() {
        int userCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("login.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                if (line.trim().equalsIgnoreCase("username,password")) {
                    continue; // Skip header line if present
                } else if (line.trim().equals("michael,mikey1")) {
                    continue; // Skip the admin user line
                }
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCount ++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
        if (userCount == 0) {
            System.out.println("No users registered yet.");
            System.out.println("Please Register A User");
            
        }
        return userCount;
        
    }
    public static void userInfo() {
        try (BufferedReader br = new BufferedReader(new FileReader("login.csv"))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                
                String[] parts = line.split(",");
                if (line.trim().equalsIgnoreCase("username,password")) {
                    continue; // Skip header line if present
                }
                lineCount++;
                if (parts.length == 2) {
                    
                    System.out.println("User " + lineCount + "- Username: " + parts[0] + ", Password: " + parts[1]);
                } else {
                    System.out.println("Invalid entry in credentials file: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
    }

    public static boolean userExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("login.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(username)) {
                    System.out.println("Username Already Exists");
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
        return false;
    }

    public static void addUser(String username, String password) {
        try (FileWriter writer = new FileWriter("login.csv", true)) {
            writer.append(System.lineSeparator());
            writer.append(username).append(',').append(password);
            Menus.clearConsole();
        } catch (IOException e) {
            System.err.println("Error writing to credentials file: " + e.getMessage());
        }
    }
}