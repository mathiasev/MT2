
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
    /**
     * Constructor Quotes
     */
    public Quote()
    {
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
    
    public String[] getOrbits() {
        return this.sService.getOrbits();
    }
}
