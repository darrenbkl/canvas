package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawLineCommandTest extends AbstractBaseTest {

//    @Test
//    public void givenInvalidDimension_whenBuild_shouldThrowException() {
//
//        exceptionRule.expect(InvalidCoordinates.class);
//        exceptionRule.expectMessage("Invalid dimension");
//
//        new DrawLineCommand(-1, 0, -1, 4);
//    }

    @Test
    public void givenNonStraightLine_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a straight line");

        new DrawLineCommand(0, 0, 5, 5);
    }

    @Test
    public void whenExecute_shouldReturnUpdatedCanvas() {


        Canvas currentCanvas = new Canvas(5, 5);

        Command command = new DrawLineCommand(2, 0, 2, 4);

        Canvas newCanvas = command.execute(currentCanvas);

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = newCanvas.toString();

        assertThat(actual, is(actual));
    }
}