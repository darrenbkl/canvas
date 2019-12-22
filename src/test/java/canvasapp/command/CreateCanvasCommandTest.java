package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateCanvasCommandTest extends AbstractBaseTest {

    @Test
    public void whenExecute_shouldReturnNewCanvas() {

        Command command = new CreateCanvasCommand(3, 4);

        Canvas currentCanvas = new Canvas(10, 10);
        Canvas newCanvas = command.execute(currentCanvas);
        String actual = newCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenZeroDimension_whenBuildCanvas_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Invalid canvas dimension");

        Command command = new CreateCanvasCommand(0, 0);

        Canvas currentCanvas = new Canvas(10, 10);
        command.execute(currentCanvas);
    }
}