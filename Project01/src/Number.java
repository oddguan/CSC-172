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
	Array array;
	int direction;
	
	public Number(){
		addKeyListener(this);
		array = new Array();
		array.addRandomNumber();
		array.addRandomNumber();
	}
	
	public void paintComponent(Graphics g){
		g.setFont(new Font("BOLD",1,40));
		for(int j=0;j<4;j++){
			for(int i=0;i<4;i++){
				if(array.getArr()[j][i]==0){
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
				
				g.fillRoundRect(i*100, j*100, 90, 90,10,10);
				g.setColor(new Color(255,102,102));
				if(array.getArr()[j][i]!=0){
					g.drawString(Integer.toString(array.getArr()[j][i]), 15+100*i, 50+100*j);
				}
		
			}
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("2048");
		Number n = new Number();
		frame.setSize(500, 500);
		frame.add(n);
		frame.addKeyListener(n);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_A){
			direction = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			direction = 2;
		}
		if(e.getKeyCode()==KeyEvent.VK_W){
			direction = 3;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			direction = 4;
		}
		if(e.getKeyCode()==KeyEvent.VK_Q){
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to quit this game?", "quit", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_R){
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to restart this game?", "restart", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				array.reset();
			}
		}
		
		
		array.move(direction);
		array.merge(direction);
		
		if(array.m){
			array.move(direction);
			array.addRandomNumber();
			repaint();
		}
		else if(array.n){
			array.addRandomNumber();
			repaint();
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
