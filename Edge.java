/**
 * Perceptron Edge
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */
public class Edge
{
    private int inputNode, outputNode;
    private double weight;

    /**
     * Constructor for objects of class Edge
     */
    public Edge(int in, int out, double weight) {
        this.inputNode = in;
        this.outputNode = out;
        this.weight = weight;
    }
    
    public int getInput() { return this.inputNode; }
    public int getOutput() { return this.outputNode; }
    public double getWeight() { return this.weight; }
    public void setWeight(double weight) { this.weight = weight; }
}
