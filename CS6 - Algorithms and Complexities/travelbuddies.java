import java.util.Scanner;
/*
    Submitted By:
    Canlas, Nebran, Blase, Talamillo
*/
public class travelbuddies {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] A; 
        int[][] B;
        int[][] distance;
        int n, d;

        do{
            System.out.print("Enter number of cities: ");
            n = in.nextInt();
        } while(n < 1 || n > 100);
        int sublist = n / 2; //divide list into two parts
        A = new int[sublist][sublist]; //For the cities you will visit
        B = new int[sublist][sublist]; //For the cities that your friend will visit
        distance = new int[n][n]; //For the entire cities
        System.out.println("Enter the distance of the cities: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                do{
                    d = in.nextInt();
                }while(d < 0 || d > 100);
                distance[i][j] = d;
            }
        }
        int row = 0, col = 0;
        //A loop that will split the original list of cities into two sublists
        for(int i = 0; i < distance.length; i++){
            for(int j = 0; j < distance[i].length; j++){
                if(i < sublist && j < sublist){
                    A[i][j] = distance[i][j];
                    
                } else if(i >= sublist  && j >= sublist){
                    B[row][col] = distance[i][j];
                    col++;
                    
                }
            }
            row += col / 2;
            col = 0;
        }
            System.out.print("\nMinimum cost of travel without dividing the list: " + getCost(distance[0].length, distance));
            System.out.print("\nMinimum cost of your travel (A): " +  getCost(A[0].length, A));
            System.out.print("\nMinimum cost of your friend's travel (B): " + getCost(B[0].length, B));

    }

    public static int minnode(int n, int k[], boolean minSet[])
  {
      int mini = Integer.MAX_VALUE;
      int mini_index = 0;
      for(int i = 0; i < n; i++)
      {
          if (minSet[i] == false && k[i] < mini)
          {
              mini = k[i];
              mini_index = i;
          }
      }
      return mini_index;
  }
  
  public static int getCost(int n, int city[][])
  {
      

      int parent[] = new int[n];
      int k[] = new int[n];
      
      boolean minSet[] = new boolean[n];
  
      for(int i = 0; i < n; i++)
      {
          k[i] = Integer.MAX_VALUE;
          minSet[i] = false;
      }
  
      parent[0] = -1;
      k[0] = 0;

      for(int i = 0; i < n - 1; i++)
      {

          int u = minnode(n, k, minSet);

          minSet[u] = true;

          for(int v = 0; v < n; v++)
          {
              if (city[u][v] > 0 && minSet[v] == false && city[u][v] < k[v])
              {
                  k[v] = city[u][v];
                  parent[v] = u;
              }
          }
      }
 
      int cost = 0;

      for(int i = 1; i < n; i++) cost += city[parent[i]][i];
          
      return cost;
  }
    
}
