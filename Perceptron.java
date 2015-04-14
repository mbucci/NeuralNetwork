
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
 
   private static final double WEIGHT_HIGH = 2.0;
   private static final int WEIGHT_OFFSET = 1;
    
   private static Map<Integer, List<Edge>> inputs;
   
   private static int timesTrained;

    /**
     * Constructor. The map's key is the ID of the input node, the value is the 
     * list edges from that node. An edge's index number in the list corresponds to 
     * the output ID.
     */
    public Perceptron(int numInput, int numOutput) {
        
        if (this.inputs != null) return;
        this.inputs = new HashMap<Integer, List<Edge>>();
        this.timesTrained = 0;
        
        for (int i = 0; i < numInput; i++) {
            
            List<Edge> edgeList = new ArrayList<Edge>();
            for (int j = 0; j < numOutput; j++) {
                double value = rand.nextDouble() * WEIGHT_HIGH;
                value -= WEIGHT_OFFSET;
                Edge newEdge = new Edge(i, j, value);
                edgeList.add(newEdge);
            }
            this.inputs.put(i, edgeList);
        }
    }
    
    public void perceptronTrained() { this.timesTrained++; }
    
    public int getTimesTrained() { return this.timesTrained; }
    
    public double getWeightedInput(int inID, int outID, double inVal) {
        return getWeight(inID, outID) * inVal;
    }
    
    public double getWeight(int inID, int outID) {
        Edge temp = this.inputs.get(inID).get(outID);;
        return temp.getWeight();
    }
    
    public void setWeight(int inID, int outID, double newWeight) {
        this.inputs.get(inID).get(outID).setWeight(newWeight);
    }
}
