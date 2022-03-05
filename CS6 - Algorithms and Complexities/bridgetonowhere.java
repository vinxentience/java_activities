import java.util.Scanner;
/*
    Submitted By:
    Canlas, Nebran, Blase, Talamillo
*/
class bridgetonowhere {
    public static void getLCS(int[] north, int[] south, int m, int n) {
      
      //Dynamic Programming (bottom-up)
      int[][] table = new int[m + 1][n + 1];
      for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
          if (i == 0 || j == 0)
            table[i][j] = 0; //Set columns [i][0] and rows [0][i] to 0
          else if (north[i - 1] == south[j - 1]) //If the north is equal with south, set the value to 1 + its diagonal element value
            table[i][j] = table[i - 1][j - 1] + 1;
          else
            table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]); //Set the value to the max value between the top and prev value
        }
      }

      int index = table[m][n];
      int temp = index;
  
      int[] lcs = new int[index + 1];
      lcs[index] = 0;
  
      int i = m, j = n;
      while (i > 0 && j > 0) {
        if (north[i - 1] == south[j - 1]) {
          lcs[index - 1] = north[i - 1];
          i--;
          j--;
          index--;
        }
        else if (table[i - 1][j] > table[i][j - 1])
          i--;
        else
          j--;
      }
      
      System.out.print("Connecting Cities: ");
      for (int k = 0; k < temp; k++) System.out.print(lcs[k] + " ");

      System.out.println("\nMaximum number of bridges: " + table[m][n]);
    }
    
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        do{
          System.out.print("Enter total number of cities: ");
          n = in.nextInt();
        } while(n < 1 || n > 100);

        int north[] = new int[n];
        int south[] = new int[n];
        System.out.print("Enter permutation of cities on the south side river: ");
        for(int i = 0; i < n; i++){
            north[i] = i + 1;
            south[i] = in.nextInt();
        }
        getLCS(north, south, north.length, south.length);
    }
  }