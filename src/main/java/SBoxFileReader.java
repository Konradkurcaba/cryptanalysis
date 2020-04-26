import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SBoxFileReader
{

    public List<Byte> readSbox(InputStream aInputStream) throws IOException
    {
        List<Byte> readSBox = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(aInputStream));
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] splitLine = line.split(",");
            Arrays.stream(splitLine).map(Byte::valueOf).forEach(readSBox::add);
        }
        return readSBox;
    }


}
