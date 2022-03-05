import java.util.*;
 /*
    Submitted By:
    Canlas, Nebran, Blase, Talamillo
*/
class fairpropertydivision
{

    static void fairDistribution(int[] arr, int n)
    {
        int i, j, sum = 0;
         
        // Finding sum of array elements
        for (i = 0; i < arr.length; i++) sum += arr[i];
        //Divide the sum of card values into 2
        int k = sum / 2;

        //Dynamic Programming (bottom-up)
        boolean [][]dp = new boolean[n + 1][k + 1];
     
        for (i = 1; i <= k; i++) dp[0][i] = false;
        
        for (i = 0; i <= n; i++) dp[i][0] = true;

        for (i = 1; i <= n; i++)
        {
            for (j = 0; j <= k; j++)
            {
                dp[i][j] = dp[i - 1][j];
                if (arr[i - 1] <= j) dp[i][j] = dp[i][j] | dp[i - 1][j - arr[i - 1]];
            }
        }
        
        //Array list for storing the cards
        List<Integer> set1 = new ArrayList<Integer>();
        int abeTotal = 0;
        List<Integer> cardset1 = new ArrayList<Integer>();
        List<Integer> set2 = new ArrayList<Integer>();
        int bobTotal = 0;
        List<Integer> cardset2 = new ArrayList<Integer>();
        
        if (!dp[n][k])
        {
            System.out.print("Fair distribution is not possible");
            return;
        } else {
            i = n;
            j = k;
        }
        
        while (i > 0 && j >= 0)
        {
            if (dp[i - 1][j]) //If this returns true, store the card on Bob's set
            {
                i--;
                cardset2.add(i + 1); //Cards
                set2.add(arr[i]); //Values
                bobTotal += arr[i];
            }
     
            else if (dp[i - 1][j - arr[i - 1]]) //If this returns true, store the card on Abe's set
            {
                i--;
                j -= arr[i];
                cardset1.add(i + 1); //Cards
                set1.add(arr[i]); //Values
                abeTotal += arr[i];
            }
        }
        
        System.out.print("\nAbe's Baseball Cards: ");
        for (i = 0; i < set1.size(); i++){
            System.out.print("C" + cardset1.get(i) + " ");  
        }

        System.out.print("\nValue (Abe): ");
        for (i = 0; i < set1.size(); i++){
            System.out.print(set1.get(i) + " ");  
        }
        System.out.print("\nSum (Abe): " + abeTotal);

        System.out.print("\nBob's Baseball Cards: ");
        for (i = 0; i < set2.size(); i++){
            System.out.print("C" + cardset2.get(i) + " ");  
        }

        System.out.print("\nValue (Bob): ");
        for (i = 0; i < set2.size(); i++){
            System.out.print(set2.get(i) + " ");
        }
        System.out.print("\nSum (Bob): " + bobTotal);
    }
     
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n, v;

        do{
            System.out.print("Enter number of baseball cards: ");
            n = in.nextInt();
        } while(n > 40 || n <= 1);

        int arr[] = new int[n];

        System.out.print("Enter card values: ");
        for(int i = 0; i < n; i++){
                do{
                    v = in.nextInt();
                }while(v < 1 || v > 40);
            arr[i] = v;
        }
        fairDistribution(arr, n);
    }
}

