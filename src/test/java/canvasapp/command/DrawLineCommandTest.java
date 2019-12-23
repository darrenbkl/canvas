package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.LineDrawable;
import canvasapp.exception.InvalidCoordinates;
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

//    @Test
//    public void givenNonStraightLine_whenBuild_shouldThrowException() {
//
//        exceptionRule.expect(InvalidCoordinates.class);
//        exceptionRule.expectMessage("Coordinates must form a straight line");
//
//        new DrawLineCommand(0, 0, 5, 5);
//    }

    @Test
    public void whenExecuteLineDrawable_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);
        Command command = new DrawCommand(drawable, 'x');

        Canvas newCanvas = command.execute(currentCanvas);
        String actual = newCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-------");
        sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");
        sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");
        sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");
        sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");
        sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");
        sb.append(System.getProperty("line.separator"));
        sb.append("-------");
        sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }
}