package canvasapp.drawable;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LineDrawableTest extends AbstractBaseTest {

    @Test
    public void givenPoint_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a straight line");

        new LineDrawable(0, 0, 5, 5);
    }

    @Test
    public void givenRect_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a straight line");

        new LineDrawable(5, 5, 5, 5);
    }

    @Test
    public void givenPoint2SmallerThanPoint1_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Point 2 must be larger than point 1");

        new LineDrawable(2, 4, 2, 0);
    }

    @Test
    public void whenDraw_shouldReturnUpdatedCanvas() {


        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new LineDrawable(2, 0, 2, 4);

        Canvas newCanvas = drawable.draw(currentCanvas);
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
}