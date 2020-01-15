package com.zuhlke.canvasapp;

import com.zuhlke.canvasapp.command.Command;
import com.zuhlke.canvasapp.command.CommandFactory;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.CanvasApplicationException;

import java.io.InputStream;
import java.util.Scanner;

public class CanvasApplication implements DrawingContext {

    private boolean exit;
    private Canvas canvas;

    public CanvasApplication(Canvas canvas, boolean exit) {
        this.canvas = canvas;
        this.exit = exit;
    }

    public static void main(String[] args) {
        CanvasApplication canvasApplication = new CanvasApplication(null, false);
        canvasApplication.run(System.in);
    }

    public void run(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        CommandFactory commandFactory = new CommandFactory();

        System.out.print("Enter command [C, L, R, B, Q]: ");

        while (in.hasNext()) {

            String[] input = in.nextLine()
                               .trim()
                               .split("\\s+");

            try {
                Command command = commandFactory.createCommand(input, this);
                canvas = command.execute();

                if (exit) {
                    return;
                }

                System.out.println(canvas.toString());
            } catch (CanvasApplicationException cae) {
                System.out.println(cae.getMessage());
            } catch (Exception e) {
                System.out.println("Application error");
            }

            System.out.print("Enter command [C, L, R, B, Q]: ");
        }
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void exit() {
        exit = true;
    }
}