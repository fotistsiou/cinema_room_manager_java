package fotistsiou.java.cinema_room_manager.step_4;

import java.util.Scanner;

/**
 * Add menu to your application
 * ----------------------------
 * Description
 * The theatre is getting popular, and the customers started complaining that it's not practical that the program stops
 * after buying one ticket. Let's add a menu that will allow them to buy tickets and display the current state of the
 * seating arrangement when needed.
 * ----------------------------
 * Objectives
 * At the start, your program should read two positive integer numbers that represent the number of rows and seats in
 * each row. Then, it should print a menu with the following three items:
 * - Show the seats should print the current seating arrangement. The empty seats should be marked with an S symbol,
 *   and taken seats are marked with a B symbol.
 * - Buy a ticket should read the seat coordinates from the input and print the ticket price like in the previous stage.
 *   After that, the chosen seat should be marked with a B when the item Show the seats is called.
 * - Exit should stop the program.
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the rows and seats and create the cinema
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String title = "Cinema";
        char[][] cinema = new char[rows][seats];
        initSeats(cinema);

        int action;
        do {
            action = getAction(scanner);
            switch (action) {
                case 1:
                    showTheSeats(cinema, title);
                    break;
                case 2:
                    buyATicket(cinema, scanner);
                    break;
                default:
                    break;
            }
        } while (action != 0);

        scanner.close();
    }

    static void initSeats(char[][] cinema) {
        for (char[] seats : cinema) {
            java.util.Arrays.fill(seats, 'S');
        }
    }

    static void printSeatsNumbers(int seats) {
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void showTheSeats(char[][] cinema, String title) {
        System.out.println(title + ":");
        printSeatsNumbers(cinema[0].length);
        for (int i = 0; i < cinema.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    static String calculateTicketPrice(int rows, int seats, int row) {
        int totalSeats = rows * seats;
        int firstRows = rows / 2;
        return totalSeats <= 60
                ? "$10" : row <= firstRows
                ? "$10" : "$8";
    }

    static void holdSeat(char[][] cinema, int row, int seat) {
        cinema[row-1][seat-1] = 'B';
    }

    static void buyATicket(char[][] cinema, Scanner scanner) {
        // Read a specific seat
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        // Calculate and print ticket price
        String ticketPrice = calculateTicketPrice(cinema.length, cinema[0].length, row);
        System.out.println("Ticket price: " + ticketPrice);

        // Hold the seat
        holdSeat(cinema, row, seat);
    }

    static int getAction(Scanner scanner) {
        System.out.print("""
                1. Show the seats
                2. Buy a ticket
                0. Exit
                """);
        return scanner.nextInt();
    }
}
