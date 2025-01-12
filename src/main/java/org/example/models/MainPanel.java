package org.example.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class MainPanel extends JPanel {
    public int nodeNr = 1;
    private final int node_diam = 30;
    public List<Node> listaNoduri;
    public List<Arc> listaArce;
    private Point pointStart = null;
    private Point pointEnd = null;
    private boolean isDragging = false;
    private int orderOfGraph;
    private int arrowSize = 15;
    private boolean isRight;
    private Node movingNode = null;

    public int getOrder(){return orderOfGraph;}

    public void changeOrder(int newOrder){orderOfGraph = newOrder;}

    public double eudist(double X1,double Y1,double X2, double Y2)
    {
        return (Math.sqrt(((X1-X2)*(X1-X2))+((Y1-Y2)*(Y1-Y2))));
    }

    public boolean pointNodeCompare(Point p1, Node n)
    {
        double dist=eudist(p1.getX(),p1.getY(),n.getCoordX()+(double)node_diam/2,n.getCoordY()+(double)node_diam/2);
        return dist<=((double)node_diam/2);
    }

    public boolean nodeNodeComparator(Node n1, Node n2) {
        double dist=eudist(n1.getCoordX(),n1.getCoordY(),n2.getCoordX(),n2.getCoordY());
        return dist<=node_diam;
    }

    public MainPanel(int order) {
        orderOfGraph = order;
        listaNoduri = new ArrayList<Node>();
        listaArce = new ArrayList<Arc>();

        // borderul panel-ului
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            //evenimentul care se produce la apasarea mousse-ului
            public void mousePressed(MouseEvent e) {
                pointStart = e.getPoint();
            }
            //evenimentul care se produce la eliberarea mousse-ului
            public void mouseReleased(MouseEvent e) {
                movingNode = null;
                if (!isDragging) {
                    if(SwingUtilities.isRightMouseButton(e)) {
                        addNode(e.getX(), e.getY());
                    }
                    if(SwingUtilities.isMiddleMouseButton(e))
                    {
                        for(Node i: listaNoduri)
                        {
                            if(pointNodeCompare(pointStart, i))
                            {
                                for(int j = 0; j < listaArce.size(); j++)
                                    if(listaArce.get(j).getStartNode() == i.getNumber() || listaArce.get(j).getEndNode() == i.getNumber()) {
                                        listaArce.remove(listaArce.get(j));
                                        j--;
                                    }
                                nodeNr--;
                                listaNoduri.remove(i);
                                listaNoduri.sort(Node::compareTo);
                                for(int n = 0; n < listaNoduri.size(); n++)
                                {
                                    for(Arc a : listaArce)
                                    {
                                        if(listaNoduri.get(n).getNumber() == a.getStartNode())
                                            a.setStartNode(n+1);
                                        if(listaNoduri.get(n).getNumber() == a.getEndNode())
                                            a.setEndNode(n+1);
                                    }
                                    listaNoduri.get(n).setNumber(n+1);
                                }
                                break;
                            }
                        }
                    }
                } else {
                    if(SwingUtilities.isRightMouseButton(e)) {
                        Arc arc = null;

                        boolean startFound = false;
                        Node startNode = null;
                        Node endNode = null;

                        for (int j = 0; j < listaNoduri.size(); j++) {
                            Node existingNode = listaNoduri.get(j);
                            // Verifică dacă pointStart este în interiorul unui nod
                            if (!startFound && pointNodeCompare(pointStart, existingNode)) {
                                startFound = true;
                                startNode = existingNode;
                                pointStart.setLocation(startNode.getCoordX() + (double) node_diam / 2, startNode.getCoordY() + (double) node_diam / 2);
                                j = -1;// Salvează nodul de start
                            }

                            // Verifică dacă pointEnd este în interiorul unui nod
                            if (startFound && existingNode != startNode && pointNodeCompare(pointEnd, existingNode)) {

                                endNode = existingNode; // Salvează nodul de sfârșit
                                pointEnd.setLocation(endNode.getCoordX() + (double) node_diam / 2, endNode.getCoordY() + (double) node_diam / 2);
                                break; // Oprește căutarea după ce găsești nodul de sfârșit
                            }
                        }

                        // Adaugă arc-ul doar dacă am găsit ambele noduri
                        if (startNode != null && endNode != null) {
                            int weight = 0;
                            if(orderOfGraph == 2)
                                weight = Integer.parseInt(JOptionPane.showInputDialog(
                                        "From which node should the algorithm start?",
                                        1
                                ));
                            arc = new Arc(pointStart, pointEnd, startNode.getNumber(), endNode.getNumber(), weight);
                            listaArce.add(arc);
                        }
                    }
                }

                // Resetare coordonate și stare
                pointStart = null;
                isDragging = false;
                repaint();
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            //evenimentul care se produce la drag&drop pe mousse
            public void mouseDragged(MouseEvent e) {
            pointEnd = e.getPoint();
            isDragging = true;
            if(SwingUtilities.isRightMouseButton(e))
                isRight = true;
            else if(SwingUtilities.isLeftMouseButton(e)) {
                isRight = false;
                if(movingNode == null) {
                    for(Node i : listaNoduri)
                    {
                        if(pointNodeCompare(pointStart, i))
                        {
                            movingNode = i;
                        }
                    }
                }
            }
            repaint();
            }
        });
    }


    //metoda care se apeleaza la eliberarea mouse-ului
    private void addNode(int x, int y) {
        Node node = new Node(x-node_diam/2, y-node_diam/2, nodeNr);

        // Verifică fiecare nod existent
        for (int j = 0; j < listaNoduri.size(); j++) {
            Node existingNode = listaNoduri.get(j);

            // Dacă nodurile se suprapun (folosind comparatorul)
            if (nodeNodeComparator(node, existingNode)) {
                node = null;
                // Oprește verificarea curentă și continuă până găsești o poziție validă
                break;
            }
        }

        // Adaugă nodul la lista de noduri
        if(node != null)
        {
            listaNoduri.add(node);
            nodeNr++;
            // Redesenare grafică
            repaint();
        }
    }

    @Override
    //se executa atunci cand apelam repaint()
    protected void paintComponent(Graphics g)
    {
        setBackground(Color.white);
        super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        g.drawString("This is my Graph!", 10, 20);
        //deseneaza arcul curent; cel care e in curs de desenare
        if (pointStart != null) {
            if(isRight) {
                g.setColor(Color.BLUE);
                g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                if (orderOfGraph == 1) {
                    double angle = Math.atan2(pointEnd.y - pointStart.y, pointEnd.x - pointStart.x);

                    int x1 = (int) (pointEnd.x - arrowSize * Math.cos(angle - Math.PI / 6));
                    int y1 = (int) (pointEnd.y - arrowSize * Math.sin(angle - Math.PI / 6));
                    int x2 = (int) (pointEnd.x - arrowSize * Math.cos(angle + Math.PI / 6));
                    int y2 = (int) (pointEnd.y - arrowSize * Math.sin(angle + Math.PI / 6));

                    g.drawLine(pointEnd.x, pointEnd.y, x1, y1);
                    g.drawLine(pointEnd.x, pointEnd.y, x2, y2);
                    g.drawLine(x1, y1, x2, y2);
                }
            }
            else {
                if(movingNode != null) {
                    for (Arc a : listaArce) {
                        if (movingNode.getNumber() == a.getStartNode()) {
                            a.setStart(pointEnd.x, pointEnd.y);
                            a.drawArc(g, orderOfGraph);
                        } else if (movingNode.getNumber() == a.getEndNode()) {
                            a.setEnd(pointEnd.x, pointEnd.y);
                            a.drawArc(g, orderOfGraph);
                        }
                    }
                    movingNode.drawNode(g, node_diam + 8);
                    movingNode.setCoordX(pointEnd.x - (node_diam + 8) / 2);
                    movingNode.setCoordY(pointEnd.y - (node_diam + 8) / 2);
                }
            }
        }
        try{
            File myFile = new File("output.txt");
            if(myFile.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File updated");
            FileWriter fw = new FileWriter(myFile);

            //deseneaza arcele existente in lista
            for (Arc a : listaArce)
            {
                a.drawArc(g, orderOfGraph);
                fw.write(a.toString());
            }
            //listaArce.forEach(arc -> arc.drawArc(g, orderOfGraph));
            //deseneaza lista de noduri
            for(int i = 0; i < listaNoduri.size(); i++)
            {
                fw.append(listaNoduri.get(i).toString());
                if(listaNoduri.get(i) != movingNode)
                    listaNoduri.get(i).drawNode(g, node_diam);
            }
        } catch (Exception e) {
            System.out.println("An error happened");
            e.printStackTrace();
        }
    }
}
