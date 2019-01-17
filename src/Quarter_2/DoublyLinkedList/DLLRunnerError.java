package Quarter_2.DoublyLinkedList;

public class DLLRunnerError
{
	public DLLRunnerError()
	{
		DoublyLinkedList<Integer> list=new DoublyLinkedList<Integer>();
		list.add(10);
		list.add(0,5);
		list.add(list.size()-1,8);
		list.add(1,12);
		list.add(20);
		list.add(25);
		list.add(4,100);
		list.add(0,0);

//		list.add(-1,5000);		//Now, check this one!!! This should give an ArrayIndexOutOfBoundsError


		list.add(list.size()-1,1000);
		System.out.println("Size: "+list.size());

		System.out.println("List: "+list);
		System.out.println("List reversed: "+list.toReversedString());

		System.out.println("\nValues at Front and End");
			System.out.println("\tFront: "+list.getRoot().getValue());
			System.out.println("\tEnd: "+list.getEnd().getValue());

		list.remove(2);
		list.remove(3);
		list.remove(0);
		list.remove(list.size()-1);

		System.out.println("\nRemove values at indexes 2, 3, 0, and then size()-1 respectively.");
			System.out.println("\tList: "+list);
			System.out.println("\tList reversed: "+list.toReversedString());


		System.out.println("\nContains Test:");
		System.out.println("\tContains 4: "+list.contains(4));
		System.out.println("\tContains 20: "+list.contains(20));



		list.clear();
		System.out.println("\nClear List!");
			System.out.println("\tList: "+list);
			System.out.println("\tList reversed: "+list.toReversedString());




	}
	public static void main(String[] args)
	{
		DLLRunnerError app=new DLLRunnerError();
	}

}
