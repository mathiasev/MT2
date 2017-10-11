
/**
 * Quote Model.
 *
 * @author u3160264 (Mathias Everson)
 * @version 20171007
 */
public class Quote
{
 
private Client cClient;
private Service sService = new Service();
private String sName;
private double dPayloadVal;

    /**
     * Constructor Quotes
     */
    public Quote()
    {
    }
    
    public void setPayloadValue(Double dPayloadVal) {
        this.dPayloadVal = dPayloadVal;
    }
    
    public void setName(String sName) {
     this.sName = sName;   
    }
    
    public String getName() {
     return this.sName;   
    }
    
    public void setService(Service sService) {
        this.sService = sService;
    }
    
    public void setClient(Client cClient){
   this.cClient = cClient;
    }
    
    public Client client() {
     return this.cClient;   
    }
    
    public Service service() {
        return this.sService;
    }
}
