// This class contains and handles the options the user may manipulate
import javax.swing.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Options extends JPanel implements ActionListener, DocumentListener  {

	public static final long serialVersionUID = 3L;
	private JLabel enterSize, caseLabel, topAlgo, bottomAlgo, enterSpeed;
	private JComboBox chooseCase, chooseTopAlgo, chooseBottomAlgo;
	private JTextField arraySizeEntry, speedEntry;
	private JButton run;
	private int arraySize = 24;
     static ArrayList<Integer> valuesArray = null;
	private boolean isWorstCase = false;
	private boolean selectionIsOnTop = true;
	private boolean insertionIsOnTop = false;
	private boolean bubbleIsOnTop = false;
	private boolean mergeIsOnTop = false;
	private boolean selectionIsOnBottom = true;
	private boolean insertionIsOnBottom = false;
	private boolean bubbleIsOnBottom = false;
	private boolean mergeIsOnBottom = false;
	static int time = 10;

	/*
	Post: sets up all the options visually
	*/
	public Options() {

     	valuesArray = new ArrayList<Integer>();
		populateRandomArray();
		
		// size of the array
		enterSize = new JLabel("Array size:");
		arraySizeEntry = new JTextField("", 3);
		arraySizeEntry.addActionListener(this);
		arraySizeEntry.getDocument().addDocumentListener(new DocListener());
		
		// algorithm on top
		topAlgo = new JLabel("Top:");
		String[] topAlgos = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Merge Sort"};
		chooseTopAlgo = new JComboBox(topAlgos);
		chooseTopAlgo.addActionListener(this);
		
		//algorithm on bottom
		bottomAlgo = new JLabel("Bottom:");
		String[] bottomAlgos = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Merge Sort"};
		chooseBottomAlgo = new JComboBox(bottomAlgos);
		chooseBottomAlgo.addActionListener(this);
		
		// average case or worst case
		caseLabel = new JLabel("Choose case:");
		String[] cases = {"Average Case", "Worst Case"};
		chooseCase = new JComboBox(cases);
          chooseCase.addActionListener(this);

		// delay of comparisons in the visualizer
          enterSpeed = new JLabel("Delay:");
          speedEntry = new JTextField("10", 2);
          speedEntry.addActionListener(this);
          speedEntry.getDocument().addDocumentListener(this);

		// run button (runs the visualizer)
		run = new JButton("Run");
		run.addActionListener(this);

		// adding everything to the panel
		add(enterSize);
		add(arraySizeEntry);
		add(topAlgo);
		add(chooseTopAlgo);
		add(bottomAlgo);
		add(chooseBottomAlgo);
		add(caseLabel);
		add(chooseCase);
		add(enterSpeed);
		add(speedEntry);
		add(run);
	}

	/*
	Post: determines which algortihm is set to execute in the top panel
	*/
	public String getTopAlgo() {

		if (selectionIsOnTop)
			return "selection";
     	else if (insertionIsOnTop)
			return "insertion";
     	else if (bubbleIsOnTop)
			return "bubble";
     	else
			return "merge";
	}

	/*
	Post: determines which algortihm is set to execute in the bottom panel
	*/
	public String getBottomAlgo() {

          if (selectionIsOnBottom)
               return "selection";
          else if (insertionIsOnBottom)
               return "insertion";
          else if (bubbleIsOnBottom)
               return "bubble";
          else
               return "merge";
	}  	

	/*
	Post: populates an ArrayList full of random integers
	*/
	public void populateRandomArray() {

		valuesArray.clear();

		int randomInt;
		boolean newVal;

          for(int i = 0; i<arraySize; i++) {

              	do {
				
				newVal = true;
		    		randomInt = (int)(Math.random()*arraySize+1);
		    		for (int j = 0; j<i && newVal; j++) {
				
					if (randomInt == valuesArray.get(j))
						newVal = false;
				}

			} while (!newVal);
			
			valuesArray.add(i, randomInt);
          }
     }

	/*
	Post: populates an ArrayList full of ints from greatest to least
	*/
	public void populateWorstCaseArray() {

		valuesArray.clear();

          int value = arraySize + 1;
          boolean newVal;

          for(int i = 0; i<arraySize; i++) {
			
			value--;
               valuesArray.add(i, value);
          }
	}
     
	/*
	Pre: action must be performed for this method to fire
	Post: the appropriate action is selected depending on the source
	*/
	public void actionPerformed (ActionEvent event) {
		
		if (event.getSource() == chooseCase) {
			
			JComboBox cb = (JComboBox)event.getSource();
			String caseChosen = (String)cb.getSelectedItem();
			if (caseChosen.equals("Worst Case"))
				isWorstCase = true;
			else isWorstCase = false;
			if (arraySize >= 25 && arraySize <= 300) {
                    if (isWorstCase)
                         populateWorstCaseArray();
                    else
                         populateRandomArray();
                    VisualSort.updateArrays();
               }
		}
		
		else if (event.getSource() == chooseTopAlgo) {

		 	JComboBox cb = (JComboBox)event.getSource();
               String caseChosen = (String)cb.getSelectedItem();
               if (caseChosen.equals("Selection Sort")) {
				selectionIsOnTop = true;
     			insertionIsOnTop = false;
     			bubbleIsOnTop = false;
     			mergeIsOnTop = false;
			}
			else if (caseChosen.equals("Insertion Sort")) {
				selectionIsOnTop = false;
                    insertionIsOnTop = true;
                    bubbleIsOnTop = false;
                    mergeIsOnTop = false;
			}
			else if (caseChosen.equals("Bubble Sort")) {
                    selectionIsOnTop = false;
                    insertionIsOnTop = false;
                    bubbleIsOnTop = true;
                    mergeIsOnTop = false;
               }
			else if (caseChosen.equals("Merge Sort")) {
                    selectionIsOnTop = false;
                    insertionIsOnTop = false;
                    bubbleIsOnTop = false;
                    mergeIsOnTop = true;
               }
		}
		
		else if (event.getSource() == chooseBottomAlgo) {

               JComboBox cb = (JComboBox)event.getSource();
               String caseChosen = (String)cb.getSelectedItem();
               if (caseChosen.equals("Selection Sort")) {
                    selectionIsOnBottom = true;
                    insertionIsOnBottom = false;
                    bubbleIsOnBottom = false;
                    mergeIsOnBottom = false;
               }
               else if (caseChosen.equals("Insertion Sort")) {
                    selectionIsOnBottom = false;
                    insertionIsOnBottom = true;
                    bubbleIsOnBottom = false;
                    mergeIsOnBottom = false;
               }
               else if (caseChosen.equals("Bubble Sort")) {
                    selectionIsOnBottom = false;
                    insertionIsOnBottom = false;
                    bubbleIsOnBottom = true;
                    mergeIsOnBottom = false;
               }
               else if (caseChosen.equals("Merge Sort")) {
                    selectionIsOnBottom = false;
                    insertionIsOnBottom = false;
                    bubbleIsOnBottom = false;
                    mergeIsOnBottom = true;
               }
          }
		
		else if (event.getSource() == run) {
			
			if (arraySize >= 25 && arraySize <= 300)
				VisualSort.run();
		}
		
		else {
			
			checkAndUpdateArray();
		}
	}

	/*
	Pre: some DocumentEvent must occur in the araySizeEntry text field
	Post: the string input is parsed to an int if it meets specs
	*/
	private class DocListener implements DocumentListener {
	
		public void insertUpdate(DocumentEvent e) {
			
			checkAndUpdateArray();
		}
	
		public void removeUpdate(DocumentEvent e) {
	
               checkAndUpdateArray();
		}
	
		public void changedUpdate(DocumentEvent e) {

	     	checkAndUpdateArray();
		}

	}
	
	/*
		Post: the string input is parsed to an int if it meets specs
	*/
	public void checkAndUpdateArray() {

		if(arraySizeEntry.getText().length()>0) {
			boolean isAnInt = true;
          	String entry = arraySizeEntry.getText();
          	entry.trim();
          	for (int i = 0; i < entry.length(); i++) {

          	     if (entry.charAt(i)<48 || entry.charAt(i)>57 || entry.equals("0"))
          	          isAnInt = false;
          	}

          	if (isAnInt) {

          	     arraySize = Integer.parseInt(entry);
          	     if (arraySize >= 25 && arraySize <= 300) {
          	          if (isWorstCase)
          	               populateWorstCaseArray();
          	          else
          	               populateRandomArray();

          	          VisualSort.updateArrays();
               	}
			}
          }
	}

	/*
	Pre: some DocumentEvent must occur in the speedEntry text field
	Post: the string input is parsed to an int if it meets specs
	*/
	public void insertUpdate(DocumentEvent e) {

     	checkAndUpdateTime();
	}

     public void removeUpdate(DocumentEvent e) {

     	checkAndUpdateTime();
	}

     public void changedUpdate(DocumentEvent e) {

     	checkAndUpdateTime();
	}

	/*
	Post: the string input is parsed to an int if it meets specs
	*/
	private void checkAndUpdateTime() {

          if(speedEntry.getText().length()>0) {
               boolean isAnInt = true;
               String entry = speedEntry.getText();
               entry.trim();
               for (int i = 0; i < entry.length(); i++) {

                    if (entry.charAt(i)<48 || entry.charAt(i)>57 || entry.equals("0"))
                         isAnInt = false;
               }

               if (isAnInt) {

                    time = Integer.parseInt(entry);
                    if (time >= 1 && time <= 20) {}
				else time = 10;
               }
          }
     }
}
