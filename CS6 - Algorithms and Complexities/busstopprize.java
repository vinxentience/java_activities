import java.util.Scanner;
/*
    Submitted By:
    Canlas, Nebran, Blase, Talamillo
*/
class busstopprize{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);  
    int n, p;
    do{
        System.out.print("Enter number of bus stops: ");
        n = in.nextInt();
    }while(n < 1 || n > 100000);

        int prize[] = new int[n];

        System.out.print("Enter the prizes: ");
        for(int i = 0; i < n; i++){
                do{
                    p = in.nextInt();
                }while(p < -100 || p > 100);
            prize[i] = p;
        }
    getMaxPrize(prize);
  }

    public static void getMaxPrize(int[] p)
  {

        int global = p[0], current = p[0]; //Set global and current to the first element of array p[]
        int end_idx = 0, total_prize = 0;
    
        for (int i = 1; i < p.length; ++i)
        {
            current = Math.max(p[i], p[i] + current); //Get the max current value
            if (current > global) //If current value is greater than the global, set current as the new global
            {
                global = current;
                end_idx = i; //Assign i as the ending index
            }
        }
    
        int start_idx = end_idx;
        //This loop will get the starting index of stops to take
        while (start_idx >= 0) 
        {
            global -= p[start_idx];
            if (global == 0) break;
            start_idx--;
        }

        System.out.println("Bus stops you should board / deboard: ");
        for(int i = start_idx; i <= end_idx; ++i)
        {
            total_prize += p[i];
            System.out.println("Bus Stop #" + (i + 1) + ": " + p[i] + " (A[" + i + "])");
        }
        System.out.println("\nMaximum prize you can get: " + total_prize);
  }
}
