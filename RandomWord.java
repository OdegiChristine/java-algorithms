import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main (String[] args){
       int count = 1;
       String champion = StdIn.readString();

       while (!StdIn.isEmpty()) {
        String word = StdIn.readString();
        count++;
        
        double p = 1.0/count;
        if (StdRandom.bernoulli(p) == true) {
            champion = word;
        }
       }

       if (champion != null) {
        System.out.println(champion);
       } else {
        System.out.println("No words");
       }
    }
}
