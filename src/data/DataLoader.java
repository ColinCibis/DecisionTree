package data;

import java.io.*;
import java.nio.file.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * This class provides data from a file for machine learning purpose, but... it
 * will only work correctly, if the class/category is the last information
 * 
 * @author Frank Mehler
 */
public class DataLoader {
	private static final String SEPARATOR = ";"; // splitting symbol
	private Path csvFilePath; // where is the data
	private List<String> listOfAttrNames; // column names for heading
	private List<DataPoint> listOfAllPoints; // the data which will be loaded


	/**
	 * Read a csv file with first two rows for structure information
	 * 
	 * @param dataFile location of the datafile
	 * @throws FileNotFoundException if file does not exist in this path
	 */
	public DataLoader(String dataFile) throws FileNotFoundException {
		this.csvFilePath = null;

		Path relativePath = Path.of(dataFile);
		Path absolutePath = relativePath.normalize().toAbsolutePath();
		boolean fileExists = Files.exists(absolutePath);
		System.out.println("Path of dataFile: " + absolutePath + " exists? " + fileExists);
		if (fileExists)
			this.csvFilePath = absolutePath;
		else
			throw new FileNotFoundException("File not found in DataLoader");

		parseContent();
	}

	
	public List<String> getListOfAttrNames() {
		return listOfAttrNames;
	}


	public void setListOfAttrNames(List<String> listOfAttrNames) {
		this.listOfAttrNames = listOfAttrNames;
	}


	public List<DataPoint> getListOfAllPoints() {
		return listOfAllPoints;
	}


	public void setListOfAllPoints(List<DataPoint> listOfAllPoints) {
		this.listOfAllPoints = listOfAllPoints;
	}


	/**
	 * This method copies the input data to data structures of this class
	 */
	private void parseContent() {
		String row = "";
		try (BufferedReader csvReader = new BufferedReader(new FileReader(this.csvFilePath.toString()))) {
			// read the first line in the file for attribute names
			if ((row = csvReader.readLine()) != null) {
				parseAttributes(row);
			}
			// collect all data from the following lines
			this.listOfAllPoints = new ArrayList<>();
			while ((row = csvReader.readLine()) != null) {
				parseData(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracting the attributes-headline of the input file
	 * 
	 * @param row contains the line of the input file
	 */
	private void parseAttributes(String row) {
		this.listOfAttrNames = new ArrayList<>();
		String[] data = row.split(SEPARATOR);
		// description of column information
		Collections.addAll(this.listOfAttrNames, data);
		// remove last entry which is no attribute, but the classification column
		this.listOfAttrNames.remove(listOfAttrNames.size() - 1);
	}

	/**
	 * Extracting the data of the input file
	 * 
	 * @param row contains the line of the input file
	 */
	private void parseData(String row) {
		NumberFormat format = NumberFormat.getNumberInstance(Locale.GERMAN);
		String[] data = row.split(SEPARATOR);
		// dimension of data, i.e. number of attributes without last attribute =
		// class-information
		final int DIMENSION_OF_DATA = data.length - 1;
		DataPoint dataPoint = new DataPoint();
		// the first entries are data
		for (int i = 0; i < DIMENSION_OF_DATA; i++) {
			Number val;
			try {
				val = format.parse(data[i]);
				// add data content
				dataPoint.getData().add(val.doubleValue());
				// add column description
				dataPoint.getAttributeNames().add(this.listOfAttrNames.get(i));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// last entry is the class/category of the data
		if (data.length == DIMENSION_OF_DATA + 1) {
			String predefined = data[DIMENSION_OF_DATA];
			dataPoint.setPredefClass(predefined);
		}
		this.listOfAllPoints.add(dataPoint);
	}

	/**
	 * return a set with all used classes (= last column) in the data
	 */
	public Set<String> getAllClasses() {
		Set<String> setOfClasses = new HashSet<>();
		for (DataPoint dataPoint : this.listOfAllPoints) {
			setOfClasses.add(dataPoint.getPredefClass());
		}
		return setOfClasses;
	}

	@Override
	public String toString() {
		String returnString = "ListOfAttrNames=" + listOfAttrNames + "\nlistOfAllPoints:\n";
		for (DataPoint dataPoint : listOfAllPoints) {
			returnString += dataPoint.toString() + "\n";
		}
		return returnString;
	}

	public static void main(String[] args) {
		try {
			final String FILE_LOCATION = "./src/resources/GiniExample.txt";
			// final String FILE_LOCATION = "./src/resources/IrisDataTraining.txt";
			DataLoader dataLoaded = new DataLoader(FILE_LOCATION);
			System.out.println(dataLoaded);

			// here an example of how to access concrete values of the data,
			// e.g. for the first line in the data and the first attribute 
			String nameOfFirstAttribute = dataLoaded.getListOfAttrNames().get(0);
			double exampleValue = dataLoaded.getListOfAllPoints().get(0).getValueForAttribute(nameOfFirstAttribute);
			System.out.println("Example Value: " + exampleValue);

			System.out.println("Names of all classes: " + dataLoaded.getAllClasses());


			Gini gini = new Gini(dataLoaded);
			String st = gini.findBestAttributeName();
			System.out.println("GINI -----------------");
			System.out.println("XXX"+st);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
