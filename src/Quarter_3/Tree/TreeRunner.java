package Quarter_3.Tree;

public class TreeRunner {

	public static void main(String[] args) {

		Tree<Character> tree = new Tree<>();
		for (int i = 0; i <= 26; i++)
			tree.add((char) (Math.random() * 26 + 'a'));

		char average = (char) tree.preOrder().replaceAll("[\\[, \\]]", "").chars().average().getAsDouble();
		System.out.println(average);
		System.out.println(tree.size());
		System.out.println();
		System.out.println(tree.preOrder());
		System.out.println(tree.inOrder());
		System.out.println(tree.postOrder());
		System.out.println();

		Tree<Character> preOrder = new Tree<>();
		for (char c : tree.preOrder().replaceAll("[\\[\\], ]", "").toCharArray())
			preOrder.add(c);
		System.out.println(preOrder.preOrder());
		System.out.println(preOrder.inOrder());
		System.out.println(preOrder.postOrder());
		System.out.println();

		Tree<Character> inOrder = new Tree<>();
		for (char c : tree.inOrder().replaceAll("[\\[\\], ]", "").toCharArray())
			inOrder.add(c);
		System.out.println(inOrder.preOrder());
		System.out.println(inOrder.inOrder());
		System.out.println(inOrder.postOrder());
		System.out.println();

		Tree<Character> postOrder = new Tree<>();
		for (char c : tree.postOrder().replaceAll("[\\[\\], ]", "").toCharArray())
			postOrder.add(c);
		System.out.println(postOrder.preOrder());
		System.out.println(postOrder.inOrder());
		System.out.println(postOrder.postOrder());
		System.out.println();
	}

}
