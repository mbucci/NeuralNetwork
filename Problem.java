
/**
 * Sets up a NN problem
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */

import java.util.*;
import java.util.regex.*;
import java.io.*;
@SuppressWarnings("unchecked")

public class Problem
{
    private static final int ANSWER_LINE_LEN = 2;
    
    private int problemType;
    private List<Clause> problem;
    
    public Problem(File f) {
        
        this.problem = new ArrayList<Clause>();
        this.problemType = (f.getName().contains("8x8")) ? 0 : 1;
        readFile(f);
    }
    
    public void readFile(File f) {
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            String pattern = "([a-z,A-Z]).*";
            ArrayList<Integer> data = new ArrayList<Integer>();
            
            while ((line = reader.readLine()) != null) {
                //Ignore any alpha lines
                if (Pattern.matches(pattern, line)) continue; 
                
                if (this.problemType == 1) {
                    if (line.length() > ANSWER_LINE_LEN) {
                        StringTokenizer tokens = new StringTokenizer(line, "01", true);
                        while (tokens.hasMoreTokens()) data.add(Integer.parseInt(tokens.nextToken()));
                        
                    } else if (line.length() == ANSWER_LINE_LEN) {
                        String answer = line.substring(1, ANSWER_LINE_LEN);
                        Clause newClause = new Clause(Integer.parseInt(answer), (List) data.clone());

                        this.problem.add(newClause);
                        data.clear();
                    }
                    
                } else {
                    StringTokenizer tokens = new StringTokenizer(line.substring(0,line.length()-2), ",");
                    String answer = line.substring(line.length()-1, line.length());
                    while (tokens.hasMoreTokens()) data.add(Integer.parseInt(tokens.nextToken()));
                    
                    Clause newClause = new Clause(Integer.parseInt(answer), (List) data.clone());
                    this.problem.add(newClause);
                    data.clear();
                }
            }
            reader.close();
            
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", f);
            e.printStackTrace();
        }
    }
}
