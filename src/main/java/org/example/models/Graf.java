package org.example.models;

import javax.swing.*;

/*
Grafica a fost inspirata din cea aratata la clasa in python.
Codul a fost testat si merge functionalitatea principala dar inca e posibil sa apara bug-uri
 */
public class Graf
{
    private static JFrame f = new JFrame("Algoritmica Grafurilor");
    private static void initUI() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyMenu(f));
        //setez dimensiunea ferestrei
        f.setSize(1280, 720);
        //fac fereastra vizibila
        f.setVisible(true);
    }

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
            	initUI();
            }
        });
	}	
}

/*
de adaugat:
pentru fiecare nod se retin liste de adiacenta, adica liste de noduri catre care exista arcec (sau de arce direct fiindca mi-e lene), se poate retine folosind un vector.
dar in java prefer lista.
 */