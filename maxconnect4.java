import java.util.*;
import java.io.*;
/*
* Keenan Parker 1001024878
* CSE 4308 Artificial Intelligence I
* Assignment 3
* 
*/
public class maxconnect4
{
    public int max_player;
    public int min_player;
    
    public static void main(String args[]) throws IOException
    {
        if(args.length != 4)
        {
            System.out.println("Error in input");
            System.exit(0);
        }
        maxconnect4 connect4 = new maxconnect4();
        String gameMode = args[0];
        String inputFile = args[1];
        String vary = args[2]; // can be whose turn is next in interactive or output on onemove
        int depth = Integer.valueOf(args[3]);
        BufferedReader fileInput = connect4.readFile(inputFile);
        int[][] board;
        if(fileInput == null)
        {
            board = connect4.emptyBoard();
        }
        else
        {
            board = connect4.readInputBoard(fileInput);
        }
        fileInput.close();
		if(gameMode.equals("interactive"))
		{
		   if(connect4.isBoardFull(board))
		    {
		         connect4.printBoard(board);
    		    int min_score = connect4.reportScore(board,connect4.min_player);
    		    int max_score = connect4.reportScore(board,connect4.max_player);
    		    System.out.println("Final score: You - "+min_score+" Computer - "+max_score);
		    }
		    else
		    {
		        boolean computer_turn = true;
    		   int pick;
    		   PrintWriter human = connect4.openOutput("human.txt");
    		   PrintWriter computer = connect4.openOutput("computer.txt");
    		   if(!vary.equals("computer-next"))
    		   {
    		       int temp = connect4.max_player;
    		       connect4.max_player = connect4.min_player;
    		       connect4.min_player = temp;
    		       computer_turn = false;
    		   }
    		   while(!connect4.isBoardFull(board))
    		   {
    		       if(computer_turn)
    		       {
    		         System.out.println("Computer's turn (Player "+connect4.max_player+") : ");
    		         connect4.printBoard(board);
    		         System.out.println();
    		         pick = connect4.aB_MiniMax_DepthLimited(board,depth);
    		         board = connect4.placePiece(board,connect4.max_player,pick);
    		         computer_turn = false;
    		         connect4.writeBoard(board,computer,connect4.max_player);
    		       }
    		       else
    		       {
    		         System.out.println("Your turn (Player "+connect4.min_player+") : ");
    		         System.out.println();
    		         pick = connect4.getPlayerInput(board);
    		         board = connect4.placePiece(board,connect4.min_player,pick);
    		         computer_turn = true;
    		         connect4.writeBoard(board,human,connect4.min_player);
    		       }
		      }
		      connect4.printBoard(board);
		   int human_final_score = connect4.reportScore(board,connect4.min_player);
		   int computer_final_score = connect4.reportScore(board,connect4.max_player);
		   if(human_final_score > computer_final_score)
		   {
		       System.out.println("(: You Win :)");
		       System.out.println("Final score: You - "+human_final_score+" Computer - "+computer_final_score);

		   }
		   else if (human_final_score < computer_final_score)
		   {
		       System.out.println("): You Lose :(");
		       System.out.println("Final score: You - "+human_final_score+" Computer - "+computer_final_score);
		   }
		   else
		   {
		       System.out.println("|: Draw :|");
		       System.out.println("Final score: You - "+human_final_score+" Computer - "+computer_final_score);
		   }
		   human.close();
		   computer.close();
		   

		   }
		   
		}
		else if(gameMode.equals("one-move"))
		{
		    
		    //java maxconnect4 one-move [input_file] [output_file] [depth]
		    if(connect4.isBoardFull(board))
		    {
		         connect4.printBoard(board);
    		    int min_score = connect4.reportScore(board,connect4.min_player);
    		    int max_score = connect4.reportScore(board,connect4.max_player);
    		    System.out.println("Final score: Computer - "+max_score+" Opponent - "+min_score);
		    }
		    else
		    {
		        connect4.printBoard(board);
    		    int min_score = connect4.reportScore(board,connect4.min_player);
    		    int max_score = connect4.reportScore(board,connect4.max_player);
    		    System.out.println("Previous score: Computer - "+max_score+" Opponent - "+min_score);
    		    int pick = connect4.aB_MiniMax_DepthLimited(board,depth);
    		    board = connect4.placePiece(board,connect4.max_player,pick);
    		    min_score = connect4.reportScore(board,connect4.min_player);
    		    max_score = connect4.reportScore(board,connect4.max_player);
    		    connect4.printBoard(board);
    		    if(connect4.isBoardFull(board))
    		    {
    		        System.out.println("Final score: Computer - "+max_score+" Opponent - "+min_score);
        		    PrintWriter outputFile = connect4.openOutput(vary);
        		    connect4.writeBoard(board,outputFile,connect4.max_player);
        		    outputFile.close();
    		    }
    		    else
    		    {
    		        System.out.println("Current score: Computer - "+max_score+" Opponent - "+min_score);
        		    PrintWriter outputFile = connect4.openOutput(vary);
        		    connect4.writeBoard(board,outputFile,connect4.max_player);
        		    outputFile.close();
    		    }
    		    
		    }
		    
		}
		else
		{
		    System.out.println("Invlaid Game mode Entered");
		}
    }
    
    
    
    
    public int aB_MiniMax_DepthLimited(int[][] gameBoard, int depthLimit)
    {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int temp = Integer.MIN_VALUE;
        int v = maxValue(gameBoard,alpha,beta, depthLimit);
        int[][] tempBoard = null;
        ArrayList<int[][]> succesor_list = succesors(gameBoard,max_player);
        
        for(int i= 0; i < succesor_list.size(); i++)
        {
            if(v > temp)
            {
                temp = v;
                tempBoard = succesor_list.get(i);
            }
            v = maxValue(succesor_list.get(i),alpha,beta, depthLimit);
        }
        
        int column = findAction(gameBoard,tempBoard); // find # 
        return column;
    }
    
    public int findAction(int[][] gameBoard , int[][] nextBoard)
    {
        for(int row  = 0; row < 6; row++)
        {
            for(int col = 0; col < 7; col++)
            {
                if(gameBoard[row][col] != nextBoard[row][col])
                {
                    return col;
                }
            }
        }
        return -1;
    }
    
    public int maxValue(int[][] gameBoard, int alpha, int beta, int d)
    {
        if(isBoardFull(gameBoard) == true|| d <= 0)
        {
            return utilityEval(gameBoard, max_player);
        }
        int v = Integer.MIN_VALUE;
        int a = alpha;
        int b = beta;
        ArrayList<int[][]> succesor_list = succesors(gameBoard,max_player);
        for(int i = 0; i < succesor_list.size(); i++)
        {

           v = max(v,minValue(succesor_list.get(i),a,b,d-1));
            if(v >= b)
            {
                return v;
            }
            a = max(a,v);
        }
        return v;
    }
    
     public int minValue(int[][] gameBoard, int alpha, int beta, int d)
    {
       if(isBoardFull(gameBoard) == true|| d == 0)
        {
            return -utilityEval(gameBoard, min_player);
        }
        int v = Integer.MAX_VALUE;
        int a = alpha;
        int b = beta;
        ArrayList<int[][]> succesor_list = succesors(gameBoard,min_player);
        for(int i = 0; i < succesor_list.size(); i++)
        {
            v = min(v,maxValue(succesor_list.get(i),a,b,d-1));
            if(v <= a)
            {
                return v;
            }
         b = min(a,v);
        }
         
        return v;
    }
    
    public int max(int a, int b)
    {
        if(a > b){ return a;}
        return b;
        
    }
    
    public int min(int a, int b)
    {
        if(a < b){ return a;}
        return b;
        
    }
    
    public int utilityEval(int[][] gameBoard, int playerNumber)
    {
        int max_score = reportScore(gameBoard,max_player);
        int min_score = reportScore(gameBoard,min_player);

       return max_score-min_score; 
    }
    
    public ArrayList<int[][]> succesors(int[][] gameBoard, int playerNumber)
    {
        ArrayList<int[][]> succesor_list = new ArrayList<int[][]>();
        int[][] tempBoard = deepCopyBoard(gameBoard);;
        for(int i =0; i < 7;i++)
        {
            if(tempBoard[0][i] != 0)
            {
                continue;
            }
            tempBoard = placePiece(tempBoard,playerNumber,i);
            succesor_list.add(tempBoard);
            tempBoard = deepCopyBoard(gameBoard);
            
        }
        return succesor_list;
    }

    public int[][] deepCopyBoard(int[][] gameBoard)
    {
        int[][] tempBoard = new int[6][7] ;
        for(int i = 0;i < 6; i++)
        {
            for(int k = 0; k < 7; k++)
            {
                tempBoard[i][k] = gameBoard[i][k];
            }
        }
        return tempBoard;
    }
    
    public  BufferedReader readFile (String filename) 
    {
        BufferedReader buffer = null;
        try
        { buffer = new BufferedReader(new FileReader(filename)); }
        catch(FileNotFoundException FNFE)
        {   buffer = null; }
        finally
        { return buffer; }
    }
    
    public  PrintWriter openOutput (String filename) 
    {
        PrintWriter output = null;
        try
        { output = new PrintWriter(new FileWriter(filename)); }
        catch(FileNotFoundException FNFE)
        {   output = new PrintWriter(new FileWriter(new File(filename))); }
        finally
        { return output; }
    }
    
