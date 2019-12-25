package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FillCommandTest extends AbstractBaseTest {

    @Test
    public void givenOutOfBoundPoint_whenExecute_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas existingCanvas = new Canvas(3, 3);
        Command command = new FillCommand(-1, 2, 'o');
        command.execute(existingCanvas);
    }

    @Test
    public void whenExecute_shouldReturnUpdatedCanvas() {

        Canvas existingCanvas = new Canvas(3, 3);
        Command c = new FillCommand(1, 2, 'o');
        Canvas newCanvas = c.execute(existingCanvas);
        String actual = newCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("-----\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }
}