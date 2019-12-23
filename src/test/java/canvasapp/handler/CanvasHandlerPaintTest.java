package canvasapp.handler;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.Point;
import canvasapp.context.DrawingContext;
import canvasapp.handler.Handler;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasHandlerPaintTest extends AbstractBaseTest {
    
    @Test
    public void givenValidInput_whenPaint_shouldReturnCanvasString() {

        String[] input = {"B", "1", "1", "o"};

        Canvas canvas = prepareCanvas(new Canvas(5, 5));
        Handler handler = new Handler(new DrawingContext(canvas));

        String actual = handler.paint(input);

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|oox  |");sb.append(System.getProperty("line.separator"));
        sb.append("|oox  |");sb.append(System.getProperty("line.separator"));
        sb.append("|oox  |");sb.append(System.getProperty("line.separator"));
        sb.append("|oox  |");sb.append(System.getProperty("line.separator"));
        sb.append("|oox  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenEmptyCanvas_whenPaint_shouldThrowException() {

        exceptionRule.expect(IllegalStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        String[] input = {"B", "1", "1", "o"};

        Canvas canvas = null;
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.paint(input);
    }

    @Test
    public void givenInvalidInputLength_whenPaint_shouldThrowException() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Input length should be 4");

        String[] input = {"B", "1", "1"};

        Canvas canvas = prepareCanvas(new Canvas(5, 5));
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.paint(input);
    }

    @Test
    public void givenInvalidInputType_whenPaint_shouldThrowException() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid integer");

        String[] input = {"B", "1", "x", "o"};

        Canvas canvas = prepareCanvas(new Canvas(5, 5));
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.paint(input);
    }

    @Test
    public void givenInvalidInputType_whenPaint_shouldThrowException2() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Color must be a single character");

        String[] input = {"B", "1", "1", "oo"};

        Canvas canvas = prepareCanvas(new Canvas(5, 5));
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.paint(input);
    }

    private Canvas prepareCanvas(Canvas canvas) {

        return canvas.drawLine(2, 0, 2, 4, 'x');
//        return canvas.draw(Arrays.asList(
//                new Point(2, 0),
//                new Point(2, 1),
//                new Point(2, 2),
//                new Point(2, 3),
//                new Point(2, 4)
//        ), 'x');
    }
}