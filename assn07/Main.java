package assn07;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        System.out.println("Enter Master Password");
        String user_entry = scanner.nextLine();
        // infinite loop to go back to "Enter master password"
        while(passwordManager.checkMasterPassword(user_entry) == false){
            System.out.println("Enter Master Password");
            user_entry = scanner.nextLine();
        }

        String command = scanner.nextLine();
        // loop to read and execute commands until "Exit" is entered
        while(command.compareTo("Exit") != 0){
            if(command.compareTo("New password") == 0){
                String website = scanner.nextLine();
                String password = scanner.nextLine();
                passwordManager.put(website, password);
                System.out.println("New Password added");
                command = scanner.nextLine();
            }

            else if(command.compareTo("Get password") == 0){
                String website = scanner.nextLine();
                if(passwordManager.get(website) == null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println(passwordManager.get(website));
                }
                command = scanner.nextLine();
            }

            else if(command.compareTo("Delete account") == 0){
                String website = scanner.nextLine();
                if(passwordManager.get(website) == null){
                    System.out.println("Account does not exist");
                }
                else{
                    passwordManager.remove(website);
                    System.out.println("Account deleted");
                }
                command = scanner.nextLine();
            }

            else if(command.compareTo("Check duplicate password") == 0){
                String password = scanner.nextLine();
                if(passwordManager.checkDuplicate(password).isEmpty()){
                    System.out.println("No account uses that password");
                }
                else{
                    System.out.println("Websites using that password:");
                    int i = 0;
                    int size = (passwordManager.checkDuplicate(password)).size();
                    while (i < size){
                        System.out.println((passwordManager.checkDuplicate(password)).get(i));
                        i++;
                    }
                }
                command = scanner.nextLine();
            }

            else if(command.compareTo("Get accounts") == 0){
                System.out.println("Your Accounts:");
                int i = 0;
                int size = (passwordManager.keySet()).size();
                String [] List = (passwordManager.keySet()).toArray(new String[passwordManager.keySet().size()]);
                while (i<size){
                    System.out.println(List[i]);
                    i++;
                }
                command = scanner.nextLine();
            }
            else if(command.compareTo("Generate random password") == 0){
                int length = Integer.parseInt(scanner.nextLine());
                System.out.println(passwordManager.generatesafeRandomPassword(length));
                command = scanner.nextLine();
            }
            else{
                System.out.println("Command not found");
                command = scanner.nextLine();
            }
        }
    }
}

