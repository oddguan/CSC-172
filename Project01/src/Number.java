import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Number extends JComponent implements KeyListener{
	private Array array;
	private int direction;
	
	private Number(){ //constructor for the class "Number"
		addKeyListener(this); //add in the key listener
		array = new Array(); //initilize the "Array"
		array.addRandomNumber(); //add in two numbers at the beginning
		array.addRandomNumber();
	}


	
	public void paintComponent(Graphics g){ //override the paintComponent method
		g.setFont(new Font("BOLD",1,40)); //set the font
		for(int j=0;j<4;j++){
			for(int i=0;i<4;i++){
				if(array.getArr()[j][i]==0){ //set different color backgrounds for different numbers
					g.setColor(new Color(176,166,166));
				}
				else if(array.getArr()[j][i]==2){
					g.setColor(new Color(229,255,204));
				}
				else if(array.getArr()[j][i]==4){
					g.setColor(new Color(178,255,102));
				}
				else if(array.getArr()[j][i]==8){
					g.setColor(new Color(128,255,0));
				}
				else if(array.getArr()[j][i]==16){
					g.setColor(new Color(76,153,0));
				}
				else if(array.getArr()[j][i]==32){
					g.setColor(new Color(229,204,255));
				}
				else if(array.getArr()[j][i]==64){
					g.setColor(new Color(178,102,255));
				}
				else if(array.getArr()[j][i]==128){
					g.setColor(new Color(127,0,255));
				}
				else if(array.getArr()[j][i]==256){
					g.setColor(new Color(76,0,153));
				}
				else if(array.getArr()[j][i]==512){
					g.setColor(new Color(153,0,76));
				}
				else{
					g.setFont(new Font("BOLD",1,30));
					g.setColor(Color.BLACK);
				}
				
				g.fillRoundRect(i*100, j*100, 90, 90,10,10); //paint the background for each number
				g.setColor(new Color(255,102,102));

				if(array.getArr()[j][i]!=0){ //paint the number
					g.drawString(Integer.toString(array.getArr()[j][i]), 15+100*i, 50+100*j);
				}
			}
		}
		g.setFont(new Font("BOLD",1,20)); //paint the current moves made
		g.setColor(Color.BLACK);
		g.drawString("moves:"+Integer.toString(array.getMoves()),400,50);
	}
	
	public static void main(String[] args){ //main method for running the game
		JFrame frame = new JFrame("2048");
		Number n = new Number(); //initilize the "Number"
		frame.setSize(550, 500); //set the preferred size for the frame
		frame.add(n); //add the "Number" into the frame
		frame.addKeyListener(n); //add the key listener
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true); //display the game frame
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_A){ //if left pressed
			direction = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){//if right pressed
			direction = 2;
		}
		if(e.getKeyCode()==KeyEvent.VK_W){//if up pressed
			direction = 3;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){//if down pressed
			direction = 4;
		}
		if(e.getKeyCode()==KeyEvent.VK_Q){//if Q pressed, prompt the user if wanting to quit the game
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to quit this game?", "quit", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_R){//if R pressed, prompt the user if wanting to restart the game
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to restart this game?", "restart", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				array.reset();
			}
		}
		
		
		array.move(direction); //move to the given direction
		array.merge(direction); //merge to the given direction
		
		if(array.m){ //if successfully moved
			array.move(direction); // move again
			array.addRandomNumber(); //add in a new number
			array.setMoves(array.getMoves()+1); //moves +1
			repaint(); //repaint the frame
		}
		else if(array.n){ //if successfully merged
			array.addRandomNumber(); //add in a new number
			array.setMoves(array.getMoves()+1); //moves +1
			repaint(); //repaint the frame
		}

		if(!array.checked()){ //if the array is not checked, that means game is over
			//prompt the user game is over, the final score, and if the user want to restart the game
			int reply = JOptionPane.showConfirmDialog(null, "Game Over! Your final score is:" +array.getMoves()+ ". Largest number:"+array.getMax()+". Do you want to play again?", "game over", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				array.reset();
				repaint();
			}
			else{
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
