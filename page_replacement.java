/*
 * Keenan Parker
 * 1001024878
 * CSE 3320.002 
 * Assignment 3: Implement and simulate the four page replacement stategies:
 *  FIFO, LRU, LFU, and Optimal. The program will read page references
 *  from a data file supplied via command line. First line of the data
 *  will represent the page table size while the following line will contain
 *  a page number. Valid page numbers are from 0 to 255. -1 shows empty spot in page table.
 *
 */
import java.util.*;
import java.io.*;


public class page_replacement
{
    public static ArrayList<String> page_reference_string;
    public static int page_table_size;
    
    public static void main(String[] args) throws IOException  
    {
        if( args.length != 1)
        {
            System.out.println("Input Format Error: java page_replacement file.txt");
        }
        String filename = args[0];
        BufferedReader fileInput = readFile(filename);
        page_reference_string = storeInput(fileInput);
        System.out.println();
        fifo();
        System.out.println();
        lru();
        System.out.println();
        lfu();
        System.out.println();
        optimal();
        fileInput.close();
    }
   
    /*
     * Function: readFile
     * Parameter(s):String filename - String containging the name of the input file
     * Returns: BufferReader result which is used to read in input
     * Description: Get a filename and opens the file to be read.
    */
    public static BufferedReader readFile (String filename)
    {
        BufferedReader buffer = null;
        try
        {
            buffer = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException FNFE)
        {    
          buffer = null;
        }
        finally
        {
           return buffer;
        }
    }
    
    /*
     * Function: storeInput
     * Parameter(s): BufferedReader buffer - file opened in the funciton above (readFile)
     * Returns: An ArrayList of Strings that contains the reference string
     * Description: Takes a file with first number being the bag table size with every
     *              following line containg a page reference and puts it into a string
    */
    public static ArrayList<String> storeInput (BufferedReader buffer) throws IOException 
    {
        ArrayList<String> result = new ArrayList<String>();
        String[] input;
        String fileLine;
        int checkIfFirstInt = 0;
        while( (fileLine = buffer.readLine()) != null)
        {
             input = fileLine.split("\n");
             if(checkIfFirstInt == 0)
             {
                 checkIfFirstInt++;
                 page_table_size = Integer.valueOf(input[0]);
                 continue;
             }
             result.add(input[0]);
        }
        return result;
    }
    
    /*
     * Function: fifo
     * Parameter(s): N/A
     * Returns: N/A
     * Description: Takes the reference string and pagetable and shows how FIFO
     *              would handle the page replacement
    */
    public static void fifo()
    {
        ArrayList<String> page_table = new ArrayList<String>(Collections.nCopies(page_table_size,"-1"));
        int page_faults = 0;
        String page;
        int pageTableSlotLastReferenced = 0;
        for(int i = 0; i < page_reference_string.size(); i++)
        {
            page = page_reference_string.get(i);
            if(page_table.contains(page))
            {
                printPageTable(page_table);
                continue;
            }
  
            page_table.set(pageTableSlotLastReferenced,page_reference_string.get(i));
            pageTableSlotLastReferenced++;
            if(pageTableSlotLastReferenced == page_table_size)
            {
                pageTableSlotLastReferenced = 0;
            }
            page_faults++;
            printPageTable(page_table);
        }
       
        System.out.println("Page fault of FIFO: "+page_faults);
        
    }
    
    /*
     * Function: lru
     * Parameter(s): N/A
     * Returns: N/A
     * Description: Takes the reference string and pagetable and shows how LRU
     *              would handle the page replacement
    */
    public static void lru()
    {
        ArrayList<String> page_table = new ArrayList<String>(Collections.nCopies(page_table_size,"-1"));
        int page_faults = 0;
        ArrayList<Integer> iterations_since_last_use = new ArrayList<Integer>(Collections.nCopies(page_table_size,0));
        String page;
        int index = 0;
        int leastRecentlyUsed;
        int pageTableSlotLastReferenced = 0;
        for(int i = 0; i < page_reference_string.size(); i++)
        {
            leastRecentlyUsed = -1;
            page = page_reference_string.get(i);
            if(page_table.contains(page))
            {
                index = page_table.indexOf(page);
                iterations_since_last_use.set(index,0);
                printPageTable(page_table);
                for(int k= 0; k < iterations_since_last_use.size(); k++)
                {
                    iterations_since_last_use.set(k,iterations_since_last_use.get(k)+1);
                }
                continue;
            }
            
            for(int k= 0; k < iterations_since_last_use.size(); k++)
            {
                if(iterations_since_last_use.get(k) > leastRecentlyUsed)
                {
                    index = k;
                    leastRecentlyUsed = iterations_since_last_use.get(k);
                }
            }
            //System.out.println(iterations_since_last_use);
            if(page_table.contains("-1"))
            {
                page_table.set(pageTableSlotLastReferenced,page_reference_string.get(i));
                iterations_since_last_use.set(pageTableSlotLastReferenced,0);
            }
            else
            {
                page_table.set(index,page_reference_string.get(i));
                iterations_since_last_use.set(index,0);
            }
            
            for(int k= 0; k < iterations_since_last_use.size(); k++)
            {
                iterations_since_last_use.set(k,iterations_since_last_use.get(k)+1);
            }
            printPageTable(page_table);
            page_faults++;
            pageTableSlotLastReferenced++;
        }
        
        System.out.println("Page fault of LRU: "+page_faults);
    }
    
