package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.LineDrawable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawLineCommandTest extends AbstractBaseTest {

    @Test
    public void whenExecuteLineDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);
        Command command = new DrawCommand(drawable, 'x');
        Canvas newCanvas = command.execute(currentCanvas);
        String actual = newCanvas.toString();

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
}