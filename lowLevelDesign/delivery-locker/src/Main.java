import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Locker locker = new Locker();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Enter command: assign/retrieve/exit");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("assign")) {
                System.out.println("Enter size of the Compartment (SMALL, MEDIUM, BIG): ");
                String sizeInput = scanner.nextLine();
                try {
                    String token = locker.assignCompartment(CompartmentSize.getSize(sizeInput));
                    System.out.println("Assigned compartment with token: " + token);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please try again.");
                }
            } else if (input.equalsIgnoreCase("retrieve")) {
                System.out.println("Enter token: ");
                String tokenInput = scanner.nextLine();
                try {
                    Compartment compartment = locker.retrieveCompartment(tokenInput);
                    System.out.println("Retrieved compartment: " + compartment);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid command. Please enter assign, retrieve, or exit.");
            }
        }
    }
}