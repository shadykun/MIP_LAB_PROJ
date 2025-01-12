package org.example.models;

import javax.swing.*;
import java.awt.*;

//creez panoul nou pentru graf si pastrez panoul asta ascuns pana cand apas un buton pe celalalt panou
//butoonul de pe celalalt panou trebuie sa fie pe frame de fapt

public class MyMenu extends JPanel {
    /**
     * Something
     * @param f Is the /jfrme of the main menu
     */
    public MyMenu(JFrame f)
    {
        JButton ordered = new JButton("Ordered Graph");
        JButton unOrdered = new JButton("Unordered Graph");
        JButton weighted = new JButton("Weighted Graph");
        JButton back = new JButton("Back to Menu");
        JLabel title = new JLabel("Choice screen");
        title.setFont(new Font("TimesRoman", Font.BOLD, 35));

        ordered.setBounds(430,200,200, 40);//x-axis, y-axis, width, height
        unOrdered.setBounds(660, 200, 200, 40);
        weighted.setBounds(545, 260, 200, 40);
        title.setBounds(540, 100, 300, 40);
        back.setBounds(10, 10, 200, 40);

        MainPanel grafDrawer = new MainPanel(0);

        unOrdered.addActionListener(e ->{
            f.remove(ordered);
            f.remove(title);
            f.remove(unOrdered);
            f.remove(weighted);
            f.revalidate();
            setVisible(false);
            f.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.9;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;
            f.add(grafDrawer, c);
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.1;
            c.gridx = 1;
            c.gridy = 0;
            f.add(new SidePanel(grafDrawer), c);
            f.revalidate();
        });

        ordered.addActionListener(e ->{
            f.remove(ordered);
            f.remove(title);
            f.remove(unOrdered);
            f.remove(weighted);
            f.revalidate();
            setVisible(false);
            f.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;
            grafDrawer.changeOrder(1);
            f.add(grafDrawer, c);
            c.fill = GridBagConstraints.VERTICAL;
            c.weightx = 0;
            c.gridx = 1;
            c.gridy = 0;
            f.add(new SidePanel(grafDrawer), c);
            f.revalidate();
            f.repaint();
        });

        weighted.addActionListener(e ->{
            f.remove(ordered);
            f.remove(title);
            f.remove(unOrdered);
            f.remove(weighted);
            f.revalidate();
            setVisible(false);
            f.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 0;
            grafDrawer.changeOrder(2);
            f.add(grafDrawer, c);
            c.fill = GridBagConstraints.VERTICAL;
            c.weightx = 0;
            c.gridx = 1;
            c.gridy = 0;
            f.add(new SidePanel(grafDrawer), c);
            f.revalidate();
            f.repaint();
        });

        f.add(title);
        f.add(unOrdered);
        f.add(ordered);
        f.add(weighted);
    }
}
