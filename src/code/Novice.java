package code;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Novice extends  JPanel implements Runnable,KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread t;
	WordBubble wb;
	boolean flg=true,alOvr;
	String ac,str;
	int speed;
	int k,cnt;
	char ch[];
	int dx,plusx,plusy;
	int level=1,tot=10;
	Bubble b[];
	static int width,height;
	private static JFrame f;
	Random r;
	int i,length;
	int score;
	boolean bons,get;
	char ofbonus;
	Image bak,bub,ii,star,bon,plus,w;
	Graphics doubleG;
	Bubble bonus;


	public Novice(String ac,int sp)
	{
		r=new Random();
		star=new ImageIcon(Novice.class.getResource("/images/star.gif")).getImage();
		bon=new ImageIcon(Novice.class.getResource("/images/02.png")).getImage();
	
		w=new ImageIcon(Novice.class.getResource("/images/flower_05.png")).getImage();
		plus=new ImageIcon(Novice.class.getResource("/images/plus.png")).getImage();
		wb=new WordBubble(300,100,w,10,-10,"hi",this);
		this.ac=ac;
		speed=sp;
		flg=true;
		init();
		bons=true;
		score=0;
		length=0;
		t=new Thread(this);
		t.start();
	}
	public void stop(){
		flg=false;
		JOptionPane.showMessageDialog(null, "GAME OVER\nYour Score:"+score);
		f.setVisible(false);
		f.dispose();
		MyGameApp.main(null);
	}
	public void init()
	{
		get=false;
		k=1;
		dx=10;
		cnt=0;
		
		b=new Bubble[10];
		ch=new char[10];
		r=new Random();
		bub=new ImageIcon(Novice.class.getResource("/images/b5.png")).getImage();
		for(int i=0;i<b.length;i++)
		{
			if(ac.equals("lower"))
			{
				ch[i]=(char)(r.nextInt(26)+97);
			}
			else if(ac.equals("upper"))
			{
				ch[i]=(char)(r.nextInt(26)+65);
			}
			else if(ac.equals("digit"))
			{
				ch[i]=(char)(r.nextInt(10)+48);
			}
			else if(ac.equals("moderate")||ac.equals("expert"))
			{
				dx=-10;
				ch[i]=(char)(r.nextInt(74)+48);
			}
			else
				ch[i]=(char)(r.nextInt(74)+48);
			
			b[i]=new Bubble(r.nextInt(1200),height-100,bub,0,dx,ch[i],this);
		}
		
		bak=new ImageIcon(Novice.class.getResource("/images/bak1.jpg")).getImage();
		

	}
	public void run()
	{
		while(flg)
		{
			
			check();
			if(ac.equals("expert"))
			wb.update();
			for(int i=0;i<k;i++)
			{
				if(b[i]!=null)
				{
					alOvr=false;
					b[i].update();
					if(ac.equals("expert")&&b[i].checkCollision(wb))
					{
						length++;
						wb.str+=ch[i];
						b[i]=null;
					}
					if(length>6)
						stop();

					if(b[i]!=null&&b[i].y<0)
					{
						if(score>=0)
						score-=10;
						else 
							stop();
						b[i]=null;
					}
				}
				
			}
			alOvr=true;	
			for(int i=0;i<b.length;i++)
			{
				if(b[i]!=null)
				{
					alOvr=false;
				}
			}
			if(this instanceof Moderate||this instanceof Expert)
			{
			
				if(bonus!=null)
					bonus.update();
				if(bonus!=null&&bonus.y<0)
				{
					get=false;
					bons=true;
					bonus=null;
				}
				if(bonus==null&&alOvr&&bons)
				{
					ofbonus=(char)(r.nextInt(74)+48);
					bonus=new Bubble(r.nextInt(1200),height-300,bon,0,dx,200,ofbonus,this);
				
					bons=false;
				}
			
			}
			if(alOvr)
			{	
				init();
			}

			repaint();
			try
			{
				Thread.sleep(speed);
			}
			catch (Exception e)
			{
			}
			cnt++;
		}
	}
	public void check()
	{
		if(cnt>((tot-level)*2))
		{
			r=new Random();
			
			cnt=0;
			k++;
			if(k>=b.length)
			{
				k=b.length;
			}
		}
	}
	
	public static void create(String s,int i)
	{
		f=new JFrame("Bubble Game");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();

		Novice nv;

		if(i>=70)
		nv=new Novice(s,i);
		else if(i>30)
		nv=new Moderate(s,i);
		else
		nv=new Expert(s,i);
		
		f.add(nv);
		f.addKeyListener(nv);
		//f.setLocationRelativeTo(null);
		
		f.setSize(width, height);
		f.setVisible(true);
	}
	public static void main(String[] args) 
	{
		create(args[0],Integer.parseInt(args[1]));
	}
	public void paint(Graphics g)
	{
		g.setFont(new Font("Arial",Font.BOLD,36));
		g.setColor(Color.white);
		g.drawImage(bak,0,0,getWidth(),getHeight(),this);
		g.drawImage(star,10,20,this);
		g.drawString(""+score,width-100,70);
		for(int i=0;i<k;i++)
		{
			if(b[i]!=null)
			b[i].paint(g);
		}
		if(bonus!=null)
			bonus.paint(g);
		if(get==true)
		{
			g.drawImage(plus,plusx,plusy,100,100,this);
			plusy-=10;
			if(plusy<0)
				get=false;
		}
		if(ac.equals("expert"))
		wb.paint(g);
	}
	public void update(Graphics g)
	{
		if(ii==null)
		{
			ii=createImage(this.getSize().width,this.getSize().height);
			doubleG=ii.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0,getSize().width,getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(ii,0,0,this);
	}

	public void keyTyped(KeyEvent e){
		char c=e.getKeyChar();
		if(c!=KeyEvent.CHAR_UNDEFINED)
		{
			for(int l=0;l<k;l++)
			{
				if(ch[l]==c&&ch[l]!=0)
				{
					ch[l]=0;
					b[l]=null;
				
					score+=10;
				}
				else if(ch[l]!=0)
				{
					if(ac.equals("experts"))
					{
						if(score>0)
						score-=10;
					}
				}
			}
			//for red ballon
			if(c==ofbonus)
			{
				
				if(bonus!=null)
				{
					get=true;
					plusx=bonus.x;
					plusy=bonus.y;
				}
				bons=true;
				bonus=null;
				score+=50;
			}
			if(ac.equals("expert"))
			{

			}

		}
	}
	public void keyPressed(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e){}
}
