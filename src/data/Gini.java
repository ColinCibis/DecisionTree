package data;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gini {
    private List<DataPoint> dataPoints;

    private final List<DataPoint> myDataPoints;

    private final DataLoader dataLoader;
    private final Map<String, Map<String, Set<Double>>> classAttributeValuesMap;
    private final Map<String, Set<Double>> attributeValuesMap;
    private final List<String> attributenames;

    public Gini(DataLoader dataLoader) {
        this.myDataPoints = dataLoader.getListOfAllPoints();
        this.dataLoader = dataLoader;
        this.classAttributeValuesMap = new HashMap<>();
        this.attributeValuesMap = new HashMap<>();
        this.attributenames = myDataPoints.getFirst().getAttributeNames();
    }

    public void generateMap() {
/*
        var classNames = dataLoader.getAllClasses();

        for (String className : classNames) {
            Map<String, Set<Double>> attributeValuesMap = new HashMap<>();

            for (String attribute : attributenames) {
                Set<Double> doubleSet = new HashSet<>();
                for (DataPoint dataPoint : myDataPoints) {
                    if (dataPoint.getPredefClass().equals(className)) {
                        doubleSet.add(dataPoint.getValueForAttribute(attribute));

                    }
                    attributeValuesMap.put(attribute, doubleSet);
                }
                classAttributeValuesMap.put(className, attributeValuesMap);
            }
        }*/
    }

    public void findBestAttributeName() {
/*
        Map<String, Double> bestAttribute = new HashMap<>();
        Map<String, Map<String, Set<Double>>> splitDataG1 = new HashMap<>();
        Map<String, Map<String, Set<Double>>> splitDataG2 = new HashMap<>();
        Double gini = Double.MAX_VALUE;

        generateMap();

        System.out.println("XXXXX"+classAttributeValuesMap);

        for (String attribute : attributenames) {

            List<String> currentAttribute = new ArrayList<>();
            currentAttribute.add(attribute);

            List<String> excludedAttributes = new ArrayList<>();


            List<Map<String,Map<String,Set<Double>>>> myList;

            for(String className : classAttributeValuesMap.keySet()) {

            }
            for (String name : attributenames) {
                    if(!name.equals(currentAttribute.get(0))){
                        excludedAttributes.add(name);
                }
            }

            for(String className : classAttributeValuesMap.keySet()) {
                splitDataG1 = excludedAttributes.stream()
                    .filter(classAttributeValuesMap.get(className)::containsKey)
                    .collect(Collectors.toMap(Function.identity(), classAttributeValuesMap::get));
            }


            calcGini(splitDataG1, splitDataG2);
            System.out.println("Normal Map:" + splitDataG2 + "Split Map: " + splitDataG1);
        }*/
        String bestAttribute;
        double n1;
        double n2;
        double n;
        double g1;
        double g2;


        for(String attributeName: attributenames){
            List<DataPoint> greaterThen = new ArrayList<>();
            List<DataPoint> smallerThen = new ArrayList<>();
            for (DataPoint dp : myDataPoints){
                double value = dp.getValueForAttribute(attributeName);
                for(DataPoint dpI: myDataPoints){
                    if(dpI.getValueForAttribute(attributeName)<=value) smallerThen.add(dpI);
                    if(dpI.getValueForAttribute(attributeName)>value) greaterThen.add(dpI);
                }
            }
            n1 = smallerThen.size();
            n2 = greaterThen.size();
            n = n1 + n2;
            for(int i=0;i<smallerThen.size();i++){

            }
        }
    }

    public double calcGini() {
        return 0.0;
    }
}


