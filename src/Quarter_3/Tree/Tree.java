package Quarter_3.Tree;

public class Tree<E extends Comparable<E>> {

	private TreeNode<E> root;

	private int size;

	public Tree() {
		size = 0;
	}

	public void add(E value) {
		if (root == null) {
			root = new TreeNode<>(value);
			size++;
		}
		else
			add(root, value);
	}

	private void add(TreeNode<E> node, E value) {
		int compare = value.compareTo(node.getValue());
		if (compare > 0)
			if (node.getRight() == null) {
				node.setRight(new TreeNode<>(value));
				size++;
			}
			else
				add(node.getRight(), value);
		else if (compare < 0)
			if (node.getLeft() == null) {
				node.setLeft(new TreeNode<>(value));
				size++;
			}
			else
				add(node.getLeft(), value);
	}

	public int size() {
		return size;
	}

	public String preOrder() {
		if (root == null)
			return "[]";
		return "[" + preOrder(root) + "]";
	}

	private String preOrder(TreeNode<E> node) {
		String preOrder = node.toString();
		if (node.getLeft() != null)
			preOrder += ", " + preOrder(node.getLeft());
		if (node.getRight() != null)
			preOrder += ", " + preOrder(node.getRight());
		return preOrder;
	}

	public String inOrder() {
		if (root == null)
			return "[]";
		return "[" + inOrder(root) + "]";
	}

	private String inOrder(TreeNode<E> node) {
		String inOrder = "";
		if (node.getLeft() != null)
			inOrder += inOrder(node.getLeft()) + ", ";
		inOrder += node.toString();
		if (node.getRight() != null)
			inOrder += ", " + inOrder(node.getRight());
		return inOrder;
	}

	public String postOrder() {
		if (root == null)
			return "[]";
		return "[" + postOrder(root) + "]";
	}

	private String postOrder(TreeNode<E> node) {
		String postOrder = "";
		if (node.getLeft() != null)
			postOrder += postOrder(node.getLeft()) + ", ";
		if (node.getRight() != null)
			postOrder += postOrder(node.getRight()) + ", ";
		postOrder += node.toString();
		return postOrder;
	}

	private class TreeNode<E extends Comparable<E>> {

		private E value;
		private TreeNode<E> left, right, parent;

		private TreeNode(E value) { this.value = value; }

		public E getValue() { return value; }

		public TreeNode<E> getLeft() { return left; }
		public void setLeft(TreeNode<E> left) { this.left = left; }

		public TreeNode<E> getRight() { return right; }
		public void setRight(TreeNode<E> right) { this.right = right; }

		@Override
		public String toString() {
			return value.toString();
		}
	}

}
