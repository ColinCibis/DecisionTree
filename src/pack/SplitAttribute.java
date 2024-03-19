package pack;

import data.DataPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds a split criteria for a node of a decision tree. It contains
 * the name of the attribute and the value the split is executed on. It can
 * split tables on that split criteria. Currently the supported type for the
 * values of a split criteria is Double
 */

public class SplitAttribute implements SplitAttributeMethods {
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

	@Override
	public boolean isPureDataset(List<DataPoint> dataPoints) {
		String dataClass = dataPoints.get(0).getPredefClass();
		for (DataPoint dp : dataPoints) {
			if (!dp.getPredefClass().equals(dataClass)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<DataPoint> getLeftDataset(List<DataPoint> dataset) {
		List<DataPoint> leftSet = new ArrayList<>();

		for (DataPoint dp : dataset) {
			Double valueForAttribute = dp.getValueForAttribute(attrName);
			if (valueForAttribute != null && valueForAttribute <= value) {
				leftSet.add(dp);
			}
		}
		return leftSet;
	}

	@Override
	public List<DataPoint> getRightDataset(List<DataPoint> dataset) {
		List<DataPoint> rightSet = new ArrayList<>();

		for (DataPoint dp : dataset) {
			Double valueForAttribute = dp.getValueForAttribute(attrName);
			if (valueForAttribute != null && valueForAttribute > this.value) {
				rightSet.add(dp);
			}
		}
		return rightSet;
	}
}
