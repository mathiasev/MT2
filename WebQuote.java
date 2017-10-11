
/**
 * Write a description of class WebQuote here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WebQuote extends QuoteView
{
    // instance variables - replace the example below with your own
    private Quote qQuote;

    /**
     *  WebQuote constructor
     */
   public WebQuote(Quote qQuote) {
       super(qQuote);
       this.qQuote = qQuote;
    }
    
    public String getHTML() {
        String html = "<html><table><thead><th>SpaceY Quoting</th></thead>"+
                      "<tbody><tr><td>Service</td><td>" + qQuote.service().getName() + "</td></tr></tbody>" +
                      "</table></html>";
        return html;
        
    }
}
