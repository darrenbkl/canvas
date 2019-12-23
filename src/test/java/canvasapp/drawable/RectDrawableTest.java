package canvasapp.drawable;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RectDrawableTest extends AbstractBaseTest {

    @Test
    public void givenPoint_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a rectangle");

        new RectDrawable(1, 1, 1, 1);
    }

    @Test
    public void givenNonRectangle_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a rectangle");

        new RectDrawable(1, 1, 5, 1);
    }

    @Test
    public void givenPoint2SmallerThanPoint1_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Point 2 must be larger than point 1");

        new RectDrawable(5, 5, 1, 1);
    }

    @Test
    public void whenExecute_shouldReturnUpdatedCanvas() {

        Canvas currentCanvas = new Canvas(5, 5);

        Drawable drawable = new RectDrawable(1, 1, 3, 4);

        Canvas newCanvas = drawable.draw(currentCanvas ,'o');

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("| ooo |");sb.append(System.getProperty("line.separator"));
        sb.append("| o o |");sb.append(System.getProperty("line.separator"));
        sb.append("| o o |");sb.append(System.getProperty("line.separator"));
        sb.append("| ooo |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = newCanvas.toString();

        assertThat(actual, is(expected));
    }
}