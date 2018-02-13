import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class checkers extends JPanel implements MouseListener{

    boolean[][] blackPieces, redPieces;
    int[] firstClick, lastClick;
    boolean redTurn, redClicked, blackClicked;
    int x, y; 

    public checkers(){
        //Initialize all of your PIVs in the constructor. 'redTurn' needs to be true, 'firstClick' and 'lastClick' needs to be -1.
        blackPieces = new boolean[8][8];
        redPieces = new boolean[8][8];
        firstClick = new int[2];
        firstClick[0] = -1; 
        firstClick[1] = -1;
        lastClick = new int[2];
        lastClick[0] = -1;
        lastClick[1] = -1;
        redTurn = true;
        redClicked = false;
        blackClicked = false;

        //Fill the board with circle pieces. Use the 2D array of blackPieces and redPieces
        //to set where the pieces are.
        for (int i = 5; i < 8; i ++) {
            for (int k = 0; k < 8; k += 2) {
                 if (i%2 == 0) {
                     redPieces[i][k+1] = true;
                    }
                 else {
                     redPieces[i][k]=true;
                    }
            }
        }
        for (int i = 0; i < 3; i ++) {
            for (int k = 0; k < 8; k += 2) {
                 if (i%2 == 0) {
                     blackPieces[i][k+1] = true;
                    }
                 else {
                     blackPieces[i][k]=true;
                    }
            }
        }
        


        addMouseListener(this);
    }

    public void checkForJump(boolean red){
        //This method will take the boolean input 'red' and will determine if a red piece or black piece jumped another piece.
        //If it did jump, then the 2D array of the opposite color needs to be updated to show one less piece.
        if (redTurn) {
            if (blackPieces[(firstClick[1] + lastClick[1])/2][(firstClick[0] + lastClick[0])/2]) {
                if (Math.abs(lastClick[0]-firstClick[0]) ==2 && firstClick[1]- lastClick[1] ==2) {
                     blackPieces[(firstClick[1] + lastClick[1])/2][(firstClick[0] + lastClick[0])/2] = false;
                }
            }
        }
        if (!redTurn) {
            if (redPieces[(firstClick[1] + lastClick[1])/2][(firstClick[0] + lastClick[0])/2]) {
                if (Math.abs(lastClick[0]-firstClick[0]) ==2 && firstClick[1]- lastClick[1] ==-2){
                redPieces[(firstClick[1] + lastClick[1])/2][(firstClick[0] + lastClick[0])/2] = false;
            }
            }
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 200, 150);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, 400, 400);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Red's Turn", 50, 50);

        //Below is the algorithm to change turns
        if(lastClick[0] != -1){
            if(redTurn) {
                redPieces[lastClick[1]][lastClick[0]] = true;
                redPieces[firstClick[1]][firstClick[0]] = false;
                checkForJump(redTurn); 
            }
            else{
                blackPieces[lastClick[1]][lastClick[0]] = true;
                blackPieces[firstClick[1]][firstClick[0]] = false;
                checkForJump(redTurn); 
            }
            firstClick[0] = -1;
            firstClick[1] = -1;
            redTurn = !redTurn;

        }

        //Write the algorithm using nested loops to clear the board for the squares.
        //You will need 2 different set of nested loops.
        for (int i = 100; i < 500; i += 100) {
            for (int k = 100; k < 500;k +=100) {
                g.clearRect(i,k,50,50);
            }
        }
        for (int i = 150; i < 550; i += 100) {
            for (int k = 150; k < 550;k +=100) {
                g.clearRect(i,k,50,50);
            }
        }

        //Write the algorithm using nested loops with an if statement on the inside
        //to set all of the red and black pieces where they belong.
        for (int i = 0; i <8; i++) {
            for (int k = 0; k<8; k++) {
                if (redPieces[k][i]) {
                    g.setColor(Color.RED);
                    g.fillOval(i*50+100, k*50+100, 50, 50);
                }
                if (blackPieces[k][i]) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i*50+100, k*50+100, 50, 50);
                }
            }
            
        }
        //Below you need to write a series of if statements that will tell the user
        //what color piece has been clicked on. Run the .jar file to get an idea
        //of what it should look like.
        
        if (blackPieces[y][x]) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Black clicked.", 50, 75);
        }
        if (redPieces[y][x]){
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Red clicked.", 50, 75);
        }

        if(!redTurn){
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, 200, 100);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Black's Turn", 50, 50);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //This method will need to be updated to show which color piece was clicked on.


        x = e.getX();
        y = e.getY();
        System.out.println(x + ", " + y);
        
        x = (x - 100)/50;
        y = (y - 100)/50;
        
        if (blackPieces[y][x]) {
            System.out.println("Black clicked.");
        }
        if (redPieces[y][x]){
            System.out.println("Red clicked.");
        }
        
        if(firstClick[0] == -1){
            firstClick[0] = x;
            firstClick[1] = y;
            lastClick[0] = -1;
            lastClick[1] = -1;
        }
        else if(firstClick[0] != -1){
            lastClick[0] = x;
            lastClick[1] = y;
        }
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e){ }
    @Override
    public void mouseReleased(MouseEvent e){ }
    @Override
    public void mouseEntered(MouseEvent e){ }
    @Override
    public void mouseExited(MouseEvent e){ }


    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.getContentPane().add(new checkers());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
