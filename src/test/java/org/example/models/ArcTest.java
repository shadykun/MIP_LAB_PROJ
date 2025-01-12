package org.example.models;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArcTest {

    @Test
    void setStart() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setStart(1, 2);
        assertEquals(1, arc.getStartX());
        assertEquals(2, arc.getStartY());
    }

    @Test
    void getStartX() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(0, arc.getStartX());
    }

    @Test void getStartY() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(0, arc.getStartY());
    }

    @Test
    void setEnd() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setEnd(1, 2);
        assertEquals(1, arc.getEndX());
        assertEquals(2, arc.getEndY());
    }

    @Test
    void getEndX() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(0, arc.getEndX());
    }

    @Test
    void getEndY() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(1, arc.getEndY());
    }

    @Test
    void setWeight() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setWeight(1);
        assertEquals(1, arc.getWeight());
    }

    @Test
    void getWeight() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(1, arc.getWeight());
    }

    @Test
    void setColor() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setColor(Color.red);
        assertEquals(Color.red, arc.getColor());
    }

    @Test
    void getColor() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(Color.BLUE, arc.getColor());
    }

    @Test
    void getStartNode() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(1, arc.getStartNode());
    }

    @Test
    void getEndNode() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals(2, arc.getEndNode());
    }

    @Test
    void setStartNode() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setStartNode(2);
        assertEquals(2, arc.getStartNode());
    }

    @Test
    void setEndNode() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        arc.setEndNode(3);
        assertEquals(3, arc.getEndNode());
    }

    @Test
    void testToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Arc arc = new Arc(p1, p2, 1, 2,1);
        assertEquals("1->2", arc.toString());
    }
}