public class DistributionDisplayer
{
    public void display(int[][] distribution)
    {
        for (int i = 0; i < 64; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                System.out.print((distribution[i][j]));
                if(distribution[i][j]>9)
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
    }
}
