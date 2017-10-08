import java.io.*;
import java.util.List;
import java.util.ArrayList;


/**
 * ServiceList
 *
 * @author u360264
 * @version 20171007
 */
public class ServiceList
{
        public static final String SERVICE_FILE_PATH = "C:\\Users\\Mathias Everson\\OneDrive - University of Canberra\\Semester 2\\Software Technology 1\\MT2\\ServiceCodes.txt";
        public static final String TEXTSEPERATOR = ",";
        
    // instance variables - replace the example below with your own
    private BufferedReader brReader;
    private List<Service> lsServiceList = new ArrayList();

    /**
     * Constructor for objects of class ServiceList
     */
    public ServiceList()
    {
        // initialise instance variables
        loadServices();
    }

    public List<Service> getServiceList() { return lsServiceList;}
    
   private void loadServices() {
       
       try {
          brReader =  new BufferedReader(new FileReader(SERVICE_FILE_PATH));
       String sLine;
         while ((sLine = brReader.readLine()) != null)
        {
            String sServiceCode = saLineArray[0].trim();
            String sDescriptionn = saLinenArray[1].trim();
                 try {
            Double dPrivce = Double.parseDouble(saLine[2].trim());
                 } catch (Exception e) { System.out.println("Error converting price"); }
                         //System.out.println(sPersonString);
             String[] saLineArray= sLine.split(TEXTSEPERATOR);
             Service sService = new Service(saLineArray[0]);    
             lsServiceList.add(sService);
}
        brReader.close();
       
      
        
    }
    catch (IOException e) {
        System.out.println("File Not Found");
    }
}
}
