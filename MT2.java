
/**
 * MT2 wrapper.
 *
 * @author u3160264
 * @version 20171007
 */
public class MT2
{
    // instance variables - replace the example below with your own
    private ServiceList slServiceList = new ServiceList();
    private ClientList clClientList = new ClientList();
    /**
     * Constructor for objects of class MT2
     */
    public MT2()
    {
        // initialise instance variables
        for(Service s: slServiceList.getServiceList()) {
            if(s.isInMenu())
            {
                String sLine = String.format("%8s : %s", s.getName(), s.getDescription());
                System.out.println(sLine);  
            }
        }

        Quote q = new Quote();
        WebQuote wq = new WebQuote(q);
    }
}