    /*
     * Function: lfu
     * Parameter(s): N/A
     * Returns: N/A
     * Description: Takes the reference string and pagetable and shows how LFU
     *              would handle the page replacement
    */
    public static void lfu()
    {
        //count refernces for each page
        ArrayList<String> page_table = new ArrayList<String>(Collections.nCopies(page_table_size,"-1"));
        int[] page_reference_counts = new int[page_table.size()];
        Arrays.fill(page_reference_counts,0);
        int page_faults = 0;
        int page_table_slot_last_used = 0;
        String page;
        String leastFrequentlyUsed = "";
        int replace_slot = 0;
        int count;
        for(int i = 0; i < page_reference_string.size(); i++)
        {
            page = page_reference_string.get(i);
            if(page_table.contains(page))
            {
                printPageTable(page_table);
                page_reference_counts[ page_table.indexOf(page) ]++;
                continue;
            }
            if(page_table.contains("-1"))
            {
                page_table.set(page_table_slot_last_used,page_reference_string.get(i));
                page_table_slot_last_used++;  
                printPageTable(page_table);
                page_faults++;
                page_reference_counts[ page_table.indexOf(page) ] = 1;
                continue;
            }
           // System.out.println(Arrays.toString(page_reference_counts));
            //finds index of leastFrequently used page currently in the table
            count = Integer.MAX_VALUE;
            for(int p = 0; p < page_reference_counts.length; p++)
            {
                if(page_reference_counts[p] < count)
                {
                    
                    count = page_reference_counts[p];
                    leastFrequentlyUsed = page_table.get(p);
                }
            }
            replace_slot = page_table.indexOf(leastFrequentlyUsed);
            
           // System.out.println(page+" "+replace_slot+" page_Table = "+page_table+" leastFrequentlyUsed = 
           //"+leastFrequentlyUsed+" page counts = "+Arrays.toString(page_reference_counts));
            page_table.set(replace_slot,page_reference_string.get(i));
            page_faults++;
            page_reference_counts[ page_table.indexOf(page) ] = 1;
            printPageTable(page_table);
        }
        
        System.out.println("Page fault of LFU: "+page_faults);
    }
    
    /*
     * Function: optimal
     * Parameter(s): N/A
     * Returns: N/A
     * Description: Takes the reference string and pagetable and shows how Optimal
     *              would handle the page replacement
    */
    public static void optimal()
    {
        ArrayList<String> page_table = new ArrayList<String>(Collections.nCopies(page_table_size,"-1"));
        int page_faults = 0;
        int page_table_slot_last_used = 0;
        int count;
        int page_table_index;
        int optimalReplace;
        int lengthAway;
        String page;
        //int will corilate to the index of the page in table and distance till next occurance in page_refercence_string.
        int[] optimal_check = new int[page_table_size];
        for(int i = 0; i < page_reference_string.size(); i++)
        {
            page = page_reference_string.get(i);
            if(page_table.contains(page))
            {
                printPageTable(page_table);
                continue;
            }
            if(page_table.contains("-1"))
            {
                page_table.set(page_table_slot_last_used,page_reference_string.get(i));
                page_table_slot_last_used++;
                printPageTable(page_table);
                page_faults++;
                continue;
            }
            count = 0;
            lengthAway = -2;
            optimalReplace = 0;
            Arrays.fill(optimal_check, -1);
            for(int x = i; x < page_reference_string.size(); x++)
            {
                if(page_table.contains(page_reference_string.get(i)))
                {
                    page_table_index = page_table.indexOf(page_reference_string.get(i));
                    if(optimal_check[page_table_index] == -1)
                    {
                        optimal_check[page_table_index] = count;
                    }
                }
                count++;
            }
            for(int y = 0; y < optimal_check.length; y++)
            {
                if(optimal_check[y] > lengthAway)
                {
                    lengthAway = optimal_check[y];
                    optimalReplace = y;
                }
            }
            page_table.set(optimalReplace,page_reference_string.get(i));
            printPageTable(page_table);
            page_faults++;
            
        }
        
        System.out.println("Page fault of Optimal: "+page_faults);
    }
    
    /*
     * Function: printPageTable
     * Parameter(s): ArrayList<String> page_table - An arraylist of strings
     *              that represent a page table
     * Returns: N/A
     * Description: Prints the page table that its given in a formatted way.
    */
    public static void printPageTable(ArrayList<String> page_table)
    {
        for(int i = 0; i < page_table.size(); i++)
        {
            if(i == 0)
            {
                System.out.printf("%s",page_table.get(i));
            }
            else
            {
                if(page_table.get(i).equals("-1"))
                {
                 System.out.printf(" %s",page_table.get(i));
                }
                else
                {
                    System.out.printf("%3s",page_table.get(i));
                }
            }
            
        }
        System.out.println();
    }
    
    
}
