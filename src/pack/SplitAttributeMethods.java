package pack;

import java.util.List;

import data.DataPoint;

public interface SplitAttributeMethods {

	/**
	 * Method to decide if the dataPoints belong to only one predefined class or not
	 * 
	 * @param dataPoints a list holding a part of the data with predefined
	 *                   information
	 * @return true if dataPoints belong to one class, else false; in case of empty
	 *         dataPoints return true
	 */
	public boolean isPureDataset(List<DataPoint> dataPoints);

	/**
	 * Method to get the rows of the original dataset that are in the left dataset
	 * for the given split criteria
	 * 
	 * @param dataset Table with the original dataset
	 * @return Table containing the rows of the dataset that are in the left dataset
	 *         for the given split criteria
	 */
	public List<DataPoint> getLeftDataset(List<DataPoint> dataset);

	/**
	 * Method to get the rows of the original dataset that are in the right dataset
	 * for the given split criteria
	 * 
	 * @param dataset Table with the original dataset
	 * @return Table containing the rows of the dataset that are in the right
	 *         dataset for the given split criteria
	 */
	public List<DataPoint> getRightDataset(List<DataPoint> dataset);

}