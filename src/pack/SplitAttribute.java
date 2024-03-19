package pack;

/**
 * Class that holds a split criteria for a node of a decision tree. It contains
 * the name of the attribute and the value the split is executed on. It can
 * split tables on that split criteria. Currently the supported type for the
 * values of a split criteria is Double
 */

public class SplitAttribute {
	private String attrName;
	private Double value;

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
