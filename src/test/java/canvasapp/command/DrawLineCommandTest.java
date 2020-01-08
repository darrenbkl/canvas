package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.drawable.Canvas;
import canvasapp.exception.IllegalCanvasStateException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawLineCommandTest extends AbstractBaseTest {

    @Test
    public void givenNullCanvas_whenExecute_shouldThrowException() {

        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        Command command = new DrawLineCommand(3,1, 3, 5);
        Canvas currentCanvas = null;
        command.execute(currentCanvas);
    }

    @Test
    public void givenValidInput_whenExecuteLineDrawable_shouldReturnUpdatedCanvas() {

        Command command = new DrawLineCommand(3,1, 3, 5);

        Canvas currentCanvas = new Canvas(5, 5);
        Canvas result = command.execute(currentCanvas);
        String actual = result.toString();

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