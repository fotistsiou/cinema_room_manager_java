package fotistsiou.java.cinema_room_manager.step_3;

import java.util.Scanner;

/**
 * Set the ticket price
 * --------------------
 * Description
 * When choosing a ticket you are guided not only by your space preference but also by your finances. Let's implement
 * the opportunity to check the ticket price and see the reserved seat.
 * --------------------
 * Objectives
 * Read two positive integer numbers that represent the number of rows and seats in each row and print the seating
 * arrangement like in the first stage. Then, read two integer numbers from the input: a row number and a seat number
 * in that row. These numbers represent the coordinates of the seat according to which the program should print the
 * ticket price. The ticket price is determined by the same rules as the previous stage:
 * - If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
 * - In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
 *   Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows,
 *   and the second half is the last 5 rows.
 * After that, the program should print out all the seats in the screen room as shown in the example and mark the chosen
 * seat by the B symbol. Finally, it should print the ticket price and stop. Note that in this project, the number of
 * rows and seats won't be greater than 9.
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

        // Print the initial scheme of the cinema
        printCinemaGrid(cinema, title);

        // Read a specific seat
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        // Calculate and print ticket price
        String ticketPrice = calculateTicketPrice(rows, seats, row);
        System.out.println("Ticket price: " + ticketPrice);

        // Hold the seat and print the new scheme of the cinema
        holdSeat(cinema, row, seat);
        printCinemaGrid(cinema, title);

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

    static void printCinemaGrid(char[][] cinema, String title) {
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
}
