package pack;

import data.DataLoader;
import data.Gini;

public class Tree {

    private final Node root;

    public Tree(DataLoader dataLoader){
        this.root = new Node();
        this.root.setDataPoints(dataLoader.getListOfAllPoints());
        this.root.setNodeInformation("Root " + dataLoader.getListOfAllPoints().size() + " Entries");
        addRecursive(dataLoader,root);
    }

    public Node getRoot() {
        return root;
    }

    private void addRecursive(DataLoader presentDataLoader, Node presentNode){
        Node nodeLeft = new Node();
        nodeLeft.setNodeInformation("\u2264");
        Node nodeRight = new Node();
        nodeRight.setNodeInformation(">");
        presentDataLoader.setListOfAllPoints(presentNode.getDataPoints());


        Gini gini = new Gini(presentDataLoader);

        SplitAttribute bestSplitAttribute = gini.findBestAttribute();
        presentNode.setSplitAttribute(bestSplitAttribute);

        nodeLeft.setDataPoints(bestSplitAttribute.getLeftDataset(presentDataLoader.getListOfAllPoints()));
        nodeRight.setDataPoints(bestSplitAttribute.getRightDataset(presentDataLoader.getListOfAllPoints()));

        nodeLeft.addNodeInformation(" "+ bestSplitAttribute.getLeftDataset(presentDataLoader.getListOfAllPoints()).size() + " entries");
        nodeRight.addNodeInformation(" "+ bestSplitAttribute.getRightDataset(presentDataLoader.getListOfAllPoints()).size() + " entries");

        presentNode.setLeft(nodeLeft);
        presentNode.setRight(nodeRight);

        if(bestSplitAttribute.isPureDataset(nodeLeft.getDataPoints())){
            nodeLeft.addNodeInformation(" pure: "+ nodeLeft.getDataPoints().getFirst().getPredefClass());
        }
        else{
            addRecursive(presentDataLoader, presentNode.getLeft());
        }
        if(bestSplitAttribute.isPureDataset(nodeRight.getDataPoints())){
            nodeRight.addNodeInformation(" pure: "+ nodeRight.getDataPoints().getFirst().getPredefClass());
        }
        else{
            addRecursive(presentDataLoader, presentNode.getRight());
        }


    }
}
