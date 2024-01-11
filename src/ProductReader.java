import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Display column headers
                System.out.printf("%-8s%-15s%-30s%-8s%n", "ID#", "Name", "Description", "Cost");
                System.out.println("=========================================================");

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    // echo to screen
                    // Split the line into individual data elements
                    String[] data = rec.split(", ");

                    // Display the data in a formatted columnar layout
                    System.out.printf("%-8s%-15s%-30s%-8s%n", data[0], data[1], data[2], data[3]);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
