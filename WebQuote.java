import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class WebQuote here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WebQuote extends QuoteView
{
    // instance variables - replace the example below with your own
    private Quote quote;
        private List<String> lsInvoiceLines = new ArrayList();
        private String html = "<html><table><thead><th>Quantity</th><th>Item</th><th>Unit Cost</th><th>Total Cost</th></thead><tbody>";
        private String endHTML = "</tbody></table></html>";

    /**
     *  WebQuote constructor
     */
   public WebQuote(Quote qQuote) {
       super(qQuote);
       this.quote = qQuote;
    }
    
    public String getHTML() {
        
          try {  
            String sLaunches = String.format("<tr><td>%,16.2f</td>" +
                                             "<td>%s launches</td><td>$%,16.2f</td>" +
                                             "<td>$%,16.2f</td></tr>",
                                              quote.getLaunches(),
                                              quote.service().getName(),
                                              quote.service().getCost(),
                                              quote.getServiceCost());
            lsInvoiceLines.add(sLaunches);

            if(quote.isNitrogen()) {
                String sNitrogen = String.format("<tr><td>%,16.2f</td><td>Nitrogen Flushes</td><td>$%,16.2f</td><td>$%,16.2f</td></tr>", quote.getLaunches(), quote.getNitrogenUnitCost() ,quote.getNitrogenCost());
                lsInvoiceLines.add(sNitrogen);
            }

             if(quote.isDiscount()){
                String sDiscount = String.format("<tr><td></td>2 percent discount for more than 5 launches</td><td>-$%,16.2f</td><td></td>", -1*quote.getDiscount());
                lsInvoiceLines.add(sDiscount);
            }
          
            String sGrossCost = String.format("<tr><td><td></td><td><strong style=\"text-align:right\">Gross Launch Cost</strong></td><td>$%,16.2f</td></tr>", quote.getGrossCost());
              lsInvoiceLines.add(sGrossCost);
                        
            if(quote.isTax()){  
               String sTax = String.format("<tr><td><td></td><td><strong style=\"text-align:right\">Tax</strong></td><td>$%,16.2f</td></tr>", quote.getTax());
              lsInvoiceLines.add(sTax);
            }

               String sNettLaunch = String.format("<tr><td><td></td><td><strong style=\"text-align:right\">Nett Launch Cost</strong></td><td>$%,16.2f</td></tr>", quote.getNettCost());
              lsInvoiceLines.add(sNettLaunch);
              
            
           
            
            if(quote.isInsurance()) {
                String sInsurance = String.format("<tr><td>1</td><td>Insurance for payload</td><td></td><td>$%,16.2f</td></tr>", quote.getInsuranceCost());
                lsInvoiceLines.add(sInsurance);
            }
           

            if (quote.isNESA()){  
                String sNESA = String.format("<tr><td></td><td></td><td><strong style=\"text-align:right\">NESA tax for GTO</strong></td><td>$%,16.2f</td></tr>",quote.getNESACost());
                lsInvoiceLines.add(sNESA);
            }
            

               String sTotal = String.format("<tr><td colspan=\"4\"></td></tr><tr><td></td><td></td><td><strong style=\"text-align:right\">Total Amount Due</strong></td><td>$%,16.2f</td></tr>",quote.getInvoiceCost());
               lsInvoiceLines.add(sTotal);
            }
        catch (Exception e) {
              e.printStackTrace();
                        //canvas.drawString("Error printing quote. Please try again.", 30, 60);
        }
        

        
        for (String sLineItem : lsInvoiceLines) {
           html +=sLineItem;
        }
        
        html +=endHTML;
        //html = "<html><style>.invoice-box{max-width: 800px; margin: auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, .15); font-size: 16px; line-height: 24px; font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; color: #555;}.invoice-box table{width: 100%; line-height: inherit; text-align: left;}.invoice-box table td{padding: 5px; vertical-align: top;}.invoice-box table tr td:nth-child(2){text-align: right;}.invoice-box table tr.top table td{padding-bottom: 20px;}.invoice-box table tr.top table td.title{font-size: 45px; line-height: 45px; color: #333;}.invoice-box table tr.information table td{padding-bottom: 40px;}.invoice-box table tr.heading td{background: #eee; border-bottom: 1px solid #ddd; font-weight: bold;}.invoice-box table tr.details td{padding-bottom: 20px;}.invoice-box table tr.item td{border-bottom: 1px solid #eee;}.invoice-box table tr.item.last td{border-bottom: none;}.invoice-box table tr.total td:nth-child(2){border-top: 2px solid #eee; font-weight: bold;}@media only screen and (max-width: 600px){.invoice-box table tr.top table td{width: 100%; display: block; text-align: center;}.invoice-box table tr.information table td{width: 100%; display: block; text-align: center</style><div class=\"invoice-box\"> <table cellpadding=\"0\" cellspacing=\"0\"> <tr class=\"top\"> <td colspan=\"2\"> <table> <tr> <td class=\"title\"> <img src=\"https://www.sparksuite.com/images/logo.png\" style=\"width:100%; max-width:300px;\"> </td><td> Invoice #: 123<br>Created: January 1, 2015<br>Due: February 1, 2015 </td></tr></table> </td></tr><tr class=\"information\"> <td colspan=\"2\"> <table> <tr> <td> Sparksuite, Inc.<br>12345 Sunny Road<br>Sunnyville, CA 12345 </td><td> Acme Corp.<br>John Doe<br>john@example.com </td></tr></table> </td></tr><tr class=\"heading\"> <td> Payment Method </td><td> Check # </td></tr><tr class=\"details\"> <td> Check </td><td> 1000 </td></tr><tr class=\"heading\"> <td> Item </td><td> Price </td></tr><tr class=\"item\"> <td> Website design </td><td> $300.00 </td></tr><tr class=\"item\"> <td> Hosting (3 months) </td><td> $75.00 </td></tr><tr class=\"item last\"> <td> Domain name (1 year) </td><td> $10.00 </td></tr><tr class=\"total\"> <td></td><td> Total: $385.00 </td></tr></table> </div></html>";
       System.out.println(html);
        return html;
        
    }
}
