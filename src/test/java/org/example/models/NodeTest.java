package org.example.models;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NodeTest {

    @Test
    void compareTo() {
        Node n1 = new Node(1,2,3);
        Node n2 = new Node(2,3,4);
        assertTrue(n1.compareTo(n2) < 0);
    }

    @Test
    void getCoordX() {
        Node n1 = new Node(1,2,3);
        assertEquals(1, n1.getCoordX());
    }

    @Test
    void setCoordX() {
        Node n1 = new Node(1,2,3);
        n1.setCoordX(5);
        assertEquals(5, n1.getCoordX());
    }

    @Test
    void getCoordY() {
        Node n1 = new Node(1, 2, 3);
        assertEquals(2, n1.getCoordY());
    }

    @Test
    void setCoordY() {
        Node n1 = new Node(1, 2, 3);
        n1.setCoordY(5);
        assertEquals(5, n1.getCoordY());
    }

    @Test
    void setCoords() {
        Node n1 = new Node(1, 2, 3);
        n1.setCoords(5,6);
        assertEquals(5, n1.getCoordX());
        assertEquals(6, n1.getCoordY());
    }

    @Test
    void getNumber() {
        Node n1 = new Node(1,2,3);
        assertEquals(3, n1.getNumber());
    }

    @Test
    void setNumber() {
        Node n1 = new Node(1, 2, 3);
        n1.setNumber(5);
        assertEquals(5, n1.getNumber());
    }

    @Test
    void getColor() {
        Node n1 = new Node(1,2,3);
        assertEquals(Color.BLUE, n1.getColor());
    }

    @Test
    void setColor() {
        Node n1 = new Node(1,2,3);
        n1.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, n1.getColor());
    }
}