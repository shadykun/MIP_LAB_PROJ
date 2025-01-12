package org.example.interfaces;

import org.example.models.Node;

import java.awt.*;

public interface INode {
    public void drawNode(Graphics g, int nodeDiameter);
    public int compareTo(Node n);
}
