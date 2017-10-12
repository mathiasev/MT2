import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class QuoteView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class QuoteView
{
    // instance variables - replace the example below with your own
    private Quote quote;
    private Font headingFont = new Font("Arial", Font.BOLD, 20);
    private Font bodyFont = new Font("Consolas", Font.PLAIN, 14);
    private List<String> lsInvoiceLines = new ArrayList();

    /**
     * Constructor for objects of class QuoteView
     */
    public QuoteView(Quote quote)
    {
        // initialise instance variables
        this.quote = quote;
    }

    public void showQuote()
    {
        Canvas canvas = new Canvas("Space Y Quote", 600,800);

        canvas.setForegroundColor(Color.BLUE);
        canvas.fillRectangle(0, 0, 600, 800);
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillRectangle(10, 10, 580, 780);

        canvas.setForegroundColor(Color.BLACK);

        canvas.setFont(headingFont);
        canvas.drawString("Space Y Quote", 30, 30);
        canvas.setFont(bodyFont);

        try {  
            String sLaunches = String.format("%2.0f x %s launches @ $%,16.2f ea     $%,16.2f", quote.getLaunches(), quote.getName(), quote.getBasePrice(),quote.getServiceCost());
            lsInvoiceLines.add(sLaunches);

            if(quote.isNitrogen()) {
                String sNitrogen = String.format("%2.0f x Nitrogen Flushes                         $%,16.2f", quote.getLaunches(), quote.getNitrogenCost(), quote.getNitrogenCost());
                lsInvoiceLines.add(sNitrogen);
            }

             if(quote.isDiscount()){
                String sDiscount = String.format(" 2 perc. Discount for more than 5 launches   -$%,16.2f", -1*quote.getDiscount());
                lsInvoiceLines.add(sDiscount);
            }
      
            lsInvoiceLines.add("\u00B6");      
            String sGrossCost = String.format(" Gross Launch Cost                            $%,16.2f", quote.getGrossCost());
              lsInvoiceLines.add(sGrossCost);
             lsInvoiceLines.add("");  lsInvoiceLines.add("");              
            if(quote.isTax()){  
               String sTax = String.format(" Tax                                          $%,16.2f", quote.getTax());
              lsInvoiceLines.add(sTax);
            }
                        lsInvoiceLines.add("\u00B6");   
               String sNettLaunch = String.format(" Nett Launch Cost                             $%,16.2f", quote.getNettCost());
              lsInvoiceLines.add(sNettLaunch);
              
            
            lsInvoiceLines.add("");
            lsInvoiceLines.add("");
            
            if(quote.isInsurance()) {
                String sInsurance = String.format(" Insurance for payload:                       $%,16.2f", quote.getInsuranceCost());
                lsInvoiceLines.add(sInsurance);
            }
           

            if (quote.isNESA()){  
                String sNESA = String.format(" NESA tax for GTO:                            $%,16.2f",quote.getNESACost());
                lsInvoiceLines.add(sNESA);
            }
            
               lsInvoiceLines.add("\u00B6");

            lsInvoiceLines.add("");
            lsInvoiceLines.add("");
                              lsInvoiceLines.add("\u00B6");
               String sTotal = String.format(" Total Amount Due:                            $%,16.2f",quote.getInvoiceCost());
               lsInvoiceLines.add(sTotal);
               lsInvoiceLines.add("\u00B6");
            }
        catch (Exception e) {
              e.printStackTrace();
                        canvas.drawString("Error printing quote. Please try again.", 30, 60);
        }
        

        int count = 80;
        for (String sLineItem : lsInvoiceLines) {
            if (sLineItem.equals("\u00B6")) {
                canvas.drawLine(60, count, 340, count);
            } else {System.out.println(sLineItem);
                canvas.drawString(sLineItem, 30, count);}

            count = count + 30;
        }

    }
}
