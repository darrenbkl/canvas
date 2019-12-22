//package canvasapp.command;
//
//import canvasapp.AbstractBaseTest;
//import canvasapp.Canvas;
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//public class PaintCommandTest extends AbstractBaseTest {
//
////    @Test
////    public void givenInvalidCoordinate_whenExecute_shouldThrowException() {
////
////        exceptionRule.expect(InvalidCoordinates.class);
////        exceptionRule.expectMessage("Coordinates must be within canvas dimension");
////
////        Canvas existingCanvas = new Canvas(3, 3);
////
////        Command command = new PaintCommand(-1, 2, 'o');
////
////        command.execute(existingCanvas);
////    }
//
//
//    @Test
//    public void whenExecute_shouldReturnUpdatedCanvas() {
//
//        Canvas existingCanvas = new Canvas(3, 3);
//
//        Command c = new PaintCommand(1, 2, 'o');
//
//        Canvas newCanvas = c.execute(existingCanvas);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("-----");sb.append(System.getProperty("line.separator"));
//        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
//        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
//        sb.append("|ooo|");sb.append(System.getProperty("line.separator"));
//        sb.append("-----");sb.append(System.getProperty("line.separator"));
//        String expected = sb.toString();
//
//        String actual = newCanvas.toString();
//
//        assertThat(actual, is(expected));
//    }
//}