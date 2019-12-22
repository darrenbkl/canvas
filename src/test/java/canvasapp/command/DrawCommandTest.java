package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.FillDrawable;
import canvasapp.drawable.LineDrawable;
import canvasapp.drawable.RectDrawable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DrawCommandTest {

    @Test
    public void whenExecuteLineDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);
        Command command = new DrawCommand(drawable);

        Canvas newCanvas = command.execute(currentCanvas);
        String actual = newCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

        @Test
    public void whenExecuteRectDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new RectDrawable(1, 1, 3, 4, 'o');
        Command command = new DrawCommand(drawable);

        Canvas newCanvas = command.execute(currentCanvas);

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("| ooo |");sb.append(System.getProperty("line.separator"));
        sb.append("| o o |");sb.append(System.getProperty("line.separator"));
        sb.append("| o o |");sb.append(System.getProperty("line.separator"));
        sb.append("| ooo |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = newCanvas.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenExecuteFillDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(3, 3);

        Drawable drawable = new FillDrawable(1, 2, 'o');
        Command command = new DrawCommand(drawable);

        Canvas newCanvas = command.execute(currentCanvas);

        StringBuilder sb = new StringBuilder();
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = newCanvas.toString();

        assertThat(actual, is(expected));
    }
}