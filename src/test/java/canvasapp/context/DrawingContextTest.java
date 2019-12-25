package canvasapp.context;

import canvasapp.Canvas;
import canvasapp.command.Command;
import canvasapp.command.CreateCanvasCommand;
import canvasapp.command.DrawCommand;
import canvasapp.command.FillCommand;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.LineDrawable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawingContextTest {

    @Test
    public void givenCreateCanvasCommand_whenExecute_thenReturnString() {

        Context context = new DrawingContext();
        Command command = new CreateCanvasCommand(5, 5);
        String actual = context.execute(command);

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|     |\n");
        sb.append("|     |\n");
        sb.append("|     |\n");
        sb.append("|     |\n");
        sb.append("|     |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenDrawCommand_whenExecute_thenReturnString() {

        Canvas canvas = new Canvas(5, 5);
        Context context = new DrawingContext(canvas);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);
        Command command = new DrawCommand(drawable, 'x');
        String actual = context.execute(command);

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenFillCommand_whenExecute_thenReturnString() {

        Canvas canvas = new Canvas(5, 5);
        Context context = new DrawingContext(canvas);

        Command command = new FillCommand(3, 3, 'o');
        String actual = context.execute(command);

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|ooooo|\n");
        sb.append("|ooooo|\n");
        sb.append("|ooooo|\n");
        sb.append("|ooooo|\n");
        sb.append("|ooooo|\n");
        sb.append("-------\n");
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