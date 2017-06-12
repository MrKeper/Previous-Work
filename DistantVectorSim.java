import java.util.*;
import java.io.*;
public class DistantVectorSim
{
    public ArrayList<router> nodes;
    public boolean stable = false;
    public static int checkStableTable;
    public static int checkStablePath;
    public static ArrayList<Integer> router_ids;
    public static final int INFINITY = 16;
    
    /*
     * Function Name: readFile  
     * Parameter(s): a string containing the name of the file to be read
     * Return(s): a bufferedreader to be read in buildNodes
     * Description: opens the file with the name filename and make a bufferedreader for it.
    */
    public BufferedReader readFile (String filename)
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
     * Function Name: builldNodes  
     * Parameter(s): the bufferedreader made in readFile
     * Return(s): an Arraylist of routers that contain the intial state of the sim
     * Description: loops through file reading input in the form A B C. All should 
     *              be ints. A and B are ids for routers and C is the cost of travel
     *              between them.The while loop read through the file. The first forloop in the while goes through the list of routrs added.
     *              if nothing was added in the loop it goes to add the router to the list since
     *              it was not before. The forloop afterwords will add any unlisted connections as infinite
     *              and make 'dumb' routers for each router to keep track of the rputer's knowledge of the network.
     *
    */
    public void buildNodes(BufferedReader buffer) throws IOException
    {
        nodes = new ArrayList<router>();
        router_ids = new ArrayList<Integer>();
        String[] input;
        String fileLine;
        int check_zero = 0;
        int check_one = 0;
        while( (fileLine = buffer.readLine()) != null)
        {
            check_zero = 0;
            check_one = 0;
            input = fileLine.split(" ");
            for(int i = 0; i < nodes.size(); i++)
            {
                router r = nodes.get(i);
                if(r.router_id == Integer.parseInt(input[0]))
                {
                    nodes.get(i).addNeighbohr(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    check_zero = 1;
                }
                if(r.router_id == Integer.parseInt(input[1]))
                {
                    nodes.get(i).addNeighbohr(Integer.parseInt(input[0]), Integer.parseInt(input[2]));
                    check_one = 1;
                }
            }
            if(check_zero == 0)
            {
                router r1 = new router(Integer.parseInt(input[0]),Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                 r1.addNeighbohr(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                 nodes.add(r1);
                 router_ids.add(Integer.parseInt(input[0]));
            }
                if(check_one == 0)
            {   
                router r2 = new router(Integer.parseInt(input[1]),Integer.parseInt(input[0]),Integer.parseInt(input[2]));
                r2.addNeighbohr(Integer.parseInt(input[0]), Integer.parseInt(input[2]));
                nodes.add(r2);            
                router_ids.add(Integer.parseInt(input[1]));
            }
        
        }
        //build neighbohr data
        for(int i = 0; i < nodes.size(); i++)
        {
            nodes.get(i).addNeighbohr(nodes.get(i).router_id, 0);
            for(int j = 0; j < router_ids.size(); j++)
            {
                if(nodes.get(i).neighbohrs.get(router_ids.get(j)) == null)
                {
                    nodes.get(i).addNeighbohr(router_ids.get(j), INFINITY);
                }
                if(router_ids.get(j) != nodes.get(i).router_id)
                {
                    router r = new router(router_ids.get(j));
                    for(int k = 0; k < router_ids.size(); k++)
                    {
                        r.addNeighbohr(router_ids.get(k),INFINITY);
                    }
                    nodes.get(i).neighbohr_data.add(r);
                }
            }
        }
    }
    
    /*
     * Function Name: pritnNodes  
     * Parameter(s): uses the nodes attribute 
     * Return(s): N/A
     * Description: Prints out the distantVector/routing Table for each node
    */
    public void printNodes()
    {
        for(int i = 0; i < nodes.size(); i++)
        {
            router r = nodes.get(i);
            router neighbohr;
            ArrayList<router> router_neighbohrs = r.neighbohr_data;
            System.out.println("\nNode "+r.router_id +" Table");
            System.out.printf("   ");
            for(int k = 0; k < router_ids.size(); k++)
            {
                System.out.printf(router_ids.get(k)+"   ");
            }
            System.out.println("\n--------------------------");
            
            for(int k = 0; k < router_ids.size(); k++)
            {
               if(r.router_id == router_ids.get(k))
               {
                   System.out.printf(router_ids.get(k)+"| ");
                   for(int a = 0; a < router_ids.size(); a++)
                   {
                       if(r.neighbohrs.get(router_ids.get(a)) == INFINITY)
                        {
                            System.out.print("\u221e"+"   ");
                        }
                        else
                        {
                            if(r.neighbohrs.get(router_ids.get(a)) > 9)
                            {
                                System.out.print(r.neighbohrs.get(router_ids.get(a))+"  ");
                            }
                            else
                            {
                                System.out.print(r.neighbohrs.get(router_ids.get(a))+"   ");
                            }
         
                        }
                   }
                   System.out.println("\n");
                }
               else
               {
                    
                    for(int j = 0; j < router_neighbohrs.size(); j++)
                    {
                        if(router_neighbohrs.get(j).router_id == router_ids.get(k))
                        {
                            neighbohr = router_neighbohrs.get(j);
                            System.out.printf(neighbohr.router_id+"| ");
                            for(int a = 0; a < router_ids.size(); a++)
                            {
                                if(neighbohr.neighbohrs.get(router_ids.get(a)) == INFINITY)
                                {
                                    System.out.print("\u221e"+"   ");
                                }
                                else
                                {
                                    if(neighbohr.neighbohrs.get(router_ids.get(a)) > 9)
                                    {
                                        System.out.print(neighbohr.neighbohrs.get(router_ids.get(a))+"  ");
                                    }
                                    else
                                    {
                                       System.out.print(neighbohr.neighbohrs.get(router_ids.get(a))+"   ");
                                    }
                                
                                }
                            }
                           System.out.println("\n");
                        }
                    }
                }
            }
        }
    }
    
    /*
     * Function Name: exchangeTables  
     * Parameter(s): uses the nodes attribute 
     * Return(s): N/A
     * Description: loops thorugh each node and exchanges tables with them. A router
     *              sets its dumb routers = to the corresponding real router. by doing this it
     *              keeps track of the surronding routers. 
    */
    public void exchangeTables()
    {
        ArrayList<router> checkNodes = deepCopyNodes(nodes);
        int node_index = 0;
        checkStableTable = 0;
        int neighbohr_router_index = 0;
        for(int i = 0; i < router_ids.size(); i++)
        {
            for(int a = 0; a < nodes.size(); a++)
            {
                if(nodes.get(a).router_id == router_ids.get(i))
                {
                    node_index = a;
                }
            }
            ArrayList<router> neighbohr_routers = nodes.get(node_index).neighbohr_data;
            for(int j = 0; j < nodes.size(); j++)
            {
                router node = nodes.get(j);
                if(router_ids.get(i) != node.router_id)
                {
                    for(int k = 0; k < neighbohr_routers.size(); k++)
                    {
                        if(neighbohr_routers.get(k).router_id == node.router_id)
                        {
                            neighbohr_router_index = k;
                        }
                    }
                    neighbohr_routers.remove(neighbohr_router_index);
                    router new_node = new router(node.router_id);
                    for(int k = 0; k < router_ids.size(); k++)
                    {
                         new_node.neighbohrs.put(router_ids.get(k),node.neighbohrs.get(router_ids.get(k)));
                    }
                    new_node.neighbohr_data = node.neighbohr_data;
                    neighbohr_routers.add(new_node);
                }
            }
             nodes.get(node_index).neighbohr_data = neighbohr_routers;
        }
        
        for(int i = 0; i < checkNodes.size(); i++)
        {
            //System.out.println(checkNodes+" "+nodes);
            router router_a = checkNodes.get(i);
            router router_b = nodes.get(i);
            for(int k = 0; k < router_a.neighbohr_data.size(); k++)
            {
                router router_a_neighbohr = router_a.neighbohr_data.get(k);
                router router_b_neighbohr = router_b.neighbohr_data.get(k);
                HashMap a_Hash = router_a_neighbohr.neighbohrs;
                HashMap b_Hash = router_b_neighbohr.neighbohrs;
                //System.out.println(a_Hash+" "+b_Hash); 
                for(int j = 0; j < router_ids.size(); j++)
                {
                    if(a_Hash.get(j) != b_Hash.get(j))
                    {
                        checkStableTable++;
                        //System.out.println(a_Hash.get(j)+" OH YEAH "+ b_Hash.get(j));
                    }
                }
                
            }
        }
    }
    
    /*
     * Function Name: deepCopyNodes  
     * Parameter(s): an arraylist of routers that will be deep copied
     * Return(s): a deep copy of the passed in arraylist
     * Description:takes a arraylist of routers and deep copies it. 
    */
    public ArrayList<router> deepCopyNodes(ArrayList<router> node_list)
    {
        ArrayList<router> copy = new ArrayList<router>();
        router new_router;
        router new_router_data;
        router temp_router;
        router temp_router_neighbohr_data;
        for(int i = 0; i < node_list.size(); i++)
        {
            temp_router = node_list.get(i);
            new_router = new router(temp_router.router_id);
            for(int k = 0; k < temp_router.neighbohr_data.size(); k++)
            {
                temp_router_neighbohr_data = temp_router.neighbohr_data.get(k);
                new_router_data = new router(temp_router_neighbohr_data.router_id);
                HashMap<Integer,Integer> dataMap = new HashMap<Integer,Integer>();
                for(int j = 0; j < router_ids.size(); j++)
                {
                    dataMap.put(router_ids.get(j),temp_router_neighbohr_data.neighbohrs.get(router_ids.get(j)));
                }
                new_router_data.neighbohrs = dataMap;
                new_router.neighbohr_data.add(new_router_data);
            }
            copy.add(new_router);
        }
        return copy;
    }
    
    /*
     * Function Name: recalculatePaths  
     * Parameter(s): uses nodes attribute
     * Return(s): nothing
     * Description: goes thorugh each router in nodes and checks if for each connection the 
     *              cost can be shorten, it check by using the distantVectorAlgorithm function.
     *              Additionally it checks if the paths changed for stability checking. 
    */
    public void recalculatePaths()
    {
        int newCost = INFINITY;
        checkStablePath = 0;
        //for each node pass it in and a neighbohr for ech neighbohr and figure out cost.
        for(int i = 0; i < nodes.size(); i++)
        {
            HashMap<Integer,Integer> checkChange = new HashMap<Integer,Integer>(nodes.get(i).neighbohrs);
            for(int j = 0; j < router_ids.size(); j++)
            {
                if(nodes.get(i).router_id != router_ids.get(j))
                {
                   ArrayList<Integer> visited = new ArrayList<Integer>();
                   newCost = distanceVectorAlgorithm(nodes.get(i).router_id ,router_ids.get(j), visited);
                   if( nodes.get(i).neighbohrs.get(router_ids.get(j)) != newCost)
                   {
                        nodes.get(i).neighbohrs.put(router_ids.get(j),newCost);
                   }
                }
            }
            if(!checkChange.equals(nodes.get(i).neighbohrs))
            {
                //System.out.println("CHANGE IN PATH");
                checkStablePath++;
            }
        }
    }
    
    /*
     * Function Name: distanceVectorAlgorithm  
     * Parameter(s): Two ints for router ids and an arraylist to keeptrack of what was visited. 
     * Return(s): an int representing the shorest cost from a to b.  
     * Description: uses the distant vector algorithm and recursion to find the shortest path
     *              from A to B.
    */
    public int distanceVectorAlgorithm(int router_a_id, int router_b_id, ArrayList<Integer> visited)
    {
        if(router_a_id == router_b_id)
        {
            return 0;
        }
        router router_a = null;
        int shortestPathCost = INFINITY;
        int travel_to_neighbohr_cost;
        int distance_from_neighbohr_to_destination; 
        int total_travel_cost;
        ArrayList<Integer> distances = new ArrayList<Integer>();
        for(int i = 0; i < nodes.size(); i++)
        {
            if(nodes.get(i).router_id == router_a_id)
            {
                router_a = nodes.get(i);
            }
        }
        if(visited.contains(router_a_id))
        {
            return router_a.neighbohrs.get(router_b_id);
        }
        visited.add(router_a_id);
        for(int i = 0; i < router_ids.size(); i++)
        {
            if(router_ids.get(i) == router_a.router_id)
            {
                continue;
            }
            travel_to_neighbohr_cost = router_a.neighbohrs.get(router_ids.get(i));
            distance_from_neighbohr_to_destination = distanceVectorAlgorithm(router_ids.get(i),router_b_id,visited);
            total_travel_cost = travel_to_neighbohr_cost + distance_from_neighbohr_to_destination;
            distances.add(total_travel_cost);
        }
        for(int k = 0; k < distances.size(); k++)
        {
            if(shortestPathCost > distances.get(k))
            {
                shortestPathCost = distances.get(k);
            }
        }
        //System.out.println(router_a_id+" "+router_b_id+" "+distances+" "+shortestPathCost);
        return shortestPathCost;
    }
    
    /*
     * Function Name: changeLinkCost  
     * Parameter(s): takes ints for router ids and an int for the new cost
     * Return(s): N/A
     * Description: changes the travel cost from node to neighbohr.
    */
    public void changeLinkCost(int node, int neighbohr, int cost)
    {
        for(int i = 0; i < nodes.size(); i++)
        {
            if(nodes.get(i).router_id == node)
            {
                nodes.get(i).neighbohrs.put(neighbohr,cost);
            }
        }
    }
    
    /*
     * Function Name: main  
     * Parameter(s):the filename
     * Return(s): N/A
     * Description: calls for reading of file, building of nodes and an intial print.
     *              Then it loops through based on user input and displays output based 
     *              on the input. 
    */
    public static void main(String[] args) throws IOException
    {
        DistantVectorSim dvs = new DistantVectorSim();
       // System.out.printf("Enter input filename: ");
        Scanner commandline = new Scanner(System.in);
        String filename = args[0];
        BufferedReader fileInput = dvs.readFile(filename);
        dvs.buildNodes(fileInput);
        System.out.println("\nInitial state: ");
        dvs.printNodes();
        String input;
        int number_of_iterations = 0;
        while(0 == 0)//delete break at beginning
        {
            System.out.printf("Enter STEP for next step, LC for Link change, RUN to go till stable, or EXIT to end: ");
            input = commandline.nextLine();
            if(input.equals("STEP"))
            {
                number_of_iterations++;
                System.out.println("\nSTEP: "+number_of_iterations);
                dvs.exchangeTables();
                dvs.recalculatePaths();
                dvs.printNodes();
                //System.out.println(checkStablePath+" "+checkStableTable);
                if(checkStablePath == 0 && checkStableTable == 0)
                {
                    System.out.println("STABLE");
                    dvs.stable = true;
                    //break;
                }
                continue;
            }
            else if(input.equals("RUN"))
            {
                while(dvs.stable == false)
                {
                    number_of_iterations++;
                    dvs.exchangeTables();
                    dvs.recalculatePaths();
                    //dvs.printNodes();
                    if(checkStablePath == 0 && checkStableTable == 0)
                    {
                        System.out.println("\nSTEP: "+number_of_iterations);
                        dvs.printNodes();
                        System.out.println("STABLE");
                        dvs.stable = true;
                        break;
                    }
                }
               // break;
            }
            else if(input.equals("LC"))
            {
                
                System.out.print("Enter the node who's link will be changed: ");
                String changed_node = commandline.nextLine();
                try
                {
                    if(!router_ids.contains(Integer.parseInt(changed_node)))
                    {
                        System.out.println("ERROR IN NODE INPUT: NODE DOES NOT EXISTS");
                        continue;
                    }
                }
                catch(Exception E)
                {
                    System.out.println("ERROR IN NODE INPUT: MUST BE INT");
                    continue;
                }
                
                System.out.print("Enter the neighbohr who's link will be changed: ");
                String changed_neighbohr = commandline.nextLine();
                try
                {
                    if(!router_ids.contains(Integer.parseInt(changed_neighbohr)))
                    {
                        System.out.println("ERROR IN NEIGHBOHR INPUT: NODE DOES NOT EXISTS");
                        continue;
                    }
                    if(changed_neighbohr.equals(changed_node))
                    {
                        System.out.println("ERROR IN NEIGHBOHR INPUT: NODES SHOULD NOT BE EQUAL");
                        continue;
                    }
                }
                catch(Exception E)
                {
                    System.out.println("ERROR IN NODE INPUT: MUST BE INT");
                    continue;
                }
                
                System.out.print("Enter the neighbohr who's link will be changed: ");
                int changed_cost = INFINITY;
                try
                {
                    changed_cost = Integer.parseInt(commandline.nextLine());
                }
                catch(Exception E)
                {
                    System.out.println("ERROR IN NEW LINK COST INPUT: MUST BE INT");
                    continue;
                }
                dvs.changeLinkCost(Integer.parseInt(changed_node),Integer.parseInt(changed_neighbohr),changed_cost);
                System.out.println("LINK CHANGED");
                dvs.stable = false;
                System.out.println("\nSTEP: "+number_of_iterations);
                dvs.printNodes();
            }
            else if(input.equals("EXIT"))
            {
                break;
            }
            else
            {
                System.out.println("Invalid Input\n");
                continue;
            }
        }
        System.out.println("___________________________________________");
        System.out.println("STABLE State: STEP "+number_of_iterations);
        dvs.printNodes();
    }
}

/*
     * Class Name: router  
     * Attributes(s): an id , a hashmap for its distant vector table, and arraylist of 'dumb' routers
     *                for tracking what it knows about those around it. 
    */
class router
{
    public final int router_id;
    public HashMap<Integer, Integer> neighbohrs;
    public ArrayList<router> neighbohr_data;
    
    public router(int id)
    {
        router_id = id;
        neighbohrs = new HashMap<Integer, Integer>();
        neighbohr_data = new ArrayList<router>();
    }
    
    public router(int id, int neighbohr, int distance)
    {
        router_id = id;
        neighbohrs = new HashMap();
        neighbohrs.put(id,distance);
        neighbohr_data = new ArrayList<router>();
    }
    
    public void addNeighbohr(int id, int distance)
    {
        neighbohrs.put(id,distance);
    }
    
    public void updateNeightbohr(int id, int distance)
    {
        neighbohrs.put(id,distance);
    }
}