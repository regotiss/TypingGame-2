package code;
class Expert extends Novice 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Expert(String s,int n)
	{
		super(s,n);
	}
	public static void main(String[] args) 
	{
		create(args[0],Integer.parseInt(args[1]));
	}
}
