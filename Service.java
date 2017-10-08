
/**
 *  Service.
 *
 * @author u3160264 (Mathias Everson)
 * @version 20171007
 */
public class Service
{
    // instance variables - replace the example below with your own
    private String sName;
    private String sDescription;
    private Double dPrice;
    private String[] saOrbit;
    private Boolean bAskOrbit;
    private Boolean bManned;
    private Boolean bShowMenu;

    /**
     * Constructor for objects of class Service
     */
    public Service()
    {
        // initialise instance variables

    }
    
    public Service(String sName, String sDescription, Double dPrice, String[] saOrbit, String sManned) {
        this.sName = sName;
        this.sDescription = sDescription;
        this.dPrice = dPrice;
        this.saOrbit = saOrbit;
        this.bAskOrbit = (saOrbit.length > 1) ? true : false;
        this.bManned = (sManned.equals("M")) ? true : false;
        this.bShowMenu = (sManned.equals("N")) ? false: true;
        
    }
    
    public Boolean isInMenu() {
        return this.bShowMenu;
    }
    
    public Double getPrice() {
     return this.dPrice;   
    }
    
    public String getDescription() {
        return this.sDescription;
    }
    
    public String getName() {
        return this.sName;
    }
}
