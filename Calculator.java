import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> arithmeticHistory = new ArrayList<>();
    static ArrayList<String> functionHistory = new ArrayList<>();
    static ArrayList<Integer> arithmeticResults = new ArrayList<>();
    static ArrayList<Double> functionResults = new ArrayList<>();

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        Scanner scan = new Scanner(System.in);
       System.out.println("\n=========================");
System.out.println("       CALCULATOR MENU");
System.out.println("=========================");
System.out.println("1. Perform Basic Arithmetic Operations");
System.out.println("2. Use Additional Mathematical Functions");
System.out.println("3. View Calculation History");
System.out.println("4. Exit");
System.out.println("=========================");
System.out.print("Please choose an option (1-4): ");


        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1 -> performArithmeticOperation();
            case 2 -> performFunctionOperation();
            case 3 -> reviewHistory();
            case 4 -> {
                System.out.println("Exiting...");
                return;
            }
            default -> {
                System.out.println("Invalid option! Please try again.");
                displayMenu();
            }
        }
    }

    public static void performArithmeticOperation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("This option supports addition (+), subtraction (-), multiplication (*), division (/), and modulus (%).");
        System.out.print("Enter arithmetic operation (e.g., 5 + 3): ");
        String input = scan.nextLine();
        arithmeticHistory.add(input);
        
        String[] tokens = input.split(" ");
        int operand1 = Integer.parseInt(tokens[0]);
        String operator = tokens[1];
        int operand2 = Integer.parseInt(tokens[2]);

        int result = 0;
        boolean isValid = true;

        switch (operator) {
            case "+" -> result = operand1 + operand2;
            case "-" -> result = operand1 - operand2;
            case "*" -> result = operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) {
                    System.out.println("Error: Division by zero is undefined.");
                    arithmeticResults.add(-1);
                    isValid = false;
                } else {
                    result = operand1 / operand2;
                }
            }
            case "%" -> {
                if (operand2 == 0) {
                    System.out.println("Error: Division by zero is undefined.");
                    arithmeticResults.add(-1);
                    isValid = false;
                } else {
                    result = operand1 % operand2;
                }
            }
            default -> {
                System.out.println("Invalid operator!");
                isValid = false;
            }
        }

        if (isValid) {
            System.out.println("Result: " + result);
            arithmeticResults.add(result);
        }

        displayMenu();
    }

    public static void performFunctionOperation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("This option supports power (pow), square root (sqrt), absolute value (abs), and rounding (round).");
        System.out.print("Enter your operation (e.g., abs -5): ");
        String input = scan.nextLine();
        functionHistory.add(input);
        
        String[] tokens = input.split(" ");
        double result = 0.0;
        boolean isValid = true;

        switch (tokens[0]) {
            case "abs" -> {
                double number = Double.parseDouble(tokens[1]);
                result = Math.abs(number);
            }
            case "pow" -> {
                double base = Double.parseDouble(tokens[0]);
                double exponent = Double.parseDouble(tokens[2]);
                result = Math.pow(base, exponent);
            }
            case "sqrt" -> {
                double number = Double.parseDouble(tokens[1]);
                if (number < 0) {
                    System.out.println("Error: Square root of a negative number is undefined.");
                    functionResults.add(-1.0);
                    isValid = false;
                } else {
                    result = Math.sqrt(number);
                }
            }
            case "round" -> {
                double number = Double.parseDouble(tokens[1]);
                result = Math.round(number);
            }
            default -> {
                System.out.println("Invalid function!");
                isValid = false;
            }
        }

        if (isValid) {
            System.out.println("Result: " + result);
            functionResults.add(result);
        }

        displayMenu();
    }

    public static void reviewHistory() {
        boolean hasHistory = false;

        if (!arithmeticHistory.isEmpty()) {
            System.out.println("Arithmetic operation history:");
            for (int i = 0; i < arithmeticHistory.size(); i++) {
                System.out.println(arithmeticHistory.get(i) + " = " + arithmeticResults.get(i));
            }
            hasHistory = true;
        }

        if (!functionHistory.isEmpty()) {
            System.out.println("Function operation history:");
            for (int i = 0; i < functionHistory.size(); i++) {
                System.out.println(functionHistory.get(i) + " = " + functionResults.get(i));
            }
            hasHistory = true;
        }

        if (!hasHistory) {
            System.out.println("Your history is empty. Please perform some operations first.");
        }

        displayMenu();
    }
}