
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
    
    private static Perceptron perceptron;

    private int inputNodes;
    private int outputNodes;
    private int epochs;                 
    private double learningRate;      //alpha
    
    private static double sigmoidConstant = 0.5;

    /**
     * Constructor
     */
    public NNRunner(int output, int epochs, double learnRate) {
        
        this.outputNodes = output;
        this.epochs = epochs;
        this.learningRate = learnRate;
    }
    
    
    public void run(Problem prob) {
        
        if (prob.getProblemType() > 0) this.inputNodes = (int) Math.pow(prob.getProblemType(), 2); 
        perceptron = new Perceptron(this.inputNodes, this.outputNodes);
        perceptron.printWeights();
        System.out.println("-------------------------------");
        
        int epochsCount = 0;
        do {
            ListIterator<Clause> lit = prob.getIterator();
            while (lit.hasNext()) {
                double[] target = new double[this.outputNodes];
                double[] output = new double[this.outputNodes];
                
                Clause temp = lit.next();
                if (this.outputNodes == 1) target[0] = temp.getAnswer() / 10.0;
                else if (this.outputNodes == 10) target[temp.getAnswer()] = 1;
                
                for (int oID = 0; oID < this.outputNodes; oID++) {
                    double weightedInputs = 0.0;
                    int iID = 0;
                    
                    for (Integer val : temp.getData()) {
                        weightedInputs += perceptron.getWeightedInput(iID, oID, val);
                        iID++;
                    }
                    
                    output[oID] = calculateActivation(weightedInputs);
                    double error = calculateError(oID, output[oID], target);
                    double activationForWeighted = calculateActivation(weightedInputs);
                    double activateDerive = activationForWeighted * (1-activationForWeighted);
                    
                    iID = 0;
                    for (Integer val : temp.getData()) {
                        double weightUpdate = this.learningRate * val * error * activateDerive;
                        double newWeight = perceptron.getWeight(iID, oID) + weightUpdate;
                        perceptron.setWeight(iID, oID, newWeight);
                        iID++;
                    }
                }
            }
            epochsCount++;
        } while (epochsCount < epochs);
    }
    
    
    // Calculates activation function for inputs. Derivative is g(in) * (1 - g(in))
    private double calculateActivation(double input) {
        
        double denominator = 1 + Math.exp(sigmoidConstant - input);
        return 1.0 / denominator;
    }
    
    
    private double calculateError(int outID, double outVal, double[] target) {

        double ret = 0.0;
        if (target.length == 1) ret = target[0] - outVal;
        else ret = target[outID] - outVal;
        return ret;
    }
}
