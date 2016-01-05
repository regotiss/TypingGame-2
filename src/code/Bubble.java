package code;
import java.awt.*;
public class Bubble
{
	
	int x,y,dx,dy,r=55;
	Image i;
	boolean b;
	Novice a;
	char ch;
	public Bubble(int x,int y,Image i,int dx,int dy,char ch,Novice s)
	{
		this.x=x;
		this.y=y;	
		this.ch=ch;
		this.i=i;
		this.dx=dx;
		this.dy=dy;
		
		a=s;
		b=true;
	
	}
	public Bubble(int x,int y,Image i,int dx,int dy,int h,char ch,Novice s)
	{
		r=h;
		b=false;
		this.x=x;
		this.y=y;	
		this.ch=ch;
		this.i=i;
		this.dx=dx;
		this.dy=dy;
	
		a=s;
	
	}
	public void update()
	{
		move();
	}
	public boolean checkCollision(WordBubble b)
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int ballR=b.getRadius();
		
		int a=x-ballX;
		int bb=y-ballY;
		int collision=r+ballR;
		
		double c=Math.sqrt((double)(a*a)+(double)(bb*bb));
		if(c<collision)
		{
			return true;
		}
		else
			return false;
	}
	public void move()
	{
		if(x-r<0||x+r>a.getWidth())
			dx=-dx;
		if(y+r>a.getHeight())
			dy=-dy;
		x+=dx;
		y+=dy;
	}
	public void paint(Graphics g)
	{
		g.setFont(new Font("Arial",Font.BOLD,36));
		g.setColor(Color.white);
		if(b)
		{
			g.drawImage(i,x-r,y-r,r*2,r*2,a);
			g.drawString(""+ch,x-10,y);
		}
		else
		{
			g.drawImage(i,x,y,a);
			g.drawString(""+ch,x+35,y+(65));
		}
		
	}
	
}