    public int[][] readInputBoard(BufferedReader buffer) throws IOException
    {
        String fileLine;
        int[][] returnArray = new int[6][7];
        int row = 0;
        while( (fileLine = buffer.readLine()) != null)
        {
            char[] chop= fileLine.toCharArray();
            if(row == 6)
            {
                max_player = Integer.valueOf(String.valueOf(chop[0]));
                if(max_player == 1)
                {
                    min_player = 2;
                }
                else
                {
                    min_player = 1;
                }
                return returnArray;
            }
            for(int i = 0; i < chop.length; i++)
            { returnArray[row][i] = Integer.valueOf(String.valueOf(chop[i])); }
            row++;
        }
        return returnArray;
    }
    
    public int reportScore(int[][] gameBoard,int playerNumber)// checks score of given a given player on a given board.
    {
        int score = 0;
        for(int i = 0; i < 6;i++)//horizontal
        {
            for(int k = 0; k < 4;k++)
            {
                if(gameBoard[i][k] == playerNumber && gameBoard[i][k+1] == playerNumber && gameBoard[i][k+2] == playerNumber && gameBoard[i][k+3] == playerNumber)
                {
                    score++;
                }
            }
        }
        
        for(int c = 0; c < 7;c++)//vertical
        {
            for(int r = 0; r < 3;r++)
            {
                if(gameBoard[r][c] == playerNumber && gameBoard[r+1][c] == playerNumber && gameBoard[r+2][c] == playerNumber && gameBoard[r+3][c] == playerNumber)
                {
                   score++;
                }
            }
        }
        
        for(int i = 0; i < 3;i++)//diag
        {
            for(int k = 0; k < 4;k++)
            {
                if(gameBoard[i][k] == playerNumber && gameBoard[i+1][k+1] == playerNumber && gameBoard[i+2][k+2] == playerNumber && gameBoard[i+3][k+3] == playerNumber)
                {
                    score++;
                }
            }
        }
    
        for(int i = 5; i > 2;i--)//diag
        {
            for(int k = 0; k < 4;k++)
            {
                if(gameBoard[i][k] == playerNumber && gameBoard[i-1][k+1] == playerNumber && gameBoard[i-2][k+2] == playerNumber && gameBoard[i-3][k+3] == playerNumber)
                {
                    score++;
                }
            }
        }
    
        return score;
    }
    
    public void printBoard(int[][] gameBoard)//Prints gameboard at its current state
    {
        for(int i = 0; i < 6;i++)
        {
            for(int k = 0; k < 7;k++)
            {
                System.out.printf("%d",gameBoard[i][k]);
            }
            System.out.println();
        }
    }
    
    public void writeBoard(int[][] gameBoard, PrintWriter output, int playerNumber)//Writes current board and whose turn it is onto output file.
    {
         for(int i = 0; i < 6;i++)
        {
            for(int k = 0; k < 7;k++)
            {
                output.printf("%d",gameBoard[i][k]);
            }
            output.println();
        }
        if(playerNumber== 1)
        {
            output.printf("%d",2);
        }
        else
        {
            output.printf("%d",1);
        }
     output.println();
    }
    
    public int getPlayerInput(int[][] gameBoard)// returns column player chose (Will need do be decremented to not cause expections.)
    {
        printBoard(gameBoard);
        System.out.println("Enter the number of the column you want to chose: ");
        Scanner command = new Scanner(System.in);
        boolean input_error = true;
        int col = 42;
        while(input_error)
        {
            if(command.hasNextInt())
            {
                col = command.nextInt();
            }
            else
            {
                printBoard(gameBoard);
                System.out.println("Input must be a int between 1 and 7");
                command.next();
                continue;
            }
            input_error = false;
        }
        col = col-1;
        if(col > 7 || col < 0)
        {
            System.out.println("Column entered must be a number 1 through 7, Try again.");
            col =  getPlayerInput(gameBoard);
        }
        if(gameBoard[0][col] != 0)
        {
            System.out.println("Column entered is full, Try again.");
            col = getPlayerInput(gameBoard);
        }
        return col;
    }
    
    public int[][] placePiece(int[][] gameBoard, int playerNumber, int column)
    {
        for(int i = 0; i < 5;i++)
        {
            if(gameBoard[i][column] == 0 && gameBoard[i+1][column] != 0)
            {
                gameBoard[i][column] = playerNumber;
                break;
            }
            else if(gameBoard[5][column] == 0)
            {
                gameBoard[5][column] = playerNumber;
                break;
            }
        }
        
        return gameBoard;
    }
    
    public boolean isBoardFull(int[][] gameBoard)
    {
        for(int c = 0; c < 7;c++)
        {
            if(gameBoard[0][c] == 0)
            {
               return false;
            }
        }
        return true;
    }
    
    public int[][] emptyBoard()
    {
        int[][] temp = new int[6][7];
        return temp;
    }
}