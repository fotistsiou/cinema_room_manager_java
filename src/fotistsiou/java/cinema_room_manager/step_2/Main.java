package fotistsiou.java.cinema_room_manager.step_2;

import java.util.Scanner;

/**
 * Calculate the profit
 * --------------------
 * Description
 * Good job: our friends really liked your program! Now they want to expand their theater and add screen rooms with more
 * seats. However, this is expensive, so they want to make sure this will pay off. Help them calculate the profit from
 * all the sold tickets depending on the number of available seats.
 * --------------------
 * Objectives
 * In this stage, you need to read two positive integer numbers from the input: the number of rows and the number of
 * seats in each row. The ticket price is determined by the following rules:
 * - If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
 * - In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
 *   Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows,
 *   and the second half is the other 5 rows.
 * Calculate the profit from the sold tickets depending on the number of seats and print the result as shown in the
 * examples below. After that, your program should stop. Note that in this project, the number of rows and seats won't
 * be greater than 9.
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        int income = calculateIncome(rows, seats);

        System.out.println("Total income:");
        System.out.println("$" + income);

        scanner.close();
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
}
