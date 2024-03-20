package data;

import data.DataPoint;
import pack.SplitAttribute;

import java.util.*;

public class Gini {
    private List<DataPoint> dataPoints;

    private List <DataPoint> myDataPoints;
    private DataLoader dataLoader;
    private Map<String, Map<String, Set<Double>>> classAttributeValuesMap;


    public Gini(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
        this.myDataPoints = dataLoader.getListOfAllPoints();
        this.classAttributeValuesMap = new HashMap<>();
    }

    public Map<String,Map<String,Set<Double>>> generateMap(){

        var attributenames = myDataPoints.get(0).getAttributeNames();
        var classNames = dataLoader.getAllClasses();

        for (String className : classNames) {
            Map<String,Set<Double>> attributeValuesMap = new HashMap<>();

            for (String attribute : attributenames) {
                Set<Double> doubleSet = new HashSet<>();

                for (DataPoint dataPoint : myDataPoints) {
                    if(dataPoint.getPredefClass().equals(className)) {
                        doubleSet.add(dataPoint.getValueForAttribute(attribute));
                    }
                }
                attributeValuesMap.put(attribute, doubleSet);
            }
            classAttributeValuesMap.put(className,attributeValuesMap);
        }
        return  classAttributeValuesMap;
    }

    public String findBestAttributeName(){

        return "TEST: " + classAttributeValuesMap;
    }

    private double giniBerechnung(double value){
        return 0.0;
    }

}
