
/**
 * Runs a basic Perceptron Neural Network
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */

import java.util.*;
import java.io.*;

public class NeuralNetwork
{

    private static NNRunner runner;
    private static Problem trainProb;
    private static Problem testProb;
    
    private static File trainFile;
    private static File testFile;
    
    private static int inputNodes;
    private static int outputNodes;
    private static int epochs;
    private static double learningRate;
    
    public static void main (String[] args) {
        
        assert(args.length == 6);
        
        inputNodes = Integer.parseInt(args[0]);
        outputNodes = Integer.parseInt(args[1]);
        epochs = Integer.parseInt(args[2]);
        learningRate = Double.parseDouble(args[3]);
        trainFile = new File(args[4]);
        testFile = new File(args[5]);
        
        assert(outputNodes == 1 || outputNodes == 10);
        
        NNRunner runner = new NNRunner(inputNodes, outputNodes, epochs, learningRate);
        trainProb = new Problem(trainFile);
        testProb = new Problem(testFile);
        
        runner.run(trainProb);
        runner.run(testProb);
    }
}
