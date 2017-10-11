import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class QuoteList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class QuoteList
{
    // instance variables - replace the example below with your 
private List<Quote>  lqQuoteList = new ArrayList();
    /**
     * Constructor for objects of class QuoteList
     */
    public QuoteList()
    {
        // initialise instance variables
      
    }

    public void add(Quote q) {
     this.lqQuoteList.add(q);   
    }
  
}
