/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankmanagementsystem;

/**
 *
 * @author Jodi Mayo
 */
import java.io.*; // For file input and output
import java.util.Scanner; // For reading user input
public class BankManagementSystem {
    
    static String[][] user = new String[10][7]; // Array to store user data (10 users, 7 fields each)
    static int userCount = 0; //Counter for the number of registered users
    static double balance = 0.0; //User's account balance
    
    public static void main(String[] args) {
        
      loadUserFromTextFile(); //Load existing users in the text file
       
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            //Display menu options for login/registration
            System.out.println(">>>>>>>>>>Banking System<<<<<<<<<<");
            System.out.println(" 1.) Log in");
            System.out.println(" 2.) Sign up");          
            System.out.print("Enter choice: ");

            //Check if the input is integer
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        logIn(scanner); //Call logIn method
                        break;
                    case 2:
                        signUp(scanner);  //Call signUp method
                        saveUserToTextFile();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again."); // Handle invalid choice
                }
            } else {
                System.out.println("Invalid input. Please enter a number."); // Handle non-integer input
                scanner.next(); 
            }
        }
    }
    
    static void logIn(Scanner scanner) {
        // Log in process
        System.out.println("-----------LOG IN----------");
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Password: ");
        String userPass = scanner.nextLine();

        // Check if the contact number exists and the password matches
        boolean loggedIn = false;
        for (int i = 0; i < userCount; i++) { // Loop through all registered users 
            if (user[i][4].equals(contact) && user[i][6].equals(userPass)) {
                loggedIn = true; // User is logged in
                break;
            }
        }

        if (loggedIn) {
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Login successfully."); 
            mainMenu(scanner); // Call main menu if login is successful
        } else {
            System.out.println("Invalid contact number or password."); // Handle login failure
        }
    }
    
    static void signUp(Scanner scanner){
        //Registration process
        System.out.println("-----------SIGN UP----------");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        String age = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Birthdate(MM/DD/YYYY): ");
        String birthDate = scanner.nextLine();
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Email address: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Password(PIN): ");
        String userPass = scanner.nextLine();
        System.out.print("Confirm Password(PIN): ");
        String userconPass = scanner.nextLine();
        
        // Check if the password and confirmation password match
        if (userPass.equals(userconPass)) {
            // Store user information in the array
            user[userCount][0] = name;        // Name
            user[userCount][1] = age;         // Age
            user[userCount][2] = address;     // Address
            user[userCount][3] = birthDate;   // Birthdate
            user[userCount][4] = contact;     // Contact
            user[userCount][5] = emailAddress;    // Email Address
            user[userCount][6] = userPass;    // Password
            userCount++;
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Sign Up successfully.");
            mainMenu(scanner); // Call main menu after successful sign up
        } else {
            System.out.println("Passwords do not match. Please try again."); //Handle password mismatch
        }
    }
    
    static void mainMenu(Scanner scanner){
        
        while (true) {
            // Main menu for authorized users
            System.out.println(">>>>>>>>>>MAIN MENU<<<<<<<<<<");
            System.out.println(" 1.) Check Balance");
            System.out.println(" 2.) Deposit");
            System.out.println(" 3.) Withdraw");
            System.out.println(" 4.) Transfer Money");
            System.out.println(" 5.) View Account");
            System.out.println(" 6.) Update Account");
            System.out.println(" 7.) Delete Account");
            System.out.println(" 8.) Exit");
            System.out.print("Enter choice: ");
            
            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        checkBalance(balance); // Call checkBalance method
                        break;
                    case 2:
                        deposit(scanner); // Call deposit method
                        break;
                    case 3:
                        withdraw(scanner); // Call withdraw method
                        break;
                    case 4:
                        transferMoney(scanner); // Call transferMoney method
                        break;
                    case 5:
                        viewAccount(scanner); // Call viewAccount method
                        break;
                    case 6:
                        updateAccount(scanner); // Call updateAccount method
                        return;
                    case 7:
                        deleteAccount(scanner); // Call deleteAccount method
                        return; 
                    case 8:
                        System.out.println("Thank your for using Bank System");
                        saveUserToTextFile(); // Save user data before exiting
                        System.exit(0); // Exit the program
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
    }
    
    static void checkBalance(double balance){
         // Display user's balance
        System.out.println("----------BALANCE----------");
        if (balance > 0) {
            System.out.println("Your balance: " + balance);
        } else {
            System.out.println("You have no funds available.");
        }
        System.out.println("------------------------");
        System.out.println("Press Enter to continue.");
        new Scanner(System.in).nextLine(); // Wait for user to press Enter
    }
    
    static void deposit(Scanner scanner){
        // Deposit amount into user's account
         System.out.println("----------DEPOSIT----------");
        System.out.println("Enter amount to deposit: ");
            if(scanner.hasNextDouble()){ // Check if the input is an integer
                double deposit = scanner.nextDouble();
                if(deposit > 0){
                    balance += deposit; // Add deposit to balance
                    System.out.println("Deposited amount: " + deposit);
                    System.out.println("Press Enter to continue.");
                    new Scanner(System.in).nextLine(); // Wait for user to press Enter
                } else {
                    System.out.println("Invalid input. Please enter an exact amount."); // Handle invalid deposit amount
                    new Scanner(System.in).nextLine();
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }           
    }
    
    static void withdraw(Scanner scanner){
        // Withdraw amount from user's account
         System.out.println("----------WITHDRAW----------");
        checkBalance(balance); // Display current balance
        System.out.println("Enter amount to withdraw: " );
        if(scanner.hasNextDouble()){ // Check if the input is an integer
                double withdraw = scanner.nextDouble();
                if(withdraw <= balance || withdraw > 0){
                    balance -= withdraw; // Deduct withdrawal from balance                  
                    System.out.println("Withdraw amount: " + withdraw);
                    System.out.println("Press Enter to continue.");
                    new Scanner(System.in).nextLine(); // Wait for user to press Enter
                } else {
                    System.out.println("Invalid input. Please enter an exact amount."); // Handle invalid withdraw amount
                    new Scanner(System.in).nextLine();
                }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
        }
    }
    
    static void transferMoney(Scanner scanner){
        // Transfer money to another user
        System.out.println("----------TRANSFER MONEY----------");
        System.out.println("Enter the contact number of user to transfer to: ");
        String toUser =  scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        
        if(scanner.hasNextDouble()){ // Check if the input is an integer
            double transferAmount = scanner.nextDouble();
            if(transferAmount > 0 || transferAmount <= balance){
                boolean recipientFound = false; // To check if recipient exists
                for (int i = 0; i < userCount; i++) { // Loop through all registered users 
                    if (user[i][4].equals(toUser)) { //Check if the contact number existed in file text matches the inputted username 
                        recipientFound = true; // Recipient found
                        break;
                    }
                }   
                if(recipientFound){
                    balance -= transferAmount; // Deduct from sender's balance
                    System.out.println("Successfully transferred PHP" + transferAmount + " to " + toUser);
                    new Scanner(System.in).nextLine(); // Wait for user to press Enter
                }else{
                System.out.println("Recipient username not found."); // Handle case where recipient does not exist
                System.out.println("Press Enter to continue.");
                new Scanner(System.in).nextLine();
                }
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
         
    }
    
   
    
    static void viewAccount(Scanner scanner){
        // View personal account information
        System.out.println("----------PERSONAL INFORMATION----------");
        System.out.print("Enter your contact number to view account: "); //User enter their contact number to view
        String contact = scanner.nextLine();
        for (int i = 0; i < userCount; i++) { // Loop through all registered users 
            if (user[i][4].equals(contact)) { // Check if username data in file text matches the input
            System.out.println((i + 1));
            System.out.println("Name: " + user[i][0]);
            System.out.println("Age: " + user[i][1]);
            System.out.println("Address: " + user[i][2]);
            System.out.println("Birthdate: " + user[i][3]);
            System.out.println("Contact Number: " + user[i][4]);
            System.out.println("Email Address: " + user[i][5]);
            System.out.println("Password: " + user[i][6]);
            System.out.println("------------------------");
            }
        }
        System.out.println("Press Enter to continue.");
        new Scanner(System.in).nextLine(); // Wait for user to press Enter
    }
    
    static void updateAccount(Scanner scanner){
        // Update account information
        System.out.println("----------UPDATE ACCOUNT----------");         
        System.out.print("Enter your contact number to update account: "); //Enter their contact number
        String contact = scanner.nextLine();
        System.out.print("Enter your password to update account: "); //Enter their password
        String userPass = scanner.nextLine();
        for (int i = 0; i < userCount; i++) { // Loop through all registered users 
            if (user[i][4].equals(contact) || user[i][6].equals(userPass)) { // Check if user data in file text matches the input
                System.out.print("Enter your new Name: ");
                user[i][0] = scanner.nextLine();
                System.out.print("Enter your new Age: ");
                user[i][1] = scanner.nextLine();
                System.out.print("Enter your new Address: ");
                user[i][2] = scanner.nextLine();
                System.out.print("Enter your new Birthdate(MM/DD/YYYY): ");
                user[i][3] = scanner.nextLine();
                System.out.print("Enter your new Contact Number: ");
                user[i][4] = scanner.nextLine();
                System.out.print("Enter your new Email Address: ");
                user[i][5] = scanner.nextLine();
                System.out.print("Enter your new Password: ");
                user[i][6] = scanner.nextLine();
                System.out.println("Account updated successfully."); // Confirm update
                break;
            }
        }
        System.out.println("Press Enter to continue.");
        new Scanner(System.in).nextLine(); // Wait for user to press Enter
        
    }
    
    static void deleteAccount(Scanner scanner){
        // Delete user account
        System.out.println("----------DELETE ACCOUNT----------");
        System.out.print("Enter your contact number to delete account: ");
        String contact = scanner.nextLine();
        System.out.print("Enter your password to delete account: ");
        String userPass = scanner.nextLine();
        for (int i = 0; i < userCount; i++) { // Loop through all registered users 
            if (user[i][4].equals(contact) && user[i][6].equals(userPass)) { // Check if user data in file text matches the input
                // Shift users left to remove the account
                for (int j = i; j < userCount - 1; j++) {
                    user[j] = user[j + 1]; // Shift users left
                }
                user[userCount - 1] = null; // Clear last user
                userCount--; // Decrement user count
                System.out.println("Account deleted successfully."); // Confirm deletion

                
            }        
        }
        System.out.println("Press Enter to continue.");
        new Scanner(System.in).nextLine(); // Wait for user to press Enter
    }
    
    static void saveUserToTextFile() {
        // Save user data to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userlist.txt"))) {
            for (int i = 0; i < userCount; i++) { // Loop through all registered users 
                writer.write(user[i][0] + ";" + user[i][1] + ";" + 
                             user[i][2] + ";" + user[i][3] + ";" + 
                             user[i][4] + ";" + user[i][5] + ";" + 
                             user[i][6]); // Write user data to file
                writer.newLine(); // Move to the next line
            }
        } catch (IOException e) {
            System.out.println("Error saving user file: " + e.getMessage()); // Handle file writing errors

        }
    }
    
    static void loadUserFromTextFile() {
        // Load user data from a text file
        File file = new File("userlist.txt");
        if (file.exists()) { // Check if file exist
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";"); // Split line into user fields
                    user[userCount][0] = parts[0].trim(); // Name
                    user[userCount][1] = parts[1].trim(); // Age
                    user[userCount][2] = parts[2].trim(); // Address
                    user[userCount][3] = parts[3].trim(); // Birthdate
                    user[userCount][4] = parts[4].trim(); // Contact
                    user[userCount][5] = parts[5].trim(); // Email Address
                    user[userCount][6] = parts[6].trim(); // Password
                    userCount++;
                }
            } catch (IOException e) { // If file doesn't exist
                System.out.println("Error reading user file: " + e.getMessage()); // Handle file reading errors
            }
        }
    }    
}
