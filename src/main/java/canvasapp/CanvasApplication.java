package canvasapp;

import canvasapp.context.DrawingContext;

import java.util.Scanner;

public class CanvasApplication {

    private static final char LINE_COLOR = 'x';

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        CanvasHandler canvasHandler = new CanvasHandler(new DrawingContext());

        while (true) {

            System.out.print("Enter command [C, L, R, B, Q]: ");

            String[] input = in.nextLine().trim().split("\\s+");
            String command = input[0];

            switch (command) {

                case "C":
                    canvasHandler.create(input);
                    break;
                case "L":
                case "R":
                    canvasHandler.draw(input);
                    break;
                case "B":
                    canvasHandler.paint(input);
                    break;
                case "Q":
                    System.out.println("Bye");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }
}