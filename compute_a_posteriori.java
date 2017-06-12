import java.util.*;
import java.io.*;


public class compute_a_posteriori
{
    
    public static ArrayList<Double> probabilityOfBag;
    public static int number_of_bags = 5;
    
    public static void main(String[] args)
    {
        String observations = "";
        if(args.length > 1)
        {
            System.out.println("Invalid input: ex. compute_a_posteriori CLLCCLLLCCL or compute_a_posteriori");
            System.exit(0);
        }
        if(args.length == 1)
        {
            observations = args[0];
        }
        PrintWriter output_result = openOutput("results.txt");
        probabilityOfBag = computePosteriorProbability(observations);;
        output_result.println("Observation sequence Q: "+observations);
        output_result.println("Length Of Q: "+observations.length());
        /////////make float length 5
        output_result.printf("P(h1 | Q) = %.5f\n",probabilityOfBag.get(0));
        output_result.printf("P(h2 | Q) = %.5f\n",probabilityOfBag.get(1));
        output_result.printf("P(h3 | Q) = %.5f\n",probabilityOfBag.get(2));
        output_result.printf("P(h4 | Q) = %.5f\n",probabilityOfBag.get(3));
        output_result.printf("P(h5 | Q) = %.5f\n",probabilityOfBag.get(4));
        output_result.println();
        output_result.printf("Probability that the next candy we pick will be C, given Q:  %.5f\n",probabilityOfBag.get(5));
        output_result.printf("Probability that the next candy we pick will be L, given Q:  %.5f\n",probabilityOfBag.get(6));
        output_result.close();
    }
    
    
    public static PrintWriter openOutput (String filename) 
    {
        PrintWriter output = null;
        try
        { output = new PrintWriter(new FileWriter(filename)); }
        catch(FileNotFoundException FNFE)
        {   output = new PrintWriter(new FileWriter(new File(filename))); }
        finally
        { return output; }
    }
    
    public static ArrayList<Double> computePosteriorProbability(String observation)
    {
        ArrayList<Double> results = new ArrayList<Double>();
        results.add(0.10);
        results.add(0.20);
        results.add(0.40);
        results.add(0.20);
        results.add(0.10);
        Double probC;
        Double probL;
        Double newProb = 0.0;
        int observation_count = observation.length();
        String[] splitObservation = observation.split("");
        if(observation_count != 0)
        {
            splitObservation = Arrays.copyOfRange(splitObservation,1,splitObservation.length);
        }
        //System.out.println(observation);
        for(int i = 0; i < observation_count; i++)
        {
            probC = computeProbabilityOfCherry(results);
            probL = computeProbabilityOfLime(results);
            for(int bag = 0; bag < number_of_bags; bag++)
            {
                if(splitObservation[i].equals("C"))
                {
                    switch (bag)
                    {
                        case 0: 
                            newProb = ((1.00)*(results.get(bag)))/probC;
                            break;
                        case 1:
                            newProb = ((0.75)*(results.get(bag)))/probC;
                            break;
                        case 2:
                            newProb = ((0.50)*(results.get(bag)))/probC;
                            break;
                        case 3:
                            newProb = ((0.25)*(results.get(bag)))/probC;
                            break;
                        case 4:
                            newProb = ((0.00)*(results.get(bag)))/probC;
                            break;
                        
                    }
                    results.set(bag,newProb);
                }
                if(splitObservation[i].equals("L"))
                {
                    switch (bag)
                    {
                        case 0: 
                            newProb = ((0.00)*(results.get(bag)))/probL;
                            break;
                        case 1:
                            newProb = ((0.25)*(results.get(bag)))/probL;
                            break;
                        case 2:
                            newProb = ((0.50)*(results.get(bag)))/probL;
                            break;
                        case 3:
                            newProb = ((0.75)*(results.get(bag)))/probL;
                            break;
                        case 4:
                            newProb = ((1.00)*(results.get(bag)))/probL;
                            break;
                        
                    }
                    results.set(bag,newProb);
                }
            }
            //System.out.println(results);

        }
        probC = computeProbabilityOfCherry(results);
        probL = computeProbabilityOfLime(results);
        results.add(probC);
        results.add(probL);
        return results;
    }
    
    public static Double computeProbabilityOfCherry(ArrayList<Double> bagProbabilities)
    {
        Double result = (1.00)*(bagProbabilities.get(0)) + (0.75)*(bagProbabilities.get(1)) + 
        (0.50)*(bagProbabilities.get(2)) + (0.25)*(bagProbabilities.get(3)) + (0.00)*(bagProbabilities.get(4));
        return result;
    }
    
    public static Double computeProbabilityOfLime(ArrayList<Double> bagProbabilities)
    {
        Double result = (0.00)*(bagProbabilities.get(0)) + (0.25)*(bagProbabilities.get(1)) + 
        (0.50)*(bagProbabilities.get(2)) + (0.75)*(bagProbabilities.get(3)) + (1.00)*(bagProbabilities.get(4));
        return result;
    }
    
    
    
    
    
    
    
    
}