package data;

import pack.SplitAttribute;

import java.security.KeyStore;
import java.util.*;

public class Gini {

    private final List<DataPoint> myDataPoints;

    private final DataLoader dataLoader;
    private final List<String> attributenames;

    public Gini(DataLoader dataLoader) {
        this.myDataPoints = dataLoader.getListOfAllPoints();
        this.dataLoader = dataLoader;
        this.attributenames =dataLoader.getListOfAttrNames();
    }
    public Double calcGini(SplitAttribute splitAttribute) {
        List<DataPoint> leftSet = splitAttribute.getLeftDataset(myDataPoints);
        List<DataPoint> rightSet = splitAttribute.getRightDataset(myDataPoints);

        double n1 = leftSet.size();
        double n2 = rightSet.size();
        double n = n1 + n2;

        Map<String,Integer> groupOne = new HashMap<>();
        Map<String,Integer> groupTwo = new HashMap<>();

        for(String className : dataLoader.getAllClasses()){
            groupOne.put(className,countClass(className,leftSet));
            groupTwo.put(className,countClass(className,rightSet));
        }
        double g1 = calculateGiniSet(groupOne,n1);
        double g2 = calculateGiniSet(groupTwo,n2);

        return (n1/n) * g1 + (n2/n )* g2;
    }

    public SplitAttribute findBestAttribute() {
        double gini = 1.0;
        SplitAttribute bestSplitAttribute = new SplitAttribute();

        for(String attribute : attributenames){
            Set<Double> attributeValues = new HashSet<>();

            for(DataPoint dp : myDataPoints){
                attributeValues.add(dp.getValueForAttribute(attribute));
            }
            for(Double value : attributeValues) {
                SplitAttribute splitAttribute = new SplitAttribute();
                splitAttribute.setAttrName(attribute);
                splitAttribute.setValue(value);

                double newGini = calcGini(splitAttribute);

                if(newGini < gini) {
                    gini = newGini;
                    bestSplitAttribute = splitAttribute;
                }
            }
        }
        return bestSplitAttribute;
    }

    private int countClass(String className, List<DataPoint> subSet){
        int count = 0;
        for(DataPoint dataPoint: subSet){
            if(dataPoint.getPredefClass().equals(className)) count++;
        }
        return count;
    }

    private double calculateGiniSet(Map<String,Integer> classCountSet, double count){
        double tempOne = 0.0;
        for(Map.Entry<String,Integer> entry: classCountSet.entrySet() ){
            tempOne += Math.pow((double)entry.getValue()/count,2);
        }
        return 1 - tempOne;
    }
}


