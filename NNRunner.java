/**
 * Implements a Perceptron Neural Network
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */

import java.util.*;

public class NNRunner
{   
    //Perceptron constants
    private static final double SIGMOID_CONSTANT = 0.0;
    private static final double WEIGHT_HIGH = 0.3;
    private static final double WEIGHT_OFFSET = WEIGHT_HIGH / 2.0;
    private static final int PRINT_INTERVAL = 5;
    
    //Instance of the perceptron network
    private static Perceptron perceptron;

    //Algorithm specific variables
    private int inputNodes;
    private int outputNodes;
    private int epochs;                 
    private double learningRate;      //alpha
    
    //Keeps track of performance
    private int numCorrect;
    
    /**
     * Constructor
     */
    public NNRunner(int output, int epochs, double learnRate) {
        
        this.outputNodes = output;
        this.epochs = epochs;
        this.learningRate = learnRate;
        this.numCorrect = 0;
    }
    
    
    //Main function for NN. Runs perceptron NN on a given problem
    public void run(Problem prob) {
        
        if (prob.getProblemType() > 0) this.inputNodes = (int) Math.pow(prob.getProblemType(), 2); 
        if (prob.getFileType().equals("Testing")) this.epochs = 1;
        
        perceptron = new Perceptron(this.inputNodes, this.outputNodes, WEIGHT_HIGH, WEIGHT_OFFSET);
        
        System.out.println("\nStarted " + prob.getFileType() + " File. Size: " + prob.getProblemType());
        System.out.println("Input Nodes: " + this.inputNodes);
        System.out.println("Output Nodes: " + this.outputNodes);
        System.out.println("Learning Rate: " + this.learningRate);
        System.out.println("Epochs: " + this.epochs);
        System.out.println("-----------------------------------------");

        int epochsCount = 0;
        do {
            this.numCorrect = 0;
            ListIterator<Clause> lit = prob.getIterator();
            while (lit.hasNext()) {
                double[] target = new double[this.outputNodes];
                double[] output = new double[this.outputNodes];
                
                Clause temp = lit.next();
                if (this.outputNodes == 1) target[0] = temp.getAnswer();
                else if (this.outputNodes == 10) target[temp.getAnswer()] = 1;
                
                for (int oID = 0; oID < this.outputNodes; oID++) {
                    
                    //**********Calculate sum of weighted inputs**********//
                    double weightedInputs = 0.0;
                    int iID = 0;
                    for (Integer val : temp.getData()) {
                        weightedInputs += perceptron.getWeightedInput(iID, oID, val);
                        iID++;
                    }
                    
                    //********Calculate error and activation value*********//
                    output[oID] = calculateActivation(weightedInputs);
                    double error = calculateError(oID, output[oID], target);
                    double activateDerive = output[oID] * (1 - output[oID]);
                    
                    //******************Weight Update*********************//
                    iID = 0;
                    for (Integer val : temp.getData()) {
                        double weightUpdate = this.learningRate * val * error * activateDerive;
                        double newWeight = perceptron.getWeight(iID, oID) + weightUpdate;
                        perceptron.setWeight(iID, oID, newWeight);
                        iID++;
                    }
                }
                calculateResults(output, target);
                perceptron.perceptronTrained();
            }
            epochsCount++;
            if (epochsCount%PRINT_INTERVAL == 0 || epochsCount == 1) printResults(epochsCount, prob);
        } while (epochsCount < epochs);
        System.out.println();
    }
    
    
    // Calculates activation function for inputs. Derivative is g(in) * (1 - g(in))
    private double calculateActivation(double input) {
        double denominator = 1 + Math.exp(SIGMOID_CONSTANT - input);
        return 1.0 / denominator;
    }
    
    
    //Calculate error for a given output node, output value and target value.
    private double calculateError(int outID, double outVal, double[] target) {
        double ret = 0.0;
        if (target.length == 1) ret = (target[0] / 10) - outVal;
        else ret = target[outID] - outVal;
        return ret;
    }
    
    
    //Calculate the results of a given problem clause. Keeps track of total correctly 
    //estimated clauses. 
    public void calculateResults(double[] output, double[] target) {

        if (target.length == 1) {
            int value = (int) (10 * output[0]);
            if ((int)(target[0]) == value) this.numCorrect++;
            
        } else {
            double currHigh = 0.0;
            int highIndex = 0;
            for (int i = 0; i < target.length; i++) {
                if (output[i] > currHigh) {
                    currHigh = output[i];
                    highIndex = i;
                }
            }
            if (target[highIndex] == 1.0) this.numCorrect++;
        }
    }
    
    
    //Prints results at the end of an epoch. 
    public void printResults(int epoch, Problem prob) {
         double percentCorrect = 100 * (double)this.numCorrect / (double)prob.getNumProblems();
         System.out.println(String.format("Epoch: " + epoch + " -> Percent Correct: %.1f%%", percentCorrect));
    }
}