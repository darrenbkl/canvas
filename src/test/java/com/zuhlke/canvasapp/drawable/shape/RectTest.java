package com.zuhlke.canvasapp.drawable.shape;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.drawable.Point;
import com.zuhlke.canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class
RectTest extends AbstractBaseTest {

    @Test
    public void givenPoint_whenBuild_shouldThrowException() {
        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a rectangle");

        new Rect(1, 1, 1, 1);
    }

    @Test
    public void givenLine_whenBuild_shouldThrowException() {
        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a rectangle");

        new Rect(1, 1, 5, 1);
    }

    @Test
    public void givenInvertedPoint_whenBuild_shouldReturnRect() {
        List<Point> expected = Arrays.asList(new Point(1, 1), new Point(2, 1), new Point(3, 1),
                                             new Point(1, 2), new Point(3, 2),
                                             new Point(1, 3), new Point(2, 3), new Point(3, 3));

        Rect rect = new Rect(3, 3, 1, 1);
        Iterable<Point> actual = rect.getPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenGetPoints_shouldReturnPointsThatRepresentRect() {
        List<Point> expected = Arrays.asList(new Point(1, 1), new Point(2, 1), new Point(3, 1),
                                             new Point(1, 2), new Point(3, 2),
                                             new Point(1, 3), new Point(2, 3), new Point(3, 3));

        Rect rect = new Rect(1, 1, 3, 3);
        Iterable<Point> actual = rect.getPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenGetEndPoints_shouldReturnEndPoints() {
        List<Point> expected = Arrays.asList(new Point(1, 1), new Point(3, 3));

        Rect rect = new Rect(1, 1, 3, 3);
        Iterable<Point> actual = rect.getEndPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }
}