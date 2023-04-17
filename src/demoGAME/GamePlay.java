package demoGAME;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePlay extends JPanel implements ActionListener,KeyListener{
	 private boolean play=false;
	  private int totalBricks=21;
	  private int score=0;
	  private Timer Timer;
	  private int delay=8;
	  private int playerx=350;
	  private int ballPosx=120;
	  private int ballPosy=350;
	  private int ballXdir=-1;
	  private int ballYdir=-2;
	  private MapGenerator map;

	  public GamePlay(){
		   addKeyListener(this);
		   setFocusable(true);
		   setFocusTraversalKeysEnabled(true);
		   Timer=new Timer(delay,this);
		   Timer.start();
		   map=new MapGenerator(3,7);
		}
	  public void paint(Graphics g){ 
		    g.setColor(Color.black);
		    g.fillRect(1,1,692,592);
		    g.setColor(Color.yellow);
		    g.fillRect(0,0,692,3);
		    g.fillRect(0,3,3,592);
		    g.fillRect(691,3,3,592);
		    g.setColor(Color.green);
		    g.fillRect(playerx,550,100,8);
		    map.draw((Graphics2D)g);
		    g.setColor(Color.red);
		    g.fillOval(ballPosx,ballPosy,20,20);
		    g.setColor(Color.green);
		    g.setFont(new Font("serif",Font.BOLD,20));
		    g.drawString("score:"+score, 550,30);
		    if(ballPosy>=570){
		        play=false;
		        ballXdir=0;
		        ballYdir=0;
		        g.setColor(Color.green);
		        g.setFont(new Font("serif",Font.BOLD,30));
		        g.drawString("+score :"+score,200,300);
		        g.setFont(new Font("serif",Font.BOLD,25));
		        g.drawString("Press Enter to Restart!!"+score,230,350);
		    
		    }
		}

	    private void moveLeft(){
	       
	       play=true;
	        playerx-=20;
	    }
	   private void moveRight(){
	       
	       play =true;
	        playerx+=20;
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        if(e.getKeyCode()==KeyEvent.VK_LEFT){
	            if(playerx<=0)
	                playerx=0;
	            else
	            moveLeft();
	        }
	            
	        
	        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
	            if(playerx>=600)
	                playerx=600;
	            else
	            moveRight();
	        }
	        if(e.getKeyCode()==KeyEvent.VK_ENTER){
	            if(!play){
	                score=0;
	                totalBricks=21;
	                ballPosx=120;
	                ballPosy=350;
	                ballXdir=-1;
	                ballYdir=-2;
	                playerx=320;
	                map=new MapGenerator(3,7);
	            }
	        }
	        repaint();
	    }   
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(play){
	            
	             
	            if(ballPosx<=0){
	                 ballXdir=-ballXdir;
	             }
	            if(ballPosx>=670){
	                ballXdir=-ballXdir;
	            }
	            if(ballPosy<=0){
	                 ballYdir=-ballYdir;
	             }
	            Rectangle ballRect=new Rectangle(ballPosx,ballPosy,20,20);
	             Rectangle paddleRect=new Rectangle(playerx,550,100,8);
	             if(ballRect.intersects(paddleRect)){
	                 ballYdir=-ballXdir;
	             }
	             A:for(int i=0;i<map.map.length;i++){
	                 for(int j=0;j<map.map[0].length;j++){
	                     if(map.map[i][j]>0){
	                         int width=map.brickWidth;
	                         int height=map.brickHeight;
	                         int brickXpos=80+j*width;
	                         int brickYpos=80+i*height;
	                         
	                         Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);
	                         if(ballRect.intersects(brickRect)){
	                             map.setBrick(0, i, j);
	                             totalBricks--;
	                             score+=5;
	                             if(ballPosx+19<=brickXpos || ballPosy+1>=brickXpos+width){
	                                 ballXdir=-ballXdir;
	                             }
	                             else{
	                                 ballYdir=-ballYdir;
	                             }
	                             break A;
	                         }
	                     }
	                 }
	             }
	             ballPosx+=ballXdir;
	             ballPosy+=ballYdir;
	         }
	         repaint();
	     }
		@Override
		public void keyTyped(KeyEvent e) {}
				// TODO Auto-generated method stub
				
			
			@Override
			public void keyReleased(KeyEvent e) {}
				// TODO Auto-generated method stub
				
			    
		
}	
		