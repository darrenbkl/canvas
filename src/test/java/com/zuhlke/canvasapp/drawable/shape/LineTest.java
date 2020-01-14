package com.zuhlke.canvasapp.drawable.shape;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.drawable.Point;
import com.zuhlke.canvasapp.exception.InvalidCoordinates;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class LineTest extends AbstractBaseTest {

    @Test
    public void givenPoint_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a straight line");

        new Line(0, 0, 0, 0);
    }

    @Test
    public void givenRect_whenBuild_shouldThrowException() {

        exceptionRule.expect(InvalidCoordinates.class);
        exceptionRule.expectMessage("Coordinates must form a straight line");

        new Line(0, 0, 5, 5);
    }

    @Test
    public void givenInvertedPoint_whenBuild_shouldReturnLine() {
        List<Point> expected = Arrays.asList(new Point(3, 3), new Point(3, 2), new Point(3, 1),
                                             new Point(3, 0));

        Line line = new Line(3, 3, 3, 0);
        Iterable<Point> actual = line.getPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenGetPoints_shouldReturnPointsThatRepresentLine() {
        List<Point> expected = Arrays.asList(new Point(2, 0), new Point(2, 1), new Point(2, 2),
                                             new Point(2, 3), new Point(2, 4));

        Line line = new Line(2, 0, 2, 4);
        Iterable<Point> actual = line.getPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenGetEndPoints_shouldReturnEndPoints() {
        List<Point> expected = Arrays.asList(new Point(2, 0), new Point(2, 4));

        Line line = new Line(2, 0, 2, 4);
        Iterable<Point> actual = line.getEndPoints();

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }
}