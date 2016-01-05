package code;
public class Moderate extends Novice
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Moderate(String s,int n)
	{
		super(s,n);
	}
	
	public static void main(String[] args) 
	{
		create(args[0],Integer.parseInt(args[1]));
	}
}
