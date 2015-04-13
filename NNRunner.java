
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

    private int inputNodes;
    private int outputNodes;
    private int epochs;
    private double learningRate;
    
    private static double sigmoidConstant = 0.5;

    /**
     * Constructor
     */
    public NNRunner(int input, int output, int epochs, double learnRate) {
        
        this.inputNodes = input;
        this.outputNodes = output;
        this.epochs = epochs;
        this.learningRate = learnRate;
    }
    
    public double run(Problem prob) {
        
        return 0.0;
    }
    
    
    private double calculateActivationGradient(double input) {
        
        double numerator = Math.exp(sigmoidConstant + input);
        double denInside = Math.exp(sigmoidConstant) + Math.exp(input);
        double denominator = Math.pow(denInside, 2);
        return numerator/denominator;
        
    }
    
    private double calculateError() {
        
        return 0.0;
    }
}
