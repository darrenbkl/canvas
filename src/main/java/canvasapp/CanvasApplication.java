package canvasapp;

import canvasapp.context.DrawingContext;
import canvasapp.handler.Handler;

import java.util.Scanner;

public class CanvasApplication {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Handler canvasHandler = new Handler(new DrawingContext());

        while (true) {

            System.out.print("Enter command [C, L, R, B, Q]: ");

            String[] input = in.nextLine().trim().split("\\s+");
            String command = input[0];

            try {
                switch (command) {
                    case "C":
                        String output = canvasHandler.create(input);
                        System.out.println(output);
                        break;
                    case "L":
                    case "R":
                        output = canvasHandler.draw(input);
                        System.out.println(output);
                        break;
                    case "B":
                        output = canvasHandler.paint(input);
                        System.out.println(output);
                        break;
                    case "Q":
                        System.out.println("Bye");
                        return;
                    default:
                        System.out.println("Wrong command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}