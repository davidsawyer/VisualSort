//This class represents the MenuBar and it's functionaltiy
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MenuBar implements ActionListener {
    
    private JMenuBar menuBar;
    private JMenu file, options, help;
    private JMenuItem exit, /*selection, insertion, bubble, merge,*/ about;
    
    // Creates the menu bar for the frame
    public JMenuBar createMenuBar() {
        
        // Menu Bar
        menuBar = new JMenuBar();
        
        // File Menu
        file = new JMenu("File");
        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.META_MASK));
        exit.addActionListener(this);
        menuBar.add(file);
        file.add(exit);
        
        // Help Menu
        help = new JMenu("Help");
        about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.META_MASK));
        about.addActionListener(this);
        menuBar.add(help);
        help.add(about);
        
        return menuBar;
    }
    
    /*
     Post: either exits or displays the about window depending on what you choose
     */
    public void actionPerformed (ActionEvent event) {
        
        if (event.getSource() == exit)
            System.exit(0);
        
        else if (event.getSource() == about){
            
            JOptionPane.showMessageDialog(menuBar,
                                          "VisualSort\nAuthor: David Sawyer\n\n"+
                                          "VisualSort is a tool that helps the user to gain understanding\n"+
                                          "about and get a feel for a few common sorting algorithms.\n\n"+
                                          "Time Complexities:\n\n"+
                                          "Selection Sort: avg- O(n^2), best- 0(n^2), worst- O(n^2)\n"+
                                          "Insertion Sort: avg- O(n^2), best- 0(n), worst- O(n^2)\n"+
                                          "Bubble Sort: avg- O(n^2), best- 0(n), worst- O(n^2)\n"+
                                          "Selection Sort: avg- O(nlogn), best- 0(nlogn), worst- O(nlogn)",
                                          "About VisualSort", JOptionPane.PLAIN_MESSAGE);
         }
    }
}
