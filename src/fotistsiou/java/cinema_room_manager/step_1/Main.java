package fotistsiou.java.cinema_room_manager.step_1;

/**
 * Print seats
 * -----------
 * Description
 * There are many enjoyable activities on this funny little planet Earth, and still, the happiest, simplest, and most
 * fulfilling one is probably going to the movies. Going with friends, going with loved ones, experiencing a whole new
 * adventure from the safety of a cinema seat, surrounded by like-minded fellow travelers.
 * As a beginner developer, you can contribute to creating this wonderful cinema experience. Your good friends asked you
 * to help them create an application for a cinema theatre where people can get tickets, reserve seats, and enjoy their
 * movie night.
 * -----------
 * Objectives
 * There is not a lot of space in our new cinema theatre, so you need to take into account the restrictions on the number
 * of seats. Your friends said that the room would fit 7 rows of 8 seats. Your task is to help them visualize the seating
 * arrangement by printing the scheme to the console.
 * Your output should be like in the example below and should contain 9 lines, the title "Cinema:" - 1 line and
 * room size - 8 lines.
 */

public class Main {

    public static void main(String[] args) {
        String title = "Cinema";
        int row = 7;
        int col = 8;
        char[][] cinema = new char[row][col];

        for (int i = 0; i < row; i++) {
            java.util.Arrays.fill(cinema[i], 'S');
        }

        System.out.println(title + ":");
        printColumnNumbers(col);
        printCinemaGrid(cinema, row, col);
    }

    private static void printColumnNumbers(int col) {
        System.out.print("  ");
        for (int i = 1; i <= col; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void printCinemaGrid(char[][] cinema, int row, int col) {
        for (int i = 0; i < row; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < col; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
}
