package org.example.models;

import org.example.interfaces.IArc;

import java.awt.*;
import java.util.Comparator;

public class Arc implements IArc
{
	private Point start;
	private Point end;
	private int arrowSize = 15;
	public static final double node_diam = 30;
	private int startNode;
	private int endNode;
	private Color color = Color.BLUE;
	private int weight;
	
	public Arc(Point start, Point end, int startNode, int endNode, int weight)
	{
		this.start = start;
		this.end = end;
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}
	
	public void drawArc(Graphics g, int order)
	{
		if (start != null)
		{
			g.setColor(color);
			g.drawLine(start.x, start.y, end.x, end.y);
			if(order == 1 || order == 2) {
				Point arrowPoint = new Point(start.x - end.x, start.y - end.y);
				double v1 = Math.sqrt(Math.pow(arrowPoint.x, 2) + Math.pow(arrowPoint.y, 2));
				double u1 = (arrowPoint.x / v1), u2 = (arrowPoint.y / v1);
				double angle = Math.atan2(end.y - start.y, end.x - start.x);

				int x1 = (int) (end.x + node_diam / 2 * u1 - arrowSize * Math.cos(angle - Math.PI / 6));
				int y1 = (int) (end.y + node_diam / 2 * u2 - arrowSize * Math.sin(angle - Math.PI / 6));
				int x2 = (int) (end.x + node_diam / 2 * u1 - arrowSize * Math.cos(angle + Math.PI / 6));
				int y2 = (int) (end.y + node_diam / 2 * u2 - arrowSize * Math.sin(angle + Math.PI / 6));

				g.drawLine((int) (end.x + node_diam / 2 * u1), (int) (end.y + node_diam / 2 * u2), x1, y1);
				g.drawLine((int) (end.x + node_diam / 2 * u1), (int) (end.y + node_diam / 2 * u2), x2, y2);
				g.drawLine(x1, y1, x2, y2);
				g.setFont(new Font("TimesRoman", Font.BOLD, 15));
				if(order == 2) {
					if(end.x - start.x > 0 && end.y - start.y > 0)
						g.drawString(((Integer)weight).toString(),x2 + (int)(10 * u1), y2 + (int)(10 * u2));
					else
						g.drawString(((Integer)weight).toString(),x1 + (int)(10 * u1), y1 + (int)(10 * u2));
				}
			}
		}
	}

	public void setStart(int x, int y)
	{
		start.x = x;
		start.y = y;
	}

	public int getStartX(){
		return start.x;
	}

	public int getStartY(){
		return start.y;
	}

	public void setEnd(int x, int y)
	{
		end.x = x;
		end.y = y;
	}

	public int getEndX(){
		return end.x;
	}

	public int getEndY(){
		return end.y;
	}

	public Color getColor(){
		return color;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}
	public int getWeight(){
		return weight;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public int getStartNode() {
		return startNode;
	}
	public int getEndNode(){
		return endNode;
	}
	public void setStartNode(int n) {
		startNode = n;
	}
	public void setEndNode(int n){
		endNode = n;
	}

	@Override
	public String toString() {
        return startNode + "->" + endNode;
	}
}

class sortByWeight implements Comparator<Arc>{
	public int compare(Arc o1, Arc o2) {
		return o1.getWeight() - o2.getWeight();
	}
}
/*
x1-x2 * x3-x4 + y1-y2 * y3-y4 = 0
y3 = x1-x2 * x3-x4 / y1-y2 + y4
dist = ((x1-x2)^2 + (y1-y2)^2)^(1/2)
 */