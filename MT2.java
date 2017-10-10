import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * MT2 wrapper.
 *
 * @author u3160264
 * @version 20171007
 */
public class MT2 extends JFrame
{
    private List<Quote> quoteList = new ArrayList();
  
    /**
     * Constructor for objects of class MT2
     */
    public MT2()
    {
JPanel mainPanel = new JPanel(new GridBagLayout());
        
        JButton newQuoteButton = new JButton("New Quote");
        newQuoteButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                   QuoteForm form = new QuoteForm();
                   add(form);
                }
            });
            
            mainPanel.add(newQuoteButton);
            this.add(mainPanel);
            
        this.pack();

        //5. Show it.
        this.setVisible(true);
        

    }

    public void addQuote(Quote qQuote) {
     quoteList.add(qQuote);
     generateList();
    }
    
    public void generateList() {
         DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String sFrameText = "";
        for(Quote q:quoteList) {
            
                model.addElement(q.getName());

            
        }

    }


}
