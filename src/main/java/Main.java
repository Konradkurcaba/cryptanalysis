import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main
{

    public static final String SBOX_FILENAME = "sbox1.txt";

    public static void main(String... args) throws IOException
    {
        SBoxFileReader reader = new SBoxFileReader();
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(SBOX_FILENAME);
        List<Byte> sBox = reader.readSbox(stream);
        DistributionCalculator calculator = new DistributionCalculator();
        int[][] distribution = calculator.calculate(sBox);
        DistributionDisplayer displayer = new DistributionDisplayer();
        displayer.display(distribution);

        int S1_e = 4;
        int S1__e = 55;
        System.out.println("Para wejściowa: S1_e=" + S1_e + " S1__e=" + S1__e);

        int S1_k = 23;
        System.out.println("Ustawiony klucz: S1_k=" + S1_k);

        int xor_in = S1_e ^ S1__e;
        System.out.println("XOR wejsciowy= " + xor_in);

        int S1_1 = S1_e ^ S1_k;
        int S1__1 = S1__e ^ S1_k;

        System.out.println("S1_1= " + S1_1);
        System.out.println("S1__1= " + S1__1);

        int S1_0 = calculator.findValue(S1_1, sBox);
        int S1__0 = calculator.findValue(S1__1, sBox);

        System.out.println("S1_0= " + S1_0);
        System.out.println("S1__0= " + S1__0);
        int xor_out = S1_0 ^ S1__0;
        System.out.println("Xor wyjsciowy= " + xor_out);
        System.out.println("************************");

        List<Integer>[] TP = ArrayUtils.countXorEqualArgument(xor_in);
        System.out.println("Pary wejsciowe dla XOR: " + xor_in);

        ArrayUtils.display(TP);

        System.out.println("Wszystkie pary dla XORa wejsciowego =" + xor_in + " i XORa wyjsciowego=" + xor_out);

        List<Integer> Pairs_values = ArrayUtils.findAllPairs(TP, xor_out, xor_in);

        System.out.println("Zbiór potencjalnych kluczy:");

        for (Integer value : Pairs_values)
        {
            int key = value ^ S1_e;
            System.out.println(value + " xor " + S1_e + "= " + key);

        }

    }

}
