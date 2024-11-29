package fotistsiou.java.cinema_room_manager.step_5;

import java.util.Scanner;

/**
 * Manage stats and make your app error-prone
 * ------------------------------------------
 * Description
 * Running a cinema theatre is no easy business. To help our friends, let's add statistics to your program. The stats will
 * show the current income, total income, the number of available seats, and the percentage of occupancy.
 * In addition, our friends asked you to take care of a small inconvenience: it's not good when a user can buy a ticket
 * that has already been purchased by another user. Let's fix this!
 * ------------------------------------------
 * Objectives
 * Now your menu should look like this:
 * 1. Show the seats
 * 2. Buy a ticket
 * 3. Statistics
 * 0. Exit
 * When the item Statistics is chosen, your program should print the following information:
 * - The number of purchased tickets;
 * - The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
 * - Current income;
 * - The total income that shows how much money the theatre will get if all the tickets are sold.
 * The rest of the menu items should work the same way as before, except the item Buy a ticket shouldn't allow a user to
 * buy a ticket that has already been purchased.
 * If a user chooses an already taken seat, print That ticket has already been purchased! and ask them to enter different
 * seat coordinates until they pick an available seat. Of course, you shouldn't allow coordinates that are out of bounds.
 * If this happens, print Wrong input! and ask to enter different seat coordinates until the user picks an available seat.
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

        // Initiate statistics
        int[] states = new int[2];

        int action;
        do {
            action = getAction(scanner);
            switch (action) {
                case 1:
                    showTheSeats(cinema, title);
                    break;
                case 2:
                    buyATicket(cinema, scanner, states);
                    break;
                case 3:
                    getStatistics(rows, seats, states);
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

    static int calculateTicketPrice(int rows, int seats, int row) {
        int totalSeats = rows * seats;
        int firstRows = rows / 2;
        return totalSeats <= 60
                ? 10 : row <= firstRows
                ? 10 : 8;
    }

    static void holdSeat(char[][] cinema, int row, int seat) {
        cinema[row-1][seat-1] = 'B';
    }

    static void buyATicket(char[][] cinema, Scanner scanner, int[] states) {
        while (true) {
            // Read a specific seat
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row > cinema.length || row <= 0 || seat > cinema[0].length || seat <= 0) {
                System.out.println("Wrong input!");
                continue;
            }
            if (cinema[row-1][seat-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }

            // Calculate and print ticket price
            int ticketPrice = calculateTicketPrice(cinema.length, cinema[0].length, row);
            System.out.println("Ticket price: " + String.format("$%d", ticketPrice));

            // Hold the seat
            holdSeat(cinema, row, seat);

            // Update statistics
            states[0] += ticketPrice;
            states[1]++;

            break;
        }
    }

    static int getAction(Scanner scanner) {
        System.out.print("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """);
        return scanner.nextInt();
    }

    static int calculateIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            return totalSeats * 10;
        }
        int firstRows = rows / 2;
        int secondRows = rows - firstRows;
        return (firstRows * seats * 10) + (secondRows * seats * 8);
    }

    static void getStatistics(int rows, int seats, int[] states) {
        double totalSeats = (double) rows * seats;
        double percentage = ((double)states[1] / totalSeats) * 100;
        int totalIncome = calculateIncome(rows, seats);
        System.out.println("Number of purchased tickets: " + states[1]);
        System.out.println("Percentage: " + String.format("%.2f%%",  percentage));
        System.out.println("Current income: " + String.format("$%d", states[0]));
        System.out.println("Total income: " + String.format("$%d", totalIncome));
    }
}
