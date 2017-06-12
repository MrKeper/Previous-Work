import java.io.*;
import java.util.*;
class writeWumpusRules
{
    public static void main(String[] args)
    {
        PrintWriter wumpus_rules = openOutput("wumpus_rules.txt");
        wumpus_rules.println("# Greetings");
        writeRule1(wumpus_rules);
        writeRule2(wumpus_rules);
        writeRule3(wumpus_rules);
        writeRule4(wumpus_rules);
        writeRule5(wumpus_rules);
        writeRule6(wumpus_rules);
        writeRule7(wumpus_rules);
        writeRule8(wumpus_rules);
        wumpus_rules.close();
    }
    
    
    //If there is a monster at square (i,j), there is stench at all adjacent squares.
    public static void writeRule1(PrintWriter rules)
    {
        rules.println("#Rules 1: If there is a monster at square (i,j), there is stench at all adjacent squares.");
        for(int i = 1; i <= 4; i++)
        {
            for (int k = 1; k <= 4; k++)
            {   
               if ( ((i == 1 && k == 1) || ( (i == 1 && k == 2)) || ( (i == 2 && k == 1)) || ( (i == 2 && k == 2) )) )
                {
                    continue;
                }
                if( (i+1 > 4) && (k+1 > 4))
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i-1)+"_"+k+" S_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4) && k-1<=0)
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+i+"_"+(k+1)+" S_"+(i-1)+"_"+k+"))");
                }
                else  if((k+1 > 4) && i-1<=0)
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i+1)+"_"+k+" S_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4))
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+i+"_"+(k+1)+" S_"+(i-1)+"_"+k+" S_"+i+"_"+(k-1)+"))");
                }
                else  if((k+1 > 4))
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i+1)+"_"+k+" S_"+(i-1)+"_"+k+" S_"+i+"_"+(k-1)+"))");
                }
                else  if( (i-1 <= 0))
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i+1)+"_"+k+" S_"+i+"_"+(k+1)+" S_"+i+"_"+(k-1)+"))");
                }
                else  if( (k-1 <= 0))
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i+1)+"_"+k+" S_"+i+"_"+(k+1)+" S_"+(i-1)+"_"+k+"))");
                }
                else
                {
                    rules.println("(if M_"+i+"_"+k+" (and S_"+(i+1)+"_"+k+" S_"+i+"_"+(k+1)+" S_"+(i-1)+"_"+k+" S_"+i+"_"+(k-1)+"))");
                }
            }
        }
    }
    //If there is stench at square (i,j), there is a monster at one of the adjacent squares.
    public static void writeRule2(PrintWriter rules)
    {
        rules.println("#Rules 2: If there is stench at square (i,j), there is a monster at one of the adjacent squares.");
        for(int i = 1; i <= 4; i++)
        {
            for (int k = 1; k <= 4; k++)
            {   
                if( (i == 1 && k == 1) )
                {
                    continue;
                }
                if( (i+1 > 4) && (k+1 > 4))
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i-1)+"_"+k+" M_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4) && k-1<=0)
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+i+"_"+(k+1)+" M_"+(i-1)+"_"+k+"))");
                }
                else  if((k+1 > 4) && i-1<=0)
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i+1)+"_"+k+" M_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4))
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+i+"_"+(k+1)+" M_"+(i-1)+"_"+k+" M_"+i+"_"+(k-1)+"))");
                }
                else  if((k+1 > 4))
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i+1)+"_"+k+" M_"+(i-1)+"_"+k+" M_"+i+"_"+(k-1)+"))");
                }
                else  if( (i-1 <= 0))
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i+1)+"_"+k+" M_"+i+"_"+(k+1)+" M_"+i+"_"+(k-1)+"))");
                }
                else  if( (k-1 <= 0))
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i+1)+"_"+k+" M_"+i+"_"+(k+1)+" M_"+(i-1)+"_"+k+"))");
                }
                else
                {
                    rules.println("(if S_"+i+"_"+k+" (or M_"+(i+1)+"_"+k+" M_"+i+"_"+(k+1)+" M_"+(i-1)+"_"+k+" M_"+i+"_"+(k-1)+"))");
                }
            }
        }
    }
    //If there is a pit at square (i,j), there is breeze at all adjacent squares.
    public static void writeRule3(PrintWriter rules)
    {
        rules.println("#Rules 3: If there is a pit at square (i,j), there is breeze at all adjacent squares.");
         for(int i = 1; i <= 4; i++)
        {
            for (int k = 1; k <= 4; k++)
            {   
                if ( ((i == 1 && k == 1) || ( (i == 1 && k == 2)) || ( (i == 2 && k == 1)) || ( (i == 2 && k == 2) )) )
                {
                    continue;
                }
                if( (i+1 > 4) && (k+1 > 4))
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i-1)+"_"+k+" B_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4) && k-1<=0)
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+i+"_"+(k+1)+" B_"+(i-1)+"_"+k+"))");
                }
                else  if((k+1 > 4) && i-1<=0)
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i+1)+"_"+k+" B_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4))
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+i+"_"+(k+1)+" B_"+(i-1)+"_"+k+" B_"+i+"_"+(k-1)+"))");
                }
                else  if((k+1 > 4))
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i+1)+"_"+k+" B_"+(i-1)+"_"+k+" B_"+i+"_"+(k-1)+"))");
                }
                else  if( (i-1 <= 0))
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i+1)+"_"+k+" B_"+i+"_"+(k+1)+" B_"+i+"_"+(k-1)+"))");
                }
                else  if( (k-1 <= 0))
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i+1)+"_"+k+" B_"+i+"_"+(k+1)+" B_"+(i-1)+"_"+k+"))");
                }
                else
                {
                    rules.println("(if P_"+i+"_"+k+" (and B_"+(i+1)+"_"+k+" B_"+i+"_"+(k+1)+" B_"+(i-1)+"_"+k+" B_"+i+"_"+(k-1)+"))");
                }
            }
        }
    }
    //If there is breeze at square (i,j), there is a pit at one or more of the adjacent squares.
    public static void writeRule4(PrintWriter rules)
    {
        rules.println("#Rules 4: If there is breeze at square (i,j), there is a pit at one or more of the adjacent squares.");
        for(int i = 1; i <= 4; i++)
        {
            for (int k = 1; k <= 4; k++)
            {   
                if( (i == 1 && k == 1) )
                {
                    continue;
                }
                if( (i+1 > 4) && (k+1 > 4))
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i-1)+"_"+k+" P_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4) && k-1<=0)
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+i+"_"+(k+1)+" P_"+(i-1)+"_"+k+"))");
                }
                else  if((k+1 > 4) && i-1<=0)
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i+1)+"_"+k+" P_"+i+"_"+(k-1)+"))");
                }
                else  if( (i+1 > 4))
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+i+"_"+(k+1)+" P_"+(i-1)+"_"+k+" P_"+i+"_"+(k-1)+"))");
                }
                else  if((k+1 > 4))
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i+1)+"_"+k+" P_"+(i-1)+"_"+k+" P_"+i+"_"+(k-1)+"))");
                }
                else  if( (i-1 <= 0))
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i+1)+"_"+k+" P_"+i+"_"+(k+1)+" P_"+i+"_"+(k-1)+"))");
                }
                else  if( (k-1 <= 0))
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i+1)+"_"+k+" P_"+i+"_"+(k+1)+" P_"+(i-1)+"_"+k+"))");
                }
                else
                {
                    rules.println("(if B_"+i+"_"+k+" (or P_"+(i+1)+"_"+k+" P_"+i+"_"+(k+1)+" P_"+(i-1)+"_"+k+" P_"+i+"_"+(k-1)+"))");
                }
            }
        }
        
    }
    //There is one and only one monster (no more, no fewer).
    public static void writeRule5(PrintWriter rules)
    {
        rules.println("#Rules 5: There is one and only one monster (no more, no fewer).");
        ArrayList <String> possibleMonster = new ArrayList<String>();
        for(int a = 1; a <= 4; a++)
        {
            for(int b = 1; b <= 4; b++)
            {
                if( (a == 1 && b == 1) || ( (a == 1 && b == 2)) || ( (a == 2 && b == 1)) || ( (a == 2 && b == 2) ))
                {
                    continue;
                }
                possibleMonster.add("M_"+a+"_"+b);
            }
        }
        String rest = "";
        for(int i = 1; i <= 4; i++)
        {
            for (int k = 1; k <= 4; k++)
            {   rest = "";
                if ( ((i == 1 && k == 1) || ( (i == 1 && k == 2)) || ( (i == 2 && k == 1)) || ( (i == 2 && k == 2) )) )
                {
                    continue;
                }
                for(int x = 0; x < possibleMonster.size(); x++)
                {
                        if(x == 0 && !possibleMonster.get(x).equals("M_"+i+"_"+k))
                        {
                            rest = rest + possibleMonster.get(x);
                        }
                        else 
                        {
                            if (!possibleMonster.get(x).equals("M_"+i+"_"+k))
                            {
                                if(rest.equals(""))
                                {
                                     rest = rest +possibleMonster.get(x);
                                }
                                else
                                {
                                    rest = rest + " "+possibleMonster.get(x);
                                }
                            }
                        }
                }
                rules.println("(if M_"+i+"_"+k+" (not (or "+rest+")))");
                

            }
            
        }
    }
    //Squares (1,1), (1,2), (2,1), (2,2) have no monsters and no pits.
    public static void writeRule6(PrintWriter rules)
    {
        rules.println("#Rules 6: Squares (1,1), (1,2), (2,1), (2,2) have no monsters and no pits.");
        for(int i = 1; i <= 2; i++)
        {
            for(int k = 1; k <= 2; k++)
            {
                rules.println("(not P_"+i+"_"+k+")");
                rules.println("(not M_"+i+"_"+k+")");
            }
        }
    }
    //The number of pits can be between 1 and 11.
    public static void writeRule7(PrintWriter rules)
    {
        rules.println("#Rules 7: The number of pits can be between 1 and 11.");
        rules.println("(and (or P_1_3 P_1_4 P_2_3 P_2_4 P_3_1 P_3_2 P_3_3 P_3_4 P_4_1 P_4_2 P_4_3 P_4_4) (not (and P_1_3 P_1_4 P_2_3 P_2_4 P_3_1 P_3_2 P_3_3 P_3_4 P_4_1 P_4_2 P_4_3 P_4_4)))");
    }
    public static void writeRule8(PrintWriter rules)
    {
        rules.println("#Rules 8: We don't care about gold, glitter, and arrows, there will be no information about them in the knowledge base, and no reference to them in the statement.");
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
}
