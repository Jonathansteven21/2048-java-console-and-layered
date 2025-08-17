package com.example.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Simple {
    static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4, EXIT = 5;
    static final int SIZE = 3;

    public static void main(String[] args) throws IOException {
        int[] bigger = {0};
        Integer[][] matrix = new Integer[SIZE][SIZE];

        // Initialize board
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                matrix[i][j] = 0;

        int option = 0;
        int[] score = {0};
        boolean gameOver = false;

        printGame(matrix, getSize(bigger[0], 0), score);

        while (option != EXIT) {
            option = menu();
            clearScreen();

            if (option < UP || option > EXIT) {
                System.out.println("Invalid option. Please choose a valid direction or exit.");
            } else if (option == EXIT) {
                System.out.println("Exiting the game.");
                printGameOver(score);
            } else {
                gameOver = move(option, matrix, bigger, score);
                printGame(matrix, getSize(bigger[0], 0), score);
                if (gameOver) {
                    System.out.println("Game Over! No more moves available.");
                    printGameOver(score);
                    break;
                }
            }
        }
    }

    public static void printGameOver(int[] score) throws IOException {
        System.out.println("Final Game Score: " + score[0]);
        System.out.println("Press Enter to exit.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        clearScreen();
        System.out.println("Thank you for playing!");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getSize(int number, int size) {
        size++;
        number = number / 10;
        return number > 0 ? getSize(number, size) : size;
    }

    public static String printNumber(int number, int bigger) {
        int size = getSize(number, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bigger - size; i++) {
            sb.append(" ");
        }
        sb.append(number);
        return sb.toString();
    }

    public static void printGame(Integer[][] matrix, int bigger, int[] score) {
        System.out.println("Score: " + score[0]);
        printGrid(matrix, bigger);
    }

    public static void printGrid(Integer[][] matrix, int bigger) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print("[" + printNumber(matrix[i][j], bigger) + "]");
            }
            System.out.println();
        }
    }

    public static int menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Up");
        System.out.println("2. Down");
        System.out.println("3. Left");
        System.out.println("4. Right");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }

    public static boolean move(int direction, Integer[][] matrix, int[] bigger, int[] score) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int dx = direction < 3 ? 0 : -2 * direction + 7;
        int dy = direction > 2 ? 0 : -2 * direction + 3;

        int lines = (dx != 0) ? cols : rows;
        List<String> zeros = new ArrayList<>();

        for (int index = 0; index < lines; index++) {
            int x = (dx != 0) ? (dx > 0 ? 0 : cols - 1) : index;
            int y = (dy != 0) ? (dy > 0 ? 0 : rows - 1) : index;

            int lastNumber = -1;
            int lx = -1, ly = -1;
            int fx = -1, fy = -1;

            while (x >= 0 && x < cols && y >= 0 && y < rows) {
                int current = matrix[y][x];

                if (current == 0) {
                    zeros.add(y + "," + x);
                    if (fx == -1) {
                        fx = x;
                        fy = y;
                    }
                } else if (current == lastNumber) {
                    matrix[ly][lx] *= 2;
                    matrix[y][x] = 0;
                    zeros.add(y + "," + x);
                    fx = lx + dx;
                    fy = ly + dy;

                    score[0] += current;
                    lastNumber = -1;

                    if (matrix[ly][lx] > bigger[0]) {
                        bigger[0] = matrix[ly][lx];
                    }
                } else {
                    if (fx != -1) {
                        matrix[fy][fx] = current;
                        zeros.remove(fy + "," + fx);
                        matrix[y][x] = 0;
                        zeros.add(y + "," + x);

                        lx = fx;
                        ly = fy;
                        fx += dx;
                        fy += dy;
                    } else {
                        lx = x;
                        ly = y;
                    }

                    lastNumber = current;
                }

                x += dx;
                y += dy;
            }
        }

        System.out.println("Finished move in direction: " + direction);
        return !addRandomTile(matrix, zeros);
    }

    public static boolean addRandomTile(Integer[][] matrix, List<String> zeros) {
        if (zeros.isEmpty()) {
            System.out.println("No empty spaces to add a new tile.");
            return false;
        }

        int randomIndex = (int) (Math.random() * zeros.size());
        String[] position = zeros.get(randomIndex).split(",");
        int y = Integer.parseInt(position[0]);
        int x = Integer.parseInt(position[1]);

        matrix[y][x] = Math.random() < 0.9 ? 2 : 4;
        return true;
    }
}
