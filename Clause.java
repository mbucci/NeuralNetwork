/**
 * Clause class for a Problem File
 * 
 * Max Bucci, Nikki Morin, Megan Maher
 * Created: 4/13/15
 * Last Modified: 4/13/15
 * 
 */

import java.util.*;

public class Clause
{
    private int answer;            //The clauses answer
    private List<Integer> data;    //The clause data

    /**
     * Constructor for objects of class Clause
     */
    public Clause(int ans, List<Integer> data) {
        this.answer = ans;
        this.data = data;
    }

    public int getAnswer() { return this.answer; }
    public List<Integer> getData() { return this.data; }
    
    public void printClause() {
        System.out.println("Answer: "+this.answer);
        for (Integer e : this.data) System.out.print(e);
        System.out.println();
    }
}
