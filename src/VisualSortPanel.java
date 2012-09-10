// This class represents a panel on which the visual effects and the sorting take place
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VisualSortPanel extends JPanel {
    
    private boolean isRunning = false;
    public static final long serialVersionUID = 3L;
    private int rectWidth;
    private int arrayLength;
    Rectangle[] rectArray = new Rectangle[this.arrayLength];

    /*
    Pre: ArrayList in Options must be populated
    Post:takes the values from the ArrayList in Options and creates rectangle equivalents
    */
    public void populateArray() {

        if (!isRunning) {
            this.getGraphics().clearRect(0, 0, getWidth(), getHeight());
        
            arrayLength = Options.valuesArray.toArray().length;

            rectArray = new Rectangle[this.arrayLength];
            rectWidth = getWidth()/this.arrayLength;
        
            int value;

            for(int i = 0; i<this.arrayLength; i++) {
    
                value = (Integer)Options.valuesArray.toArray()[i] * getHeight()/this.arrayLength;
        
                if (getHeight()/this.arrayLength > 1) 
                    value--;

                rectArray[i] = new Rectangle(rectWidth*(i), getHeight()-value, rectWidth, value);
            }

            for(int i = 0; i<this.arrayLength; i++) {

                paint(getGraphics(), Color.gray, i);
            }
        }
    }

    /*
    Post: paints the rectangle in a particular Color
    */
    public void paint(Graphics g, Color color, int i) {

        g.setColor(color);
          g.fillRect(((int)(rectArray[i].getX())), (int)(rectArray[i].getY()), (int)(rectArray[i].getWidth()), (int)(rectArray[i].getHeight()));    
    }
    
    /*
    Post: swaps the height of two Rectangles in the rectArray
    */
    private void swap(int a, int b) {

        int aX, bX;
        Rectangle temp = new Rectangle();

        this.getGraphics().clearRect(((int)(rectArray[a].getX())), (int)(rectArray[a].getY()), (int)(rectArray[a].getWidth()), (int)(rectArray[a].getHeight()));
          this.getGraphics().clearRect(((int)(rectArray[b].getX())), (int)(rectArray[b].getY()), (int)(rectArray[b].getWidth()), (int)(rectArray[b].getHeight()));
          aX = (int)rectArray[a].getX();
          bX = (int)rectArray[b].getX();

          temp = rectArray[a];
          rectArray[a] = rectArray[b];
          rectArray[b] = temp;
          rectArray[a].setLocation(aX, (int)rectArray[a].getY());
          rectArray[b].setLocation(bX, (int)rectArray[b].getY());

        paint(getGraphics(), Color.gray, a);
        paint(getGraphics(), Color.gray, b);
    }

    /*
    Post: takes a nap for Options.time milliseconds
    */
    private void sleep() {

        try {
               Thread.sleep(Options.time);
          }
          catch (InterruptedException e) {
               System.exit(-1);
          }
    }
    
    /*
    Pre: the rectArray is populated
    Post: sorts the Rectangles by means of selection sort
    */
    public void selectionSort() {

        if (!isRunning) {
            
            sleep();

            isRunning = true;

            int min, position, i;
            Rectangle temp = new Rectangle();

            for (position = 0; position < this.arrayLength; position++) {
        
                sleep();            
                
                min = position;
                paint(getGraphics(), Color.gray, min);
                for (i = position+1; i < this.arrayLength; i++) {
                    
                    paint(getGraphics(), Color.red, i);
                    sleep();

                    if (rectArray[i].getHeight() < rectArray[min].getHeight()) {
                    
                        sleep();
                        min = i;
                    }
                    paint(getGraphics(), Color.gray, i);
                    
                }

                if (min != position) {
                    
                    swap(min, position);
                }
            }

            isRunning = false;
        }
    }

    /*
     Pre: the rectArray is populated
     Post: sorts the Rectangles by means of insertion sort
     */
    public void insertionSort() {

        if(!isRunning) {
            
            sleep();

            isRunning = true;

            int i, j;
            Rectangle temp = new Rectangle();

            for (i = 1; i < this.arrayLength; i++) {

                paint(getGraphics(), Color.gray, i);
                
                j = i;

                sleep();

                while (j > 0 && rectArray[j-1].getHeight() > rectArray[j].getHeight()) {

                    paint(getGraphics(), Color.red, j);
                    sleep();

                    swap(j, j-1);

                    j--;
                }
            }

            isRunning = false;
        }
    }

    /*
     Pre: the rectArray is populated
     Post: sorts the Rectangles by means of bubble sort
     */
    public void bubbleSort() {

          if(!isRunning) {
          
               sleep();
            
            isRunning = true;
               
            boolean swapped = true;
            int j = 0;
            Rectangle temp = new Rectangle();

            while (swapped) {

                paint(getGraphics(), Color.gray, j);
                sleep();
                
                swapped = false;
                j++;

                for (int i = 0; i < this.arrayLength - j; i++) {

                    paint(getGraphics(), Color.red, i);
                    sleep();
                    
                    if (rectArray[i].getHeight() > rectArray[i+1].getHeight()) {
                    
                        swap(i, i+1);
                
                        swapped = true;
                    }
                    paint(getGraphics(), Color.gray, i);
                }
            }

               isRunning = false;
          }
     }

    /*
     Pre: the rectArray is populated
     Post: sorts the Rectangles by means of merge sort
     */
    public void mergeSort() {

          if(!isRunning) {
          
               isRunning = true;
            mergeSortImplementation(rectArray, 0, arrayLength-1);
               isRunning = false;
          }
     }
    
    /*
     Post: sorts the Rectangles by means of merge sort (this is the actual merge implementation)
     */
    private void mergeSortImplementation(Rectangle[] array, int lo, int n) {

        int low = lo;
        int high = n;
        
        sleep();
        if (low >= high) {

            return;
        }

        int middle = (low + high) / 2;
        
        mergeSortImplementation(array, low, middle);
        mergeSortImplementation(array, middle + 1, high);

        int end_low = middle;
        int start_high = middle + 1;

        while ((lo <= end_low) && (start_high <= high)) {

            sleep();

            if (array[low].getHeight() < array[start_high].getHeight()) {
                
                sleep();
                low++;
            }
            else {
                sleep();    

                Rectangle temp = new Rectangle();
                temp = array[start_high];    
                int lowX = (int)rectArray[low].getX();

                for (int k = start_high - 1; k >= low; k--) {
                    
                    array[k+1] = array[k];
                    array[k+1].setLocation((k+1)*rectWidth, (int)rectArray[k+1].getY());
                }
                
                array[low] = temp;
                  array[low].setLocation(lowX, (int)rectArray[low].getY());
                
                low++;
                end_low++;
                start_high++;
            }
            update(rectArray);
        }
    }

    /*
    Post: updates the rectArray by iterating through all of the array of the Rectangles
    */
    private void update (Rectangle[] array) {

        this.getGraphics().clearRect(0, 0, getWidth(), getHeight());        

        for(int i = 0; i<this.arrayLength; i++) {
        
                 paint(getGraphics(), Color.gray, i);
        }    
    }
}
