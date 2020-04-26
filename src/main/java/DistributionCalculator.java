import java.util.List;

public class DistributionCalculator
{

    private static byte[] SBOX_ORDER =
    {
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30,
            1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31,
            32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62,
            33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63
    };

    public int[][] calculate(List<Byte> sBox)
    {
        int [][] distribution = new int[64][16];
        int w;

        for(int i = 0;i<64;i++)
        {
            for(int j = 0;j < 64; j++)
            {
                w = findValue(j,sBox) ^ findValue(j ^ i, sBox);
                distribution[i][w]++;
            }
        }
        return distribution;
    }

    private int findValue(int aPosition,List<Byte> sBox )
    {
        for (int i = 0; i < 64; i++)
        {
            if (SBOX_ORDER[i] == aPosition)
            {
                return sBox.get(i);
            }
        }
        return 0;
    }

}
