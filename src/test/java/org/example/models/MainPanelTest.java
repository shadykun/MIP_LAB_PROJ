package org.example.models;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MainPanelTest {
    @Test
    public void getOrder() {
        MainPanel mainPanel = new MainPanel(0);
        assertEquals(0, mainPanel.getOrder());
    }

    @Test
    public void changeOrder() {
        MainPanel mainPanel = new MainPanel(0);
        mainPanel.changeOrder(1);
        assertEquals(1, mainPanel.getOrder());
    }

    @Test
    public void eudist() {
        MainPanel mainPanel = new MainPanel(0);
        assertEquals(1, mainPanel.eudist(0, 0, 1, 0));
    }

    @Test
    public void pointNodeCompare() {
        MainPanel mainPanel = new MainPanel(0);
        Point point1 = new Point(0,0);
        Node pointNode1 = new Node(1, 0,0);
        Node pointNode2 = new Node(-15, -15,0);
        assertFalse(mainPanel.pointNodeCompare(point1, pointNode1));
        assertTrue(mainPanel.pointNodeCompare(point1, pointNode2));
    }

    @Test
    public void nodeNodeComparator() {
        MainPanel mainPanel = new MainPanel(0);
        Node pointNode1 = new Node(33, 0,0);
        Node pointNode2 = new Node(2, 0,0);
        Node pointNode3 = new Node(2, 0,0);
        assertFalse(mainPanel.nodeNodeComparator(pointNode1, pointNode2));
        assertTrue(mainPanel.nodeNodeComparator(pointNode2, pointNode3));
    }
}