package com.zuhlke.canvasapp.drawable;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.drawable.shape.Line;
import com.zuhlke.canvasapp.drawable.shape.Rect;
import com.zuhlke.canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasTest extends AbstractBaseTest {

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
    public void givenOversizeDimension_whenBuildCanvas_shouldThrowException() {
        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Invalid canvas dimension");

        new Canvas(1001, 1001);
    }

    @Test
    public void whenToString_shouldReturnStringRepresentation() {
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
        Canvas updatedCanvas = canvas.draw(new Line(2, 0, 2, 4), new Color('x'));
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
        canvas.draw(new Line(-1, 0, -1, 4), new Color('x'));
    }

    @Test
    public void givenValidRect_whenDrawRect_shouldReturnUpdatedCanvas() {
        Canvas canvas = new Canvas(5, 5);
        Canvas updatedCanvas = canvas.draw(new Rect(1, 1, 3, 3), new Color('x'));
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
        canvas.draw(new Rect(-1, 0, 4, 4), new Color('x'));
    }

    @Test
    public void givenValidPointAndColor_whenFill_shouldReturnUpdatedCanvas() {
        Canvas canvas = new Canvas(3, 3);
        Canvas updatedCanvas = canvas.fill(new Point(1, 2), new Color('o'));
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
        canvas.fill(new Point(-1, 0), new Color('o'));
    }
}