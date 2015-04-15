/**
 * Implements a Perceptron Network
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */

import java.util.*;
public class Perceptron
{
   private static Random rand = new Random();
   
   private static Map<Integer, List<Edge>> inputs;    //Perceptron data structure
   private static int timesTrained;                   //times trained
   private static int biasNodeID;                     //ID of the bias node

   
    /**
     * Constructor. The map's key is the ID of the input node, the value is the 
     * list edges from that node. An edge's index number in the list corresponds to 
     * the output ID.
     */
    public Perceptron(int numInput, int numOutput, double wHigh, double wOff) {
        
        //Don't reinitialize Perceptron if it already exists
        if (this.inputs != null) return;
        this.inputs = new HashMap<Integer, List<Edge>>();
        this.timesTrained = 0;
        this.biasNodeID = rand.nextInt(numInput);
        
        for (int i = 0; i < numInput; i++) {
            
            List<Edge> edgeList = new ArrayList<Edge>();
            for (int j = 0; j < numOutput; j++) {
                double value = rand.nextDouble() * wHigh;
                value -= wOff;
                Edge newEdge = new Edge(i, j, value);
                edgeList.add(newEdge);
            }
            this.inputs.put(i, edgeList);
        }
    }

   
    //Given an input node ID, output node ID and input value, calculates the weighted input for
    //that edge
    public double getWeightedInput(int inID, int outID, double inVal) {
        // bias node always has an input value of 1
        if (inID == this.biasNodeID) inVal = 1.0;
        return getWeight(inID, outID) * inVal;
    }
    
    public int getTimesTrained() { return this.timesTrained; }
    public void perceptronTrained() { this.timesTrained++; }
    
    public double getWeight(int inID, int outID) {
        return this.inputs.get(inID).get(outID).getWeight();
    }
    public void setWeight(int inID, int outID, double newWeight) {
        this.inputs.get(inID).get(outID).setWeight(newWeight);
    }
}
