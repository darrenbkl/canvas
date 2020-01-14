package com.zuhlke.canvasapp;

import com.zuhlke.canvasapp.command.Command;
import com.zuhlke.canvasapp.command.CommandFactory;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.CanvasApplicationException;

import java.io.InputStream;
import java.util.Scanner;

public class CanvasApplication {

    public static void main(String[] args) {
        CanvasApplication canvasApplication = new CanvasApplication();
        canvasApplication.run(System.in);
    }

    public void run(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        Canvas canvas = null;
        CommandFactory commandFactory = new CommandFactory();

        System.out.print("Enter command [C, L, R, B, Q]: ");

        while (in.hasNext()) {

            String[] input = in.nextLine()
                               .trim()
                               .split("\\s+");

            try {
                Command command = commandFactory.createCommand(input);
                canvas = command.execute(canvas);
                System.out.println(canvas.toString());
            } catch (CanvasApplicationException cae) {
                System.out.println(cae.getMessage());
            } catch (Exception e) {
                System.out.println("Application error");
            }

            System.out.print("Enter command [C, L, R, B, Q]: ");
        }
    }
}