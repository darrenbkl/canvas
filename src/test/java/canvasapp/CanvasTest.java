package canvasapp;

import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasTest extends AbstractBaseTest {

    @Test
    public void givenValidDimension_whenBuildCanvas_thenReturnCanvas() {

        Canvas canvas = new Canvas(10, 20);

        assertThat(canvas.getWidth(), is(10));
        assertThat(canvas.getHeight(), is(20));
    }

    @Test
    public void givenZeroDimension_whenBuildCanvas_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Invalid canvas dimension");

        new Canvas(0, 0);
    }

    @Test
    public void givenNegativeDimension_whenBuildCanvas_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Invalid canvas dimension");

        new Canvas(-1, -1);
    }

    @Test
    public void givenEmptyCanvas_whenPrint_shouldReturnStringRepresentation() {

        Canvas canvas = new Canvas(3, 3);
        String actual = canvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----\n");
        sb.append("|   |\n");
        sb.append("|   |\n");
        sb.append("|   |\n");
        sb.append("-----\n");
        String expected = sb.toString();

        assertThat(actual, is(sb.toString()));
    }

    @Test
    public void givenValidLine_whenDrawLine_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        Canvas updatedCanvas = canvas.drawLine(2, 0, 2, 4, 'x');
        String actual = updatedCanvas.toString();

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
    public void givenOutOfBoundLine_whenDrawLine_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas canvas = new Canvas(5, 5);

        canvas.drawLine(-1, 0, -1, 4, 'x');
    }

    @Test
    public void givenValidRect_whenDrawRect_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        Canvas updatedCanvas = canvas.drawRect(1, 1, 3, 3, 'x');
        String actual = updatedCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|     |\n");
        sb.append("| xxx |\n");
        sb.append("| x x |\n");
        sb.append("| xxx |\n");
        sb.append("|     |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenOutOfBoundRect_whenDrawRect_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas canvas = new Canvas(5, 5);

        canvas.drawLine(-1, 0, -1, 4, 'x');
    }

    @Test
    public void givenValidPoint_whenFill_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(3, 3);

        Canvas updatedCanvas = canvas.fill(1, 2, 'o');
        String actual = updatedCanvas.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("-----\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenOutOfBoundPoint_whenFill_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas canvas = new Canvas(5, 5);

        canvas.fill(-1, 0, 'o');
    }
}