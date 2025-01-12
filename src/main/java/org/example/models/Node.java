package org.example.models;

import org.example.interfaces.INode;

import java.awt.*;

public class Node implements Comparable<Node>, INode
{
	private int coordX;
	private int coordY;
	private int number;
	private Color color = Color.BLUE;

	public int compareTo(Node n)
	{
		return number - n.getNumber();
	}

	public Node(int coordX, int coordY, int number)
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.number = number;
	}

	public void drawNode(Graphics g, int node_diam)
	{
		g.setColor(color);
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX, coordY, node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        if(number < 10)
        	g.drawString(((Integer)number).toString(), coordX+13, coordY+20);
        else
        	g.drawString(((Integer)number).toString(), coordX+8, coordY+20);
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public void setCoords(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
