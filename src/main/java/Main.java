import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main
{
    
    private static final String SBOX_FILENAME = "sbox1.txt";

    public static void main(String... args) throws IOException
    {
        SBoxFileReader reader = new SBoxFileReader();
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(SBOX_FILENAME);
        List<Byte> sBox = reader.readSbox(stream);
        DistributionCalculator calculator = new DistributionCalculator();
        int[][] distribution = calculator.calculate(sBox);
        DistributionDisplayer displayer = new DistributionDisplayer();
        displayer.display(distribution);
    }

}
