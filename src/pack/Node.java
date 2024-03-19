package pack;

import java.util.List;
import data.DataPoint;

/**
 * This class represents a node of a decision tree. For inner nodes it should
 * contain a splitAttribute and left and right child nodes
 * 
 * @author Frank Mehler
 */
public class Node {
	private Node left; // left child node
	private Node right;
	private SplitAttribute splitAttribute; // which attribute with which value splits the data
	private List<DataPoint> dataPoints; // data of the current node
	private String nodeInformation;

	public Node() {
		this.nodeInformation = "no information";
	}

	/**
	 * Function that says if a node is a leaf node
	 * 
	 * @return boolean that says if the node is a leaf or not
	 */
	public boolean isLeaf() {
		if (left == null && right == null)
			return true;
		return false;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public SplitAttribute getSplitAttribute() {
		return splitAttribute;
	}

	public void setSplitAttribute(SplitAttribute splitAttribute) {
		this.splitAttribute = splitAttribute;
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public String getNodeInformation() {
		return nodeInformation;
	}

	public void setNodeInformation(String nodeInformation) {
		this.nodeInformation = nodeInformation;
	}

	@Override
	public String toString() {
		String returnString = "Node [splitAttribute=" + splitAttribute;
		returnString += ", dataPoints=" + dataPoints.size() + ", info=" + nodeInformation + "]";
		return returnString;
	}

	/**
	 * @param newInfo adds new Information to a node
	 */
	public void addNodeInformation(String newInfo) {
		this.nodeInformation += newInfo;
	}
}
