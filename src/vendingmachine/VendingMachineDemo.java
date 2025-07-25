package vendingmachine;

import java.util.Scanner;

public class VendingMachineDemo {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        while (true) {
            displayMenu();
            machine.getStatus();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        machine.displayProducts();
                        break;
                    case 2:
                        selectProductInteractive(machine);
                        break;
                    case 3:
                        insertMoneyInteractive(machine);
                        break;
                    case 4:
                        machine.getStatus();
                        break;
                    case 5:
                        restockInteractive(machine);
                        break;
                    case 6:
                        System.out.println("Thank you for using the vending machine!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

            System.out.println("\n📍 Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        VENDING MACHINE MENU              ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. View Products                         ║");
        System.out.println("║ 2. Select Product                        ║");
        System.out.println("║ 3. Insert Money                          ║");
        System.out.println("║ 4. Check Status                          ║");
        System.out.println("║ 5. Restock (Admin)                       ║");
        System.out.println("║ 6. Exit                                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("Choose an option (1-6): ");
    }

    private static void selectProductInteractive(VendingMachine machine) {
        machine.displayProducts();
        System.out.print("\n Enter product code (e.g., A1, B2): ");
        String code = scanner.nextLine().trim().toUpperCase();
        machine.selectProduct(code);
    }

    private static void insertMoneyInteractive(VendingMachine machine) {
        System.out.println("Available denominations: $1, $5, $10, $20");
        System.out.print("Enter amount to insert: $");

        try {
            int amount = Integer.parseInt(scanner.nextLine().trim());

            // Validate denomination
            if (amount == 1 || amount == 5 || amount == 10 || amount == 20) {
                machine.insertMoney(amount);
            } else {
                System.out.println("Invalid denomination. Please use: $1, $5, $10, $20");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    private static void restockInteractive(VendingMachine machine) {
        machine.displayProducts();
        System.out.print("\nEnter product code to restock: ");
        String code = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter quantity to add: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            machine.restockProduct(code, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid quantity.");
        }
    }
}
