package org.example.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.*;

public class SidePanel extends JPanel {
    private JComboBox algorithmSelector;
    private ItemListener selectedAlgorithm = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == algorithmSelector)
            {
                System.out.println("Selected");
            }
        }
    };
    private final Color[] CCColors = {Color.decode("0xda8ee7"), Color.decode("0xbe2ed6"), Color.decode("0xa000c8"), Color.decode("0x7600bc")};
    private JButton runAlgorithm;
    private long expectedTime;
    private int sleepTime = 500;
    private MainPanel grafDrawer;
    private JButton reset;

    public SidePanel(MainPanel grafDrawer){
        String[] listOfAlgorithms = {"Recursive DFS", "DFS", "BFS", "CC", "SCC", "Topo_Sort", "is_Tree", "find_root", "build_tree"};
        algorithmSelector = new JComboBox(listOfAlgorithms);
        algorithmSelector.addItemListener(selectedAlgorithm);
        runAlgorithm = new JButton("Run algorithm");
        this.grafDrawer = grafDrawer;
        reset = new JButton("Reset Graph");
        JPanel objects = new JPanel();
        objects.setLayout(new BoxLayout(objects, BoxLayout.Y_AXIS));
        objects.setBorder(BorderFactory.createTitledBorder("Algorithm picker"));

        algorithmSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        runAlgorithm.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);

        runAlgorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() ->{
                    int startingNode = 1;
                    if(algorithmSelector.getSelectedItem().toString().equals("Recursive DFS") || algorithmSelector.getSelectedItem().toString().equals("DFS")  ||
                        algorithmSelector.getSelectedItem().toString().equals("BFS")){
                        startingNode = Integer.parseInt(JOptionPane.showInputDialog(
                                "From which node should the algorithm start?",
                                1
                        ));
                        if(startingNode > grafDrawer.listaNoduri.size() || startingNode < 1) {
                            System.err.println("Eroare, nodul nu exista");
                            return;
                        }
                    }
                        expectedTime = System.currentTimeMillis();
                        switch (algorithmSelector.getSelectedItem().toString()) {
                            case "Recursive DFS":
                                recursiveDFS(startingNode);
                                break;
                            case "DFS":
                                DFS(startingNode, Color.green);
                                break;
                            case "BFS":
                                BFS(startingNode);
                                break;
                            case "CC":
                                find_CC();
                                break;
                            case "SCC":
                                SCC();
                                break;
                            case "Topo_Sort":
                                topological_sort();
                                break;
                            case "is_Tree":
                                is_tree(true);
                                break;
                            case "find_root":
                                find_root();
                                break;
                            case "build_tree":
                                graph_to_tree();
                                break;
                            default:
                                System.out.println("Default?");
                                break;
                        }
                        expectedTime += (long) 4 * sleepTime;
                        while (System.currentTimeMillis() < expectedTime) ;
                        for (int i = 0; i < grafDrawer.listaNoduri.size(); ) {
                            grafDrawer.listaNoduri.get(i).setColor(Color.BLUE);
                            grafDrawer.repaint();
                            i++;
                        for (Arc a : grafDrawer.listaArce) {
                            a.setColor(Color.BLUE);
                        }
                    }
                }).start();
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grafDrawer.listaNoduri.isEmpty()) {
                    grafDrawer.listaNoduri.subList(0, grafDrawer.listaNoduri.size()).clear();
                }
                if(!grafDrawer.listaArce.isEmpty())
                {
                    grafDrawer.listaArce.subList(0, grafDrawer.listaArce.size()).clear();
                }
                grafDrawer.repaint();
                grafDrawer.nodeNr = 1;
            }
        });
        objects.add(Box.createRigidArea(new Dimension(0, 5)));
        objects.add(algorithmSelector);
        objects.add(Box.createRigidArea(new Dimension(0, 10)));
        objects.add(runAlgorithm);
        objects.add(Box.createRigidArea(new Dimension(0, 10)));
        objects.add(reset);
        objects.add(Box.createRigidArea(new Dimension(0, 5)));
        add(objects);
    }

    //Nu se bazeaza pe un vector de visited, ci pe culoarea nodurilor din graf
    public void recursiveDFS(int nodeNumber)
    {
        while(System.currentTimeMillis() < expectedTime);
        expectedTime += sleepTime;
        grafDrawer.listaNoduri.get(nodeNumber-1).setColor(Color.GREEN);
        grafDrawer.repaint();
        for(Arc a: grafDrawer.listaArce)
        {
            if(a.getStartNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getEndNode()-1).getColor()==Color.BLUE)
            {
                recursiveDFS(a.getEndNode());
            }

            if(grafDrawer.getOrder()==0)
            {
                if(a.getEndNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getStartNode()-1).getColor()==Color.BLUE)
                {
                    recursiveDFS(a.getStartNode());
                }
            }
        }
    }

    public void DFS(int startingNodeNumber, Color col) {
        Stack<Integer> stack = new Stack<>();
        stack.add(startingNodeNumber);//orice poate fi o stiva daca incerci destul de mult
        while (!stack.isEmpty()) {
            while (System.currentTimeMillis() < expectedTime) ;
            expectedTime += sleepTime;
            int nodeNumber = stack.pop();
            if (grafDrawer.listaNoduri.get(nodeNumber - 1).getColor() != col) {
                grafDrawer.listaNoduri.get(nodeNumber - 1).setColor(col);
                grafDrawer.repaint();
                for (Arc a : grafDrawer.listaArce) {
                    if (a.getStartNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getEndNode() - 1).getColor() == Color.BLUE) {
                        stack.add(a.getEndNode());
                    }
                    if (grafDrawer.getOrder()==0) {
                        if (a.getEndNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getStartNode() - 1).getColor() == Color.BLUE) {
                            stack.add(a.getStartNode());
                        }
                    }
                }
            }
        }
    }

    public void DFS(int startingNodeNumber, List<Integer> visited, Color col)
    {
        Stack<Integer> stack = new Stack<>();
        stack.add(startingNodeNumber);//orice poate fi o stiva daca incerci destul de mult
        while(!stack.isEmpty())
        {
            while(System.currentTimeMillis() < expectedTime);
            expectedTime += sleepTime;
            int nodeNumber = stack.pop();
            if(!visited.contains(nodeNumber))
            {
                visited.add(nodeNumber);
                grafDrawer.listaNoduri.get(nodeNumber-1).setColor(col);
                grafDrawer.repaint();
                for(Arc a: grafDrawer.listaArce)
                {
                    if(a.getStartNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getEndNode()-1).getColor()==Color.BLUE)
                    {
                        stack.add(a.getEndNode());
                    }
                    if(grafDrawer.getOrder()==0)
                    {
                        if(a.getEndNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getStartNode()-1).getColor()==Color.BLUE)
                        {
                            stack.add(a.getStartNode());
                        }
                    }
                }
            }
        }
    }

    public void BFS(int startingNodeNumber)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(startingNodeNumber);//kind of illegal but if it works, don't touch it
        while(!stack.isEmpty())
        {
            while(System.currentTimeMillis() < expectedTime);
            expectedTime += sleepTime;
            int nodeNumber = stack.removeFirst();
            if(grafDrawer.listaNoduri.get(nodeNumber-1).getColor()==Color.BLUE)
            {
                grafDrawer.listaNoduri.get(nodeNumber-1).setColor(Color.GREEN);
                grafDrawer.repaint();
                for(Arc a: grafDrawer.listaArce)
                {
                    if(a.getStartNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getEndNode()-1).getColor()==Color.BLUE)
                    {
                        stack.add(a.getEndNode());
                    }
                    if(grafDrawer.getOrder()==0)
                    {
                        if(a.getEndNode() == nodeNumber && grafDrawer.listaNoduri.get(a.getStartNode()-1).getColor()==Color.BLUE)
                        {
                            recursiveDFS(a.getStartNode());
                        }
                    }
                }
            }
        }
    }

    public void find_CC()
    {
        int i = 0;
        Color col;
        List<Integer> visited = new ArrayList<Integer>();
        for(Node n: grafDrawer.listaNoduri)
        {
            col = CCColors[i];
            if(n.getColor()==Color.BLUE) {
                DFS(n.getNumber(), visited, col);
                System.out.println(i);
                i++;
            }
        }
    }

    public void DFS_SCC(int startingNodeNumber, List<Integer> visited, Stack<Integer> stack)
    {
        visited.add(startingNodeNumber);
        for(Arc a: grafDrawer.listaArce)
        {
            if(a.getStartNode() == startingNodeNumber && !visited.contains(a.getEndNode())) {
                System.out.println(a.getEndNode());
                DFS_SCC(a.getEndNode(), visited, stack);
            }
        }
        stack.push(startingNodeNumber);
    }

    public void DFS_SCC_Transposed(int startingNodeNumber, List<Integer> visited, List<Integer> component)
    {
        visited.add(startingNodeNumber);
        component.add(startingNodeNumber);
        for(Arc a: grafDrawer.listaArce)
        {
            if(a.getEndNode() == startingNodeNumber && !visited.contains(a.getStartNode()))
                DFS_SCC_Transposed(a.getStartNode(), visited, component);
        }
    }

    public void SCC()
    {
        Stack<Integer> stack = new Stack<>();
        List<Integer> visited = new ArrayList<>();
        for(int i = 1; i <= grafDrawer.listaNoduri.size(); i++)
            if (!visited.contains(i))
                DFS_SCC(i, visited, stack);

        visited.clear();
        List<List<Integer>> sccs = new ArrayList<>();

        while(!stack.isEmpty())
        {
            int i = stack.pop();
            if(!visited.contains(i)) {
                List<Integer> component = new ArrayList<>();
                DFS_SCC_Transposed(i, visited, component);
                sccs.add(component);
            }
        }
        for(int i = 0; i < sccs.size(); i++)
        {
            for(int j = 0; j < sccs.get(i).size(); j++) {
                grafDrawer.listaNoduri.get(sccs.get(i).get(j) - 1).setColor(CCColors[i]);
                grafDrawer.repaint();
                while(System.currentTimeMillis() < expectedTime);
                expectedTime += sleepTime;
            }
        }
    }

    public void topological_sort()
    {
        Stack<Integer> stack = new Stack<>();
        List<Integer> visited = new ArrayList<>();
        for(int i = 1; i <= grafDrawer.listaNoduri.size(); i++)
            if (!visited.contains(i))
                DFS_SCC(i, visited, stack);
        System.out.println(stack.reversed());
        while(!stack.isEmpty())
        {
            while(System.currentTimeMillis() < expectedTime);
            expectedTime += 2 * sleepTime;
            int i = stack.pop();
            grafDrawer.listaNoduri.get(i-1).setColor(Color.pink);
            grafDrawer.repaint();
        }
    }

    public boolean is_tree(boolean alert)
    {
        if(grafDrawer.listaNoduri.size() != 1 + grafDrawer.listaArce.size()) {
            JOptionPane.showMessageDialog(null, "!Tree", "This is not a tree", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int i = 0;
        List<Integer> visited = new ArrayList<Integer>();
        for(Node n: grafDrawer.listaNoduri)
        {
            if(n.getColor()==Color.BLUE) {
                DFS(n.getNumber(), visited, Color.cyan);
                System.out.println(i);
                i++;
            }
        }
        if(alert)
            if(i != 1) {
                JOptionPane.showMessageDialog(null, "!Tree", "This is not a tree", JOptionPane.ERROR_MESSAGE);
                return false;
            }else
                JOptionPane.showMessageDialog(null, "Tree!", "This is a tree", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public Integer find_root()
    {
        if(is_tree(false)) {
            List<Integer> leaves = new ArrayList<>();
            Dictionary<Integer, Integer> degree = new Hashtable<>();

            for(Node n: grafDrawer.listaNoduri)
            {
                int adiacentNodes = 0;
                for(Arc a: grafDrawer.listaArce)
                    if(a.getStartNode() == n.getNumber() || a.getEndNode() == n.getNumber())
                        adiacentNodes++;
                degree.put(n.getNumber(), adiacentNodes);
                if(degree.get(n.getNumber()) == 1)
                    leaves.add(n.getNumber());
            }
            int count = leaves.size();
            while(count < grafDrawer.listaNoduri.size()) {
                List<Integer> new_leaves = new ArrayList<>();
                for (Integer i : leaves) {
                    for (Arc a : grafDrawer.listaArce) {
                        if (a.getStartNode() == i) {
                            degree.put(a.getEndNode(), degree.get(a.getEndNode()) - 1);
                            if (degree.get(a.getEndNode()) == 1)
                                new_leaves.add(a.getEndNode());
                        } else if (a.getEndNode() == i) {
                            degree.put(a.getStartNode(), degree.get(a.getStartNode()) - 1);
                            if (degree.get(a.getStartNode()) == 1)
                                new_leaves.add(a.getStartNode());
                        }
                    }
                    degree.put(i, 0);
                }
                count += new_leaves.size();
                leaves.clear();
                leaves.addAll(new_leaves);
            }
            System.out.println(leaves);
            for(Integer i : leaves) {
                grafDrawer.listaNoduri.get(i-1).setColor(Color.RED);
                grafDrawer.repaint();
                while (System.currentTimeMillis() < expectedTime);
                expectedTime += 2 * sleepTime;
            }
            return leaves.getFirst();
        }
        return -1;
    }

    public void graph_to_tree()
    {
        if(is_tree(false)) {
            List<Integer> Tree = new ArrayList<>();
            Integer center_node = find_root();
            Tree.add(center_node);
            grafDrawer.listaNoduri.get(center_node-1).setColor(Color.GREEN);
            grafDrawer.repaint();
            int y = 40;
            int width = 1152;
            while(!Tree.isEmpty())
            {
                int x_start = (width - (Tree.size() * 50))/2;
                List<Integer> next_level = new ArrayList<>();

                int x = x_start;
                System.out.println(Tree.size());
                System.out.println("y = " + y);
                System.out.println(Tree);
                for(int i = 0; i < Tree.size(); i++)
                {
                    System.out.println(x);
                    grafDrawer.listaNoduri.get(Tree.get(i)-1).setCoords(x, y);
                    for(Arc a: grafDrawer.listaArce) {
                        if (a.getStartNode() == Tree.get(i) && grafDrawer.listaNoduri.get(a.getEndNode() - 1).getColor() != Color.GREEN) {
                            next_level.add(a.getEndNode());
                            grafDrawer.listaNoduri.get(a.getEndNode() - 1).setColor(Color.GREEN);
                        } else if (a.getEndNode() == Tree.get(i) && grafDrawer.listaNoduri.get(a.getStartNode() - 1).getColor() != Color.GREEN) {
                            next_level.add(a.getStartNode());
                            grafDrawer.listaNoduri.get(a.getStartNode() - 1).setColor(Color.GREEN);
                        }
                    }
                    x += 50;
                    for(Arc a: grafDrawer.listaArce) {
                        a.setStart(grafDrawer.listaNoduri.get(a.getStartNode()-1).getCoordX()+(int)Arc.node_diam/2, grafDrawer.listaNoduri.get(a.getStartNode()-1).getCoordY()+(int)Arc.node_diam/2);
                        a.setEnd(grafDrawer.listaNoduri.get(a.getEndNode()-1).getCoordX()+(int)Arc.node_diam/2, grafDrawer.listaNoduri.get(a.getEndNode()-1).getCoordY()+(int)Arc.node_diam/2);
                    }
                    grafDrawer.repaint();
                    while (System.currentTimeMillis() < expectedTime);
                    expectedTime += sleepTime;
                }
                y += 80;
                Tree.clear();
                Tree.addAll(next_level);
            }
        }
    }

    public void edgeAdder(int node_id, PriorityQueue<Arc> pq, List<Integer> visited)
    {
        visited.add(node_id);

        for(Arc a: grafDrawer.listaArce)
            if(a.getStartNode() == node_id && !visited.contains(a.getEndNode()))
                pq.add(a);
    }
}