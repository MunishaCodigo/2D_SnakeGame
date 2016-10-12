import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.*;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
	//variables
	
	private int[] snakexlength= new int[750];
	private int[] snakeylength= new int[750];
	//motion of snake
	private boolean left= false;
	private boolean right= false;
	private boolean up= false;
	private boolean down= false;
	//snake face variable
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int score=0;
	
	private int move=0;
	
	private Timer timer;
	private int delay = 100;

	//snake body
	private ImageIcon snakeimage;
	private int lengthofsnake= 3;
	//title of game
	private ImageIcon titleImage;
	//food image
	private ImageIcon enemyimage;
	//food position 
	
	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
			                   500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
                               500,525,550,575,600,625} ;
	//random x y position
	private Random rgen= new Random();
	private int xpos= rgen.nextInt(34);
	private int ypos= rgen.nextInt(23);
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g )
	{
		//game just started
		if(move==0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
		
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
		}
		
		
		// draw title image border
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		//draw title image
		
		titleImage= new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border of gameplay
		
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background the gameplay
		
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);	
		
		//draw score field
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Scores:"+score, 750, 30);
		

		//draw snake length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Snake Length:"+lengthofsnake, 750, 50);
		
		//draw snake
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int i=0;i<lengthofsnake;i++)
		{
			
			if(i==0 && right)
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}	
			if(i==0 && left)
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}	
			if(i==0 && up)
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);	
			}	
			if(i==0 && down)
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);		
			}	
			if(i!=0)
			{
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);	
			}
		}
		//food image
		enemyimage = new ImageIcon("enemy.png");
		
		//check if food collides with snake
		
		if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0])
		{
			score++;
			lengthofsnake++;
			xpos= rgen.nextInt(34);
			ypos= rgen.nextInt(23);
		}
		enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
		
		//Check if snake collides with itself
		for(int i=1;i<lengthofsnake;i++)
		{
			if(snakexlength[i]==snakexlength[0]&&snakeylength[i]==snakeylength[0])
			{
				right=false;
				left=false;
				up=false;
				down=false;
				
				//game over message
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("GAME OVER", 300, 300);
				
				//Start New message
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,30));
				g.drawString("Press Space Bar To Restart", 250, 360);			
			}
			
		}
		
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1; r>=0 ; r--)
			{
				snakeylength[r+1]= snakeylength[r];
			}
			for(int r=lengthofsnake; r>=0 ; r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]+25;
					
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
					
				}
				if(snakexlength[r]>850)
				{
					snakexlength[r]=25;
				}
				
			}
			repaint();
		}
		if(left)
		{

			for(int r=lengthofsnake-1; r>=0 ; r--)
			{
				snakeylength[r+1]= snakeylength[r];
			}
			for(int r=lengthofsnake; r>=0 ; r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]-25;
					
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
					
				}
				if(snakexlength[r]<25)
				{
					snakexlength[r]=850;
				}
				
			}
			repaint();
		}
		if(up)
		{

			for(int r=lengthofsnake-1; r>=0 ; r--)
			{
				snakexlength[r+1]= snakexlength[r];
			}
			for(int r=lengthofsnake; r>=0 ; r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]-25;
					
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
					
				}
				if(snakeylength[r] < 75)
				{
					snakeylength[r]=625;
				}
				
			}
			repaint();
		}
		if(down)
		{

			for(int r=lengthofsnake-1; r>=0 ; r--)
			{
				snakexlength[r+1]= snakexlength[r];
			}
			for(int r=lengthofsnake; r>=0 ; r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]+25;
					
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
					
				}
				if(snakeylength[r]>625)
				{
					snakeylength[r]=75;
				}
				
			}
			repaint();
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		//press spacebar for new game
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			move=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		//movement of snake by keys
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			move++;
			right=true;
			if(left!=true)
			{
				right=true;
				
			}
			else
			{
				right=false;
				left=true;
			}

			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			move++;
			left=true;
			if(right!=true)
			{
				left=true;
				
			}
			else
			{
				left=false;
				right=true;
			}
		
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			move++;
			up=true;
			if(down!=true)
			{
				up=true;
				
			}
			else
			{
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			move++;
			down=true;
			if(up!=true)
			{
				down=true;
				
			}
			else
			{
				down=false;
				up=true;
			}
			left=false;
			right=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	


}
