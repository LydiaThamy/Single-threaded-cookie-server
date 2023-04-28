package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cookie {

    List<String> cookieList = new ArrayList<>();
    File file = null;

    // open cookie file
    public void openFile(String fileName) throws IOException {
        file = new File("c:\\Data" + File.separator + fileName);
        if (file.exists()) {

            // read file
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            
            while ((line = br.readLine()) != null) {
                cookieList.add(line);
            }

            br.close();
            fr.close();
        } else {
            System.out.println(fileName + " file does not exist");
        }
    }

    public String getCookie() {
        return cookieList.get((int)(Math.random() * cookieList.size()));
    }
}

