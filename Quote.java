/**
 * Quote Class for Space Y Quoting System
 *
 * @author u3160264 (Mathias Everson)
 * @version 20170919
 */
public class Quote
{
    private static final double NITROGEN_COST = 180000.00;
    private static final double INSURANCE_COST = 97100;
    private static final double DISCOUNT_PERCENT = 0.02;
    private static final double NESA_TAX_RATE = 0.004;
    private static final double TAX_RATE = 0.03;

    private Service scServiceCode;
    private Client cClient;
    private int iLaunches           = 0;                    //TOSET
    private String sOrbitLevel      = "LEO";                //TOSET
    private boolean bNitrogen       = false;                //TOSET
    private boolean bInsurance      = false;                //TOSET
    private double dPayloadValue    = 0.0;                  //TOSET

    private double dServiceCost     = 0.0;                  //AUTO FROM SERVICE
    private double dNitrogenCost    = 0.0;                  //SET FROM LAUNCH * COST

    private double dNESACost        = 0.0;
    private double dTax             = 0.0;
    private double dInsuranceCost   = 0.0;

    private double dDiscount        = 0.0;

    private double dNettCost        = 0.0;
    private double dGrossCost       = 0.0;
    private double dInvoiceCost     = 0.0;

    /**
     * Constructor for objects of class Quote
     */
    public Quote()
    {

    }

    /**
     * Setters for Values
     */
    public void setLaunches(int iLaunches) {this.iLaunches = iLaunches; }

    public void setClient(Client cClient) {this.cClient = cClient;}
    
    public void setService(Service scServiceCode) { this.scServiceCode = scServiceCode;}

    public void setOrbitLevel(String sOrbitLevel) { this.sOrbitLevel = sOrbitLevel;}

    public void setNitrogen(boolean bNitrogen) {this.bNitrogen = bNitrogen;}

    public void setInsurance(boolean bInsurance) {this.bInsurance = bInsurance;}

    public void setPayloadValue(double dPayloadValue) {this.dPayloadValue = dPayloadValue;}

    /**
     * To be called before getting any value;
     */
    public void calculate() {
        // Calculate Service Cost
        this.dServiceCost = this.scServiceCode.getCost() * this.iLaunches;

        // Calculate Nitrogen Cost
        this.dNitrogenCost = (bNitrogen) ? this.iLaunches * NITROGEN_COST : 0;

            // Calculate Discount

        this.dDiscount = (this.iLaunches > 4) ? (-1 *(this.dServiceCost * DISCOUNT_PERCENT)) : 0;

        
        // Set Gross Cost 
        this.dGrossCost = this.dServiceCost + this.dNitrogenCost + this.dDiscount;
        
     // Calulate Tax
        this.dTax = (isTax())? this.dGrossCost * TAX_RATE: 0;

         // Set Nett Cost
        this.dNettCost = this.dGrossCost + this.dTax;
        
        
        // Calculate Insurance Cost
        int iNearestMillion = (int)(Math.ceil(this.dPayloadValue/1000000));
        this.dInsuranceCost = (bInsurance) ? (iNearestMillion) * INSURANCE_COST : 0;


   
        //Calculate NESA Tax
         if("GTO".equals(this.sOrbitLevel.toUpperCase())) {
             
        System.out.println(this.sOrbitLevel + " * " + NESA_TAX_RATE);
            this.dNESACost = this.dGrossCost * NESA_TAX_RATE;}
            else { this.dNESACost = 0.0;
            }

      
       
        this.dInvoiceCost = this.dNettCost + this.dInsuranceCost + this.dNESACost;
    }

    /**
     * Getters for values
     */
    public double getNitrogenUnitCost() {return this.NITROGEN_COST;}
    
    public Client client() {return this.cClient;}
    public Service service() { return this.scServiceCode;}
    
    public double getInvoiceCost() {return this.dInvoiceCost;}

    public double getPayloadValue() {return this.dPayloadValue;}

    public double getLaunches() {return (double)this.iLaunches;}

    public String getName() { return this.scServiceCode.getName();}

    public double getBasePrice() { return this.scServiceCode.getCost();}

    public double getServiceCost() { return this.dServiceCost;}

    public boolean isNESA() { return (this.sOrbitLevel.equals("GTO")) ? true :  false;}

    public boolean isNitrogen() { return (this.dNitrogenCost > 0) ? true : false;}

    public double getNitrogenCost()   {return this.dNitrogenCost;}

    public double getNESACost()       {return this.dNESACost;}

    public boolean isTax() {return (this.scServiceCode.isManned()) ? false: true;}

    public double getTax()             {return this.dTax;}

    public boolean isInsurance() {return this.bInsurance;}

    public double getInsuranceCost()  {return this.dInsuranceCost;}

    public boolean isDiscount() {return (this.iLaunches > 4) ? true : false;}

    public double getDiscount()        {return this.dDiscount;}

    public double getNettCost()        {return this.dNettCost;}

    public double getGrossCost()       {return this.dGrossCost;}
}

