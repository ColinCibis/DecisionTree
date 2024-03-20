package data;

import java.text.DecimalFormat;
import java.util.*;

/**
 * This class provides the basic data structure for classification
 * 
 * @author Frank Mehler
 *
 */
public class DataPoint {
	private List<Double> data; // the raw data
	private List<String> attributeNames; // column names of attributes, e.g. "sepal-length"
	private String predefClass; // predefined category for already classified data

	public DataPoint() {
		this.data = new ArrayList<>();
		this.attributeNames = new ArrayList<>();
		this.predefClass = "Initial Class";
	}

	/**
	 * @param attrName the name of the attribute we are looking for content
	 * @return value corresponding to an attribute in the data point, null if
	 *         attribute does not exist Example: attrName = "sepal-length", then the
	 *         corresponding value, e.g. 5.1 is returned
	 */
	public Double getValueForAttribute(String attrName) {
		if (attributeNames.size() != data.size()) {
			System.out.println("Error in getValueForAttribute()");
			return null;

		}
		for (int i = 0; i < this.attributeNames.size(); i++) {
			if (this.attributeNames.get(i).equals(attrName))
				return this.data.get(i);
		}
		return null;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(List<String> attributeNames) {
		this.attributeNames = attributeNames;
	}

	public String getPredefClass() {
		return predefClass;
	}

	public void setPredefClass(String predefClass) {
		this.predefClass = predefClass;
	}

	@Override
	public String toString() {
		String dataString = "";
		DecimalFormat formatter = new DecimalFormat("0.00");
		for (int i = 0; i < this.data.size(); i++) {
			dataString += this.attributeNames.get(i) + ": ";
			dataString += formatter.format(this.data.get(i));
			if (i < this.data.size() - 1)
				dataString += "\t";
		}
		return dataString + "\t " + this.predefClass;
	}
}
