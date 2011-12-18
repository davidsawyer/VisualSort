//This class contains the main method and ties everything together and packs it up.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VisualSort {

	private static Options options;
	private static VisualSortPanel panel1;
	private static VisualSortPanel panel2;
	private static Thread t1, t2;

	/*
	Post: Executes the program
	*/
	public static void main(String[] args) {

		// Setting up the frame
          JFrame frame = new JFrame ("VisualSort");
          frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Moves menu bar to the mac menu bar
          System.setProperty("apple.laf.useScreenMenuBar", "true");
	
		MenuBar menuBar = new MenuBar();
		frame.setJMenuBar(menuBar.createMenuBar());
          
		// Disable resizing of the window and setting up the MenuBar
          frame.setResizable(false);
		
		options = new Options();
		options.populateRandomArray();

          // Pack everything up and set it to visible
          frame.setPreferredSize(new Dimension(1000,650));
          frame.pack();
	
		// Setting up the options panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		frame.add(options, c);

		//creates the top panel
		panel1 = new VisualSortPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 296;
          c.gridx = 0;
          c.gridy = 1;
		c.weightx = .5;
		c.weighty = .5;
          frame.add(panel1, c);
		
		//creates the bottom panel
		panel2 = new VisualSortPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 314;
          c.gridx = 0;
          c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
          frame.add(panel2, c);
		
		frame.setVisible(true);
	}

	/*
	Pre: the ArrayList of ints has been populated
	Post: the array of Rectangles are drawn to the panel
	*/
	public static void updateArrays() {

		panel1.populateArray();
		panel2.populateArray();
	}

	/*
	Pre: the ArrayList of ints has been populated
	Post: executes the visualization
	*/
	public static void run() {
	
		updateArrays();
		
		try {
               Thread.sleep(300);
          }
          catch (InterruptedException e) {
               System.exit(-1);
          }	

		Runnable r1 = new Runnable() {
			
			public void run() {

				if (options.getTopAlgo().equals("selection"))
               		panel1.selectionSort();
     			else if (options.getTopAlgo().equals("insertion"))
          	     	panel1.insertionSort();
          		else if (options.getTopAlgo().equals("bubble"))
               		panel1.bubbleSort();
          		else if (options.getTopAlgo().equals("merge"))
               		panel1.mergeSort();
			}
		};
		
		Runnable r2 = new Runnable() {

			public void run() {
				
				if (options.getBottomAlgo().equals("selection"))
          	     	panel2.selectionSort();
	        		else if (options.getBottomAlgo().equals("insertion"))
          		     panel2.insertionSort();
          		else if (options.getBottomAlgo().equals("bubble"))
               		panel2.bubbleSort();
          		else if (options.getBottomAlgo().equals("merge"))
               		panel2.mergeSort();            
     		}
		};

		t1 = new Thread(r1);
		t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
