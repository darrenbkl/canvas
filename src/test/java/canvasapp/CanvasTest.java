package canvasapp;

import canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class CanvasTest extends AbstractBaseTest {

    @Test
    public void buildCanvas() {

        Canvas canvas = new Canvas(10, 20);

        assertThat(canvas.getW(), is(10));
        assertThat(canvas.getH(), is(20));
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
    public void givenValidCoordinate_whenCheckIsWithinCanvas_shouldReturnTrue() {
        Canvas canvas = new Canvas(10, 5);
        boolean actual = canvas.isWithinCanvas(1, 1);

        assertThat(actual, is(true));
    }

    @Test
    public void givenInvalidCoordinate_whenCheckIsWithinCanvas_shouldReturnTrue() {
        Canvas canvas = new Canvas(10, 5);
        boolean actual = canvas.isWithinCanvas(11, 1);

        assertThat(actual, is(false));
    }

    @Test
    public void givenCanvas_whenPrint_shouldReturnStringRepresentation() {

        Canvas canvas = new Canvas(3, 3);

        StringBuilder sb = new StringBuilder();
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("|   |");sb.append(System.getProperty("line.separator"));
        sb.append("-----");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = canvas.toString();

        assertThat(actual, is(sb.toString()));
    }

    @Test
    public void givenValidPoint_whenDraw_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        Point point = new Point(2, 2);

        Canvas updatedCanvas = canvas.draw(point, 'x');

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = updatedCanvas.toString();

        assertThat(actual, is(expected));
        assertThat(updatedCanvas.isBorder(2, 2), is(true));
    }

    @Test
    public void givenValidPoints_whenDraw_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 0));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        points.add(new Point(2, 3));
        points.add(new Point(2, 4));

        Canvas updatedCanvas = canvas.draw(points, 'x');

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = updatedCanvas.toString();

        assertThat(actual, is(expected));
        assertThat(updatedCanvas.isBorder(2, 0), is(true));
        assertThat(updatedCanvas.isBorder(2, 1), is(true));
        assertThat(updatedCanvas.isBorder(2, 2), is(true));
        assertThat(updatedCanvas.isBorder(2, 3), is(true));
        assertThat(updatedCanvas.isBorder(2, 4), is(true));
    }

    @Test
    public void givenOutOfBoundPoints_whenDraw_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas canvas = new Canvas(5, 5);

        List<Point> points = new ArrayList<>();
        points.add(new Point(-1, 0));

        canvas.draw(points, 'x');
    }

    @Test
    public void givenValidPoint_whenPaint_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        Point point = new Point(2, 2);

        Canvas updatedCanvas = canvas.paint(point, 'o');

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("|     |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = updatedCanvas.toString();

        assertThat(actual, is(expected));
        assertThat(updatedCanvas.isPaint(2, 2, 'o'), is(true));

    }

    @Test
    public void givenValidPoints_whenPaint_shouldReturnUpdatedCanvas() {

        Canvas canvas = new Canvas(5, 5);

        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 0));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        points.add(new Point(2, 3));
        points.add(new Point(2, 4));

        Canvas updatedCanvas = canvas.paint(points, 'o');

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  o  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        String actual = updatedCanvas.toString();

        assertThat(actual, is(expected));
        assertThat(canvas, not(updatedCanvas));

        assertThat(updatedCanvas.isPaint(2, 0, 'o'), is(true));
        assertThat(updatedCanvas.isPaint(2, 1, 'o'), is(true));
        assertThat(updatedCanvas.isPaint(2, 2, 'o'), is(true));
        assertThat(updatedCanvas.isPaint(2, 3, 'o'), is(true));
        assertThat(updatedCanvas.isPaint(2, 4, 'o'), is(true));
    }

    @Test
    public void givenOutOfBoundPoints_whenPaint_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must be within canvas dimension");

        Canvas canvas = new Canvas(5, 5);

        List<Point> points = new ArrayList<>();
        points.add(new Point(-1, 0));

        canvas.paint(points, 'o');
    }
}