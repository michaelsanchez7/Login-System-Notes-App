import java.util.Scanner;

public class Menus {
    private static final Scanner scnr = new Scanner(System.in);
    static String username;
    static String password;
    static boolean uReg = false;
    
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
    }

    public static void buffer() {
        for (int i = 0; i <= 4; i++) {
                System.out.print(".");
                    
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Error during sleep: " + e.getMessage());
                break;
            }
            i++;
        }
    }

    public static void printLoginMenu() {

        Credentials.registerCount();
         System.out.println("USERNAME");
        username = scnr.nextLine();
        System.out.println("PASSWORD");
        password = scnr.nextLine();
        if (Credentials.validCreds(username, password) == true) {
            uReg = true;
            postLogInMenu();
        } 
    }

    public static void printRegisterMenu() { 
        System.out.println("\nAccount Creation\nAfter This Process, You Will Be Brought To The Login Menu\n");
        System.out.println("Please enter a Username");
        username = scnr.nextLine();

        if (Credentials.userExists(username) == true) {
            System.out.println("Failed");
        } else {
            System.out.println("Please enter a Password");
            password = scnr.nextLine();
            
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
                return;
            }

            Credentials.addUser(username, password);
            System.out.println("\nRegistration successful. Returning to login menu.\nPlease Sign In\n");
            printLoginMenu();
            
            
        }
    }

    public static void printAdminMenu() {
        System.out.println("ADMIN PANEL");
        boolean exit = false;
        
        do {
            uReg = false;
            System.out.println("\n1: View User Count\n2: View User Info\n3: Notes Menu\n4: Exit");
            int adminChoice = scnr.nextInt();
            scnr.nextLine();
            try {
                switch (adminChoice) {
                    case 1:
                        clearConsole();
                        System.out.println("Total registered users: " + Credentials.registerCount());
                        break;

                    case 2:
                        clearConsole();
                        System.out.println("User Information:");
                        Credentials.userInfo();
                        break;
                    
                    case 3:
                        clearConsole();
                        buffer();
                        printNotesMenu();
                        break;
                    case 4:
                        clearConsole();
                        System.out.print("Exiting");
                        buffer();
                        System.out.println("\nGoodbye");
                        System.exit(0);
                        break;

                    default: 
                        System.out.println("Invalid choice. Please try again.");
                        break;
                
                    }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scnr.nextLine();
            }
        } while (!exit);
    }

    public static void printMainMenu() {
        
        boolean exit = false;
        boolean registered = false;
        if (registered == false) {
            do {   
            
            System.out.println("\nMain Menu");
            System.out.println("1: Login? \n2: Register?\n3: Exit?");
            
            try {
                int loginChoice = scnr.nextInt();
                scnr.nextLine(); 

                switch (loginChoice) {
                    case 1:
                        clearConsole();
                        if (Credentials.registerCount() == 0) {
                            printRegisterMenu();
                            if (uReg == true) {
                                registered = true;
                                
                            }
                        } else {
                            printLoginMenu();
                            if (uReg == true) {
                                registered = true;
                            }
                        }
                        break;      

                    case 2:
                        clearConsole();
                        printRegisterMenu();
                        if (uReg == true) {
                            exit = true;
                        } 
                        break;

                    case 3:
                        clearConsole();
                        System.out.print("Exiting");
                        buffer();
                        System.out.println("\nGoodbye");
                        System.exit(0);
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scnr.nextLine(); 
                }
            } while (exit == false);
        }
    }  

    public static void postLogInMenu() {
        boolean exit = false;

        do {
            System.out.println("\nMAIN MENU");
            System.out.println("1: Notes\n2: Logout");
        
            try {
                int choice = scnr.nextInt();
                scnr.nextLine();
                switch (choice) {
                    case 1:
                        clearConsole();
                        buffer();
                        printNotesMenu();
                        break;

                    case 2:
                        clearConsole();
                        System.out.print("Exiting");
                        buffer();
                        System.out.println("\nGoodbye");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                    }   
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scnr.nextLine(); // Clear the invalid input
                } 
            }while (!exit);
        }

    public static void printNotesMenu() {
        boolean exit = false;

        do {
            System.out.println("NOTES MENU");
            System.out.println("1: Create Note\n2: Read Notes\n3: Back to Main Menu");

            try {
                int choice = scnr.nextInt();
                scnr.nextLine(); 

                switch (choice) {
                    case 1:
                        clearConsole();
                        System.out.println("Enter note content:");
                        String noteContent = scnr.nextLine();
                        Notes.createNote(username, noteContent);
                        break;

                    case 2:
                        clearConsole();
                        Notes.readNotes(username);
                        break;

                    case 3:
                        clearConsole();
                        System.out.print("Returning to Main Menu");
                        buffer();
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
        } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scnr.nextLine();
            }
        } while (exit == false);
    }
}