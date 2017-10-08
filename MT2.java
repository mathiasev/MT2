import java.awt.*;
import javax.swing.*;

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
        init();

    }
    
    private void init() {
             //1. Create the frame.
JFrame frame = new JFrame("FrameDemo");

//2. Optional: What happens when the frame closes?
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//3. Create components and put them in the frame.
//...create emptyLabel...
    DefaultListModel model = new DefaultListModel();
    JList list = new JList(model);


String sFrameText = "";
 for(Service s: slServiceList.getServiceList()) {
            if(s.isInMenu())
            {
                String sLine = String.format("%8s : %s", s.getName(), s.getDescription());
                System.out.println(sLine);
                model.addElement(sLine);
               //sFrameText +="\n" + sLine;
            }
        }
        
        
JLabel emptyLabel = new JLabel(sFrameText);
frame.getContentPane().add(list, BorderLayout.CENTER);

//4. Size the frame.
frame.pack();

//5. Show it.
frame.setVisible(true);
       

        Quote q = new Quote();
        WebQuote wq = new WebQuote(q);   
    }
}
