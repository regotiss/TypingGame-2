package code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyGameApp extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	JRadioButton novice,mod,exp,sel,low,upp,dig,all;
	JPanel p,noviceP; 
	JButton next,nnext;
	static JFrame f;

	public MyGameApp()
	{

		p=new JPanel();
		p.setLayout(new GridLayout(0,4));
		p.setSize(300,180);

		novice=new JRadioButton("novice",true);
		mod=new JRadioButton("moderate");
		exp=new JRadioButton("expert");

		ButtonGroup b=new ButtonGroup();
		b.add(novice);
		b.add(mod);
		b.add(exp);

		p.add(novice);
		p.add(mod);
		p.add(exp);

		next=new JButton("next");
		p.add(next);

		add(p);
		
		sel=novice;



		novice.addActionListener(this);
		mod.addActionListener(this);
		exp.addActionListener(this);
		next.addActionListener(this);

		setSize(340,240);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	public static void create()
	{
		f=new JFrame("Bubble Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new MyGameApp());
		f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) 
	{
	
		create();
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==next)
		{
			p.setVisible(false);
			if(sel==novice)
			{
				noviceP=new JPanel();
				noviceP.setLayout(new GridLayout(0,5));
				noviceP.setSize(300,180);

				low=new JRadioButton("lower",true);
				sel=low;
				upp=new JRadioButton("upper");
				dig=new JRadioButton("digit");
				all=new JRadioButton("all");

				ButtonGroup b=new ButtonGroup();
				b.add(low);
				b.add(upp);
				b.add(dig);
				b.add(all);

				noviceP.add(low);
				noviceP.add(upp);
				noviceP.add(dig);
				noviceP.add(all);
				
				nnext=new JButton("next");
				noviceP.add(nnext);

				nnext.addActionListener(this);
				low.addActionListener(this);
				upp.addActionListener(this);
				dig.addActionListener(this);
				all.addActionListener(this);

				add(noviceP);
			}
			else if(sel==mod)
			{
				String s[]={sel.getActionCommand(),"40"};
				Moderate.main(s);
			}
			else
			{
				String s[]={sel.getActionCommand(),"30"};
				Expert.main(s);
			}

			
		}
		else if(e.getSource()==nnext)
		{
			setVisible(false);

			f.setVisible(false);
			String s[]={sel.getActionCommand(),"70"};
			Novice.main(s);
		}
		else
		{
			sel=(JRadioButton)e.getSource();
		}
	}
}
