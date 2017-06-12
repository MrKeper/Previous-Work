/*
 *
 */
import java.util.*;
import java.io.*;


public class find_route 
{
    
    public  BufferedReader readFile (String filename)
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
    
    public ArrayList<city> buildList(BufferedReader buffer) throws IOException
    {
        ArrayList<city> list = new ArrayList<city>();
        String[] input;
        String fileLine;
        int check = 0;
        int check_neighbor = 0;
        while( (fileLine = buffer.readLine()) != null)
        {
            check = 0;
            check_neighbor = 0;
            input = fileLine.split(" ");
            if(input[0].equals("END"))
            {
                break;
            }
                
            for(int i = 0; i < list.size(); i++)
            {
                
                if((list.get(i).getName()).equals(input[0]))
                {
                    list.get(i).addNeighbor(input[1],Integer.valueOf(input[2]));
                    check = 1;
                    break;
                }
            }
            for(int k = 0; k < list.size(); k++)
            {
                if((list.get(k).getName()).equals(input[1]))
                {
                    list.get(k).addNeighbor(input[0],Integer.valueOf(input[2]));
                    check_neighbor = 1;
                    break;
                }
            }
            if(check == 1 && check_neighbor == 1)
            {
                continue;
            }
            else if(check == 1 && check_neighbor == 0)
            {
                city c = new city(input[1],input[0],Integer.valueOf(input[2]));
                list.add(c);
                continue;
            }
            else if(check == 0 && check_neighbor == 1)
            {
                city c = new city(input[0],input[1], Integer.valueOf(input[2]));
                list.add(c);
                continue;
            }
            else
            {
                city c = new city(input[0],input[1], Integer.valueOf(input[2]));
                city n = new city(input[1],input[0], Integer.valueOf(input[2]));
                list.add(n);
                list.add(c);
            }

            
        }
        return list;
    }
    
    public void printList(ArrayList<city> l)
    {
         for(int i = 0; i < l.size(); i++)
            {
                System.out.println(l.get(i).getName());
            }
    }
    
    public int routeSearch(ArrayList<city> list, String initial, String goal) // Search function using Uniform cost search
    {
        //printList(list);
        int dist = -1;
        int intialIndex=0;
        intialIndex = findCityIndex(initial,list);
        if(intialIndex == -1)
        {
            return -1;
        }
        route initialRoute = new route(list.get(intialIndex));
        int addedDistance;
        PriorityQueue<route> queue = new PriorityQueue<route>();
        queue.add(initialRoute);
        while(queue.size() != 0)
        {
            route tempRoute = queue.remove();
            ArrayList<city> tempCityList = tempRoute.getPath();
            city lastCity = tempCityList.get(tempCityList.size()-1);
            if(lastCity.getName().equals(goal))
            {
                printRoute(tempRoute);
                dist = tempRoute.getDistance();
                return dist;
            }
            else
            {
                ArrayList<String> tempNeighborList = lastCity.getNeighbors();
                int check = 0;
                for(int i = 0; i < tempNeighborList.size();i++)
                {
                    check = 0;
                    route addRoute = copyRoute(tempRoute);
                    city newCity = list.get(findCityIndex(tempNeighborList.get(i),list));
                    for(int k = 0; k < addRoute.getPath().size();k++)
                    {
                        if(newCity.getName().equals(addRoute.getPath().get(k).getName()))
                        {
                            check = 1;
                            break;
                        }
                    }
                    if(check == 1)
                    {
                        continue;
                    }
                    addRoute.addCity(newCity);
                    addRoute.addDistance(lastCity.getDistances().get(i));
                    queue.add(addRoute);
                }
            }
        }
        return dist;
    }
    
    public route copyRoute(route r)
    {
        route returnRoute = new route();
        for(int i = 0; i < r.getPath().size(); i++)
        {
            returnRoute.getPath().add(r.getPath().get(i));
        }
        returnRoute.addDistance(r.getDistance());
        return returnRoute;
    }
    
    public int findCityIndex(String cityName, ArrayList<city> list)
    {
        int cityIndex=-1;
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i).getName().equals(cityName))
            {
                cityIndex = i;
                return cityIndex;
            }
        }
        return cityIndex;
    }
    
    public void printRoute(route r)
    {
        ArrayList<city> cityList = r.getPath();
        System.out.println("Route: ");
        int d = 0;

        for(int i = 0; i < cityList.size()-1;i++)
        {
             city c1 = cityList.get(i);
             city c2 = cityList.get(i+1);
             for(int k = 0; k < c1.getNeighbors().size(); k++)
             {
                 if(c1.getNeighbors().get(k).equals(c2.getName()))
                 {
                     d = k;
                     break;
                 }
             }
             d = c1.getDistances().get(d);
             System.out.println(c1.getName() + " to " + c2.getName() + " | " + d);
        }
    }


    public static void main(String[] args) throws IOException  
    {
        if( args.length != 3)
        {
            System.out.println("Error in input");
        }
        String filename = args[0];
        String initialState = args[1];
        String goalState = args[2];
        find_route route = new find_route();
        BufferedReader fileInput = route.readFile(filename);
        ArrayList<city> cityList = route.buildList(fileInput);
        int distanceToGoal = route.routeSearch(cityList,initialState,goalState);
        if(distanceToGoal < 0)
        {
            System.out.println("Distance = infintity");
            System.out.println("Route does not exist");
        }
        else
        {
            System.out.println("Distance = "+ distanceToGoal);
        }
        fileInput.close();  
    }
    
   public class city
    {
        private String name;
        private ArrayList<String> neighbors;
        private ArrayList<Integer> dist;
        
        public city(String cityName, String neighborCity, int d)
        {
            name = cityName;
            neighbors = new ArrayList<String>();
            dist = new ArrayList<Integer>();
            neighbors.add(neighborCity);
            dist.add(d);
        }

        public void addNeighbor (String cityName, int d)
        {
            neighbors.add(cityName);
            dist.add(d);
        }
        
        public String getName()
        {
            return name;
        }
        
        public ArrayList<String> getNeighbors()
        {
            return neighbors;
        }
        
        public ArrayList<Integer> getDistances()
        {
            return dist;
        }
    }
    
    public class route implements Comparable<route>
    {
        private ArrayList<city> path;
        private int distance;
        
        public route(city c)
        {
            path = new ArrayList<city>();
            path.add(c);
            distance = 0;
        }
        
        public route()
        {
            path = new ArrayList<city>();
            distance = 0;
        }
        
        public void addCity(city c) { this.getPath().add(c); }
        public void addDistance(int d){ distance = distance + d;}
        
        public ArrayList<city> getPath() {return path;}
        public int getDistance() {return distance;}
        
        public boolean equals(route object2)
        {
            return this.getDistance() == object2.getDistance();
        }
        
        public int compareTo(route object2)
        {
            if(this.equals(object2))
            {
                return 0;
            }
            else if (getDistance() > object2.getDistance())
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
    
}
