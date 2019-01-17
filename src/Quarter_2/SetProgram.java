package Quarter_2;

import java.util.TreeSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
public class SetProgram
{
	public SetProgram()
	{
		TreeSet<Integer> set=new TreeSet<Integer>();
		int count=0;
		while(count<20)
		{
			set.add((int)(Math.random()*100)+1);
			count++;
		}

		System.out.println("Set size: "+set.size());

		//Option 1 - Walking through a set
		System.out.println("\nOption 1: Use an Iterator");
		Iterator it=set.iterator();
		System.out.println( "Iterate through the Set" );
		while(it.hasNext())
		{
			int temp=(int)it.next();
			System.out.println("Value:"+ temp);
		}

		//Option 2 - Walking through a set
		System.out.println("\nOption 2: For-Each");
		System.out.println("Walking through the Set");
		for(Integer num:set)
			System.out.println("Value: "+num);



	}


	public static void main(String[] args)
	{
		SetProgram app=new SetProgram();
	}


}