package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.RectDrawable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawRectCommandTest extends AbstractBaseTest {

    @Test
    public void whenExecuteRectDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);
        Drawable drawable = new RectDrawable(1, 1, 3, 4);
        Command command = new DrawCommand(drawable, 'o');
        Canvas newCanvas = command.execute(currentCanvas);
        String actual = newCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|     |\n");
        sb.append("| ooo |\n");
        sb.append("| o o |\n");
        sb.append("| o o |\n");
        sb.append("| ooo |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }
}