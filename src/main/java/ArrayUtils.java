import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtils
{

    static void display(List<Integer>[] table)
    {
        for (int i = 0; i < 16; i++)
        {
            System.out.println("OUT: " + i + " IN: ");
            table[i].forEach(value -> {
                System.out.print(value + " ");
            });
        }
    }

    public static List<Integer>[] countXorEqualArgument(int aArgument) throws IOException
    {
        int w;
        List<Integer>[] TP = new ArrayList[16];
        for (int i = 0; i < 16; i++)
        {
            TP[i] = new ArrayList<>();
        }
        DistributionCalculator calculator = new DistributionCalculator();
        for (int i = 0; i < 64; i++)
        {
            List<Byte> sBox = getSbox();
            w = calculator.findValue(i, sBox) ^ calculator.findValue(i ^ aArgument, sBox);
            TP[w].add(i);
        }
        return TP;
    }

    private static List<Byte> getSbox() throws IOException
    {
        SBoxFileReader reader = new SBoxFileReader();
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(Main.SBOX_FILENAME);
        List<Byte> sBox = reader.readSbox(stream);
        return sBox;
    }

    public static List<Integer> findAllPairs(List<Integer>[] aTable, Integer aXorOut, Integer aXorIn)
    {
        List<Integer> pairsValues = new ArrayList<Integer>();
        for (int i = 0; i < aTable[aXorOut].size(); i++)
        {
            for (int j = 0; j < aTable[aXorOut].size(); j++)
            {
                if (j != i)
                {
                    if ((aTable[aXorOut].get(i) ^ aTable[aXorOut].get(j)) == aXorIn)
                    {
                        System.out.print("(" + aTable[aXorOut].get(i) + "," + aTable[aXorOut].get(j) + ")");
                        pairsValues.add(aTable[aXorOut].get(i));
                    }

                }

            }
        }
        return pairsValues;
    }

}
