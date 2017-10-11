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
        public static final String SERVICE_FILE_PATH = "ServiceCodes.txt";
        public static final String TEXTSEPERATOR = ",";
        public static final String ORBITSEPERATOR = "\\|";
        
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

    public String[] getNames() {
        String[] saStrings = new String[lsServiceList.size()];
        for(int i = 0; i<lsServiceList.size(); i++) {
            saStrings[i] = lsServiceList.get(i).getName();
        }
        return saStrings;
    }
    
    public List<Service> getServiceList() { return lsServiceList;}
    
   private void loadServices() {
       
       try {
          brReader =  new BufferedReader(new FileReader(SERVICE_FILE_PATH));
       String sLine;
         while ((sLine = brReader.readLine()) != null)
        {
            try {
                String[] saLineArray= sLine.split(TEXTSEPERATOR);
            
            String sServiceCode = saLineArray[0].trim();
            String sDescription = saLineArray[1].trim();
            
            Double dPrice = null;
            try {
               dPrice  = Double.parseDouble(saLineArray[2].trim());
            } catch (Exception e) { System.out.println("Error converting price"); }
            
            String sOrbits = saLineArray[3].replaceAll(" ","");
            System.out.println(sOrbits);
            String[] saOrbits = saLineArray[3].split(ORBITSEPERATOR);
            for(int i = 0; i < saOrbits.length; i++) {
                saOrbits[i] = saOrbits[i].trim();
               
            }

      
            String sManned = saLineArray[4].trim();

             Service sService = new Service(sServiceCode, sDescription, dPrice, saOrbits, sManned);    
             lsServiceList.add(sService);
            } catch (Exception e) 
            {
             System.out.println("Failed to add service, skipping to next");   
            }
}
        brReader.close();
       
      
        
    }
    catch (IOException e) {
        System.out.println("File Not Found");
    }
}
}
