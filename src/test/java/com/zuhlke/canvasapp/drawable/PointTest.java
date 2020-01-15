package com.zuhlke.canvasapp.drawable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void givenPoints_whenCheckIsLargerThan_returnTrue() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        boolean largerThan = p2.isLargerThan(p1);

        assertThat(largerThan, is(true));
    }

    @Test
    public void givenPoints_whenCheckIsLargerThan_returnFalse() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        boolean largerThan = p1.isLargerThan(p2);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenEqualPoints_whenCheckIsLargerThan_returnFalse() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        boolean largerThan = p1.isLargerThan(p2);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenPoints_whenCheckIsSmallerThan_returnFalse() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        boolean largerThan = p2.isSmallerThan(p1);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenPoints_whenCheckIsSmallerThan_returnTrue() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        boolean largerThan = p1.isSmallerThan(p2);

        assertThat(largerThan, is(true));
    }

    @Test
    public void givenEqualPoints_whenCheckIsSmallerThan_returnFalse() {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        boolean largerThan = p1.isSmallerThan(p2);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenPoints_whenCheckIsBetweenInclusive_returnTrue() {
        Point subject = new Point(1, 5);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);

        boolean betweenInclusive = subject.isBetweenInclusive(p1, p2);

        assertThat(betweenInclusive, is(true));
    }

    @Test
    public void givenPoints_whenCheckIsBetweenInclusive_returnFalse() {
        Point subject = new Point(1, 6);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);

        boolean betweenInclusive = subject.isBetweenInclusive(p1, p2);

        assertThat(betweenInclusive, is(false));
    }

    @Test
    public void givenSamePoints_whenCheckIsEqualTo_returnTrue() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        boolean largerThan = p1.equals(p2);

        assertThat(largerThan, is(true));
    }

    @Test
    public void givenDifferentPoints_whenCheckIsEqualTo_returnFalse() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);

        boolean largerThan = p1.equals(p2);

        assertThat(largerThan, is(false));
    }


    @Test
    public void givenPoints_whenCheckOnSameXAxisWith_returnTrue() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 5);

        boolean largerThan = p1.isOnSameXAxisWith(p2);

        assertThat(largerThan, is(true));
    }

    @Test
    public void givenPoints_whenCheckOnSameXAxisWith_returnFalse() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 1);

        boolean largerThan = p1.isOnSameXAxisWith(p2);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenPoints_whenCheckOnSameYAxisWith_returnTrue() {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(3, 5);

        boolean largerThan = p1.isOnSameYAxisWith(p2);

        assertThat(largerThan, is(true));
    }

    @Test
    public void givenPoints_whenCheckOnSameYAxisWith_returnFalse() {
        Point p1 = new Point(5, 2);
        Point p2 = new Point(5, 3);

        boolean largerThan = p1.isOnSameYAxisWith(p2);

        assertThat(largerThan, is(false));
    }

    @Test
    public void givenPoints_whenGetRight_returnPointToTheRight() {
        Point actual = new Point(5, 5).right();
        Point expected = new Point(6, 5);

        assertThat(actual, is(expected));
    }

    @Test
    public void givenPoints_whenGetLeft_returnPointToTheLeft() {
        Point actual = new Point(5, 5).left();
        Point expected = new Point(4, 5);

        assertThat(actual, is(expected));
    }

    @Test
    public void givenPoints_whenGetUp_returnPointAbove() {
        Point actual = new Point(5, 5).up();
        Point expected = new Point(5, 4);

        assertThat(actual, is(expected));
    }

    @Test
    public void givenPoints_whenGetDown_returnPointBelow() {
        Point actual = new Point(5, 5).down();
        Point expected = new Point(5, 6);

        assertThat(actual, is(expected));
    }
}