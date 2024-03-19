package pack;

import java.io.PrintStream;

/**
 * This class can create a simple printed version of a given decision tree Idea
 * from https://www.baeldung.com/java-print-binary-tree-diagram
 * 
 * @author Frank Mehler
 */
public class TreePrinter {
	private Node node;

	/**
	 * Create a DecisionTreePrinter that prints the given tree to an output stream
	 * 
	 * @param node root node of the decision tree
	 */
	public TreePrinter(Node node) {
		this.node = node;
	}

	/**
	 *
	 * @param sb      StringBuilder that will contain the output
	 * @param padding String that contains the padding for the output e.g " "
	 * @param pointer String that contains the pointer for the output e.g " "
	 * @param node    current node
	 */
	public void traversePreOrder(StringBuilder sb, String padding, String pointer, Node node) {
		if (node != null) {
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.getNodeInformation());
			if (!node.isLeaf()) { // if it is not a leaf...
				sb.append(", Split Attribute: " + node.getSplitAttribute().getAttrName() + " "
						+ node.getSplitAttribute().getValue());
			}

			sb.append("\n");

			StringBuilder paddingBuilder = new StringBuilder(padding);
			paddingBuilder.append("│  ");

			String paddingForBoth = paddingBuilder.toString();
			String pointerForRight = "└──";
			String pointerForLeft = (node.getRight() != null) ? "├──" : "└──";

			traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeft());
			traversePreOrder(sb, paddingForBoth, pointerForRight, node.getRight());
		}
	}

	/**
	 * Method to print the decision tree
	 * 
	 * @param os output stream for the tree, e.g. System.out for console window
	 */
	public void print(PrintStream os) {
		StringBuilder sb = new StringBuilder();
		traversePreOrder(sb, "", "", this.node);
		os.print(sb.toString());
	}
}
