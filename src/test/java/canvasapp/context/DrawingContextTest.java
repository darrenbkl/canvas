package canvasapp.context;

import canvasapp.Canvas;
import canvasapp.command.Command;
import canvasapp.command.DrawCommand;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.LineDrawable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawingContextTest {

    @Test
    public void givenLineDrawable_whenExecute_returnString() {

        Canvas canvas = new Canvas(5, 5);
        Context context = new DrawingContext(canvas);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);
        Command command = new DrawCommand(drawable);
        String actual = context.execute(command);

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
    public void givenNullCanvas_whenGetIsInitialized_returnFalse() {

        Context context = new DrawingContext(null);

        assertThat(context.isInitialized(), is(false));
    }

    @Test
    public void givenNonnullCanvas_whenGetIsInitialized_returnFalse() {

        Canvas canvas = new Canvas(10, 10);
        Context context = new DrawingContext(canvas);

        assertThat(context.isInitialized(), is(true));
    }
}