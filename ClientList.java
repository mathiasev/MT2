import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * ClientList
 *
 * @author u360264
 * @version 20171007
 */
public class ClientList
{
    public static final String CLIENT_FILE_PATH = "Customers.txt";
    public static final String TEXTSEPERATOR = ",";
    public static final String ORBITSEPERATOR = "\\|";

    // instance variables - replace the example below with your own
    private BufferedReader brReader;
    private List<Client> lsClientList = new ArrayList();

    /**
     * Constructor for objects of class ServiceList
     */
    public ClientList()
    { 
        // initialise instance variables
        loadClients();
    }

    public List<String> getCodes() {
       List<String> lsStrings = new ArrayList();
        for(int i = 0; i<lsClientList.size(); i++) {
            lsStrings.add(lsClientList.get(i).getCode());
        }
        return lsStrings;
    }

    public List<Client> getClientList() { return lsClientList;}

    private void loadClients() {

        try {
            brReader =  new BufferedReader(new FileReader(CLIENT_FILE_PATH));
            String sLine;
            while ((sLine = brReader.readLine()) != null)
            {
                try {
                    String[] saLineArray= sLine.split(TEXTSEPERATOR);

                    String sClientCode = saLineArray[0].trim();
                    String sName = saLineArray[1].trim();
                    String sTitle = saLineArray[2].trim();
                    
                    Double dCreditLimit = null;
                    try {
                        dCreditLimit  = Double.parseDouble(saLineArray[3].trim());
                    } catch (Exception e) { System.out.println("Error converting price"); }

                    Client cClient = new Client(sClientCode, sName, sTitle,dCreditLimit);    
                    lsClientList.add(cClient);
                } catch (Exception e) 
                {
                    System.out.println("Failed to add client, skipping to next");   
                }
            }
            brReader.close();

        }
        catch (IOException e) {
            System.out.println("File Not Found");
        }
    }
}
