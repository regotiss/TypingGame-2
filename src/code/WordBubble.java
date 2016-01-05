package code;
import java.awt.*;
import javax.swing.*;
public class WordBubble 
{
	int x,y,dx,dy;
	String str;
	JPanel p;
	int r=80;
	Image i;
	public WordBubble(int x,int y,Image i,int dx,int dy,String str,JPanel p)
	{
		this.i=i;
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.str=str;
		this.p=p;
	}
	public void update()
	{
		move();
	}
	public void move()
	{
		if(x-r<0||x+r>p.getWidth())
			dx=-dx;
		if(y+r>p.getHeight()||y-r<0)
			dy=-dy;
		x+=dx;
		y+=dy;
	}
	public void paint(Graphics g)
	{
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawImage(i,x-r,y-r,r*2,r*2,p);
		g.setColor(Color.white);
		g.drawString(str,x-40,y);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public double getDx()
	{
		return dx;
	}
	public double getDy()
	{
		return dy;
	}
	public int getRadius()
	{
		return r;
	}
}
