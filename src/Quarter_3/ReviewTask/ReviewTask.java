package Quarter_3.ReviewTask;

import java.util.*;

public class ReviewTask {

	public static void main(String[] args) {

		System.out.println("#1: Fill ArrayList");
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < (int) (Math.random() * 100 + 50); i++)
			list.add((int) (Math.random() * 99 + 1));
		System.out.println();

		System.out.println("#2: Display ArrayList");
		System.out.println(list);
		System.out.println();

		System.out.println("#3: Create 2D Array");
		int size = (int) (Math.ceil(Math.sqrt(list.size())));
		int[][] numbers = new int[size][size];
		System.out.println();

		System.out.println("#3: Remove from ArrayList and put into 2D Array");
		while (!list.isEmpty()) {
			int x = (int) (Math.random() * numbers.length);
			int y = (int) (Math.random() * numbers[0].length);
			if (numbers[x][y] == 0)
				numbers[x][y] = list.remove(0);
		}
		System.out.println();

		System.out.println("#4: Display 2D Array");
		for (int[] arr : numbers) {
			for (int number : arr)
				System.out.print(number + "\t");
			System.out.println();
		}
		System.out.println();

		System.out.println("#5: Remove from ArrayList and put into 2D Array");
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < numbers[0].length; i++)
			for (int[] number : numbers)
				stack.push(number[i]);
		System.out.println();

		System.out.println("#6: Display the Stack");
		for (Integer integer : stack)
			System.out.print(integer + " ");
		System.out.println();
		System.out.println();

		System.out.println("#7: Remove the values from the Stack and into a HashSet");
		HashSet<Integer> hashSet = new HashSet<>();
		while (!stack.empty()) {
			int num = stack.pop();
			System.out.print(num + " ");
			hashSet.add(num);
		}
		System.out.println();
		System.out.println();

		System.out.println("#8: Display the HashSet");
		for (Integer integer : hashSet)
			System.out.print(integer + " ");
		System.out.println();

		System.out.println("#9: Remove the values from the HashSet and into a Priority Queue");
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		Iterator<Integer> iterator = hashSet.iterator();
		while (iterator.hasNext()) {
			queue.add(iterator.next());
			iterator.remove();
		}
		System.out.println();

		System.out.println("#10: Display the PriorityQueue");
		for (Integer integer : hashSet)
			System.out.println(integer);
		System.out.println();

		System.out.println("#11: Remove the values from the PriorityQueue and into a TreeMap");
		TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int currentKey = -1;
		while (queue.size() > 0) {
			if (queue.peek() % 2 == 0) {
				currentKey = queue.peek();
				map.put(queue.poll(), new TreeSet<>());
			}
			else if (currentKey != -1)
				map.get(currentKey).add(queue.poll());
		}
		System.out.println();

		System.out.println("#12: Display the TreeMap");
		for (Integer key: map.keySet())
			System.out.println(key + " " + map.get(key));

	}

}
