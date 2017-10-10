import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class QuoteForm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class QuoteForm extends JPanel
{
    // instance variables - replace the example below with your own
    // instance variables - replace the example below with your own
    private ServiceList slServiceList = new ServiceList();
    private List<Service> lsServiceList = slServiceList.getServiceList();
    private ClientList clClientList = new ClientList();
    private Quote q = new Quote();
    ButtonGroup orbitButtons = new ButtonGroup();
    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
    String[] saOrbits = new String[2];
            JButton submitCode = new JButton("Submit Code");

    /**
     * Constructor for objects of class QuoteForm
     */
    public QuoteForm()
    {
         lsServiceList = slServiceList.getServiceList();
        // initialise instance variables
       

       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        radioPanel.setVisible(false);

        /*
         * 1. Create Heading
         */
        JLabel jLabelHeading = new JLabel("Space Y Quoting System");
        Font headingFont = new Font("Helvetica",Font.BOLD, 14);
        jLabelHeading.setFont(headingFont);

        //3. Create components and put them in the frame.
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String sFrameText = "";
        for(Service s: lsServiceList) {
            if(s.isInMenu())
            {
                String sLine = String.format("%8s : %s", s.getName(), s.getDescription());
                System.out.println(sLine);
                model.addElement(sLine);

            }
        }

        
        
        submitCode.setEnabled(false);
        submitCode.setVisible(true);
        submitCode.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                     lsServiceList = slServiceList.getServiceList();
                                   System.out.println("ButtonClick for " + list.getSelectedIndex());
                       Service selectedService = lsServiceList.get(list.getSelectedIndex());
                       System.out.println(selectedService);
                       if(selectedService != null) 
                            q.setService(selectedService);

                            getOrbit();

                        
                   
                }
            });
        
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting() == false) {

        if (list.getSelectedIndex() == -1) {
        //No selection, disable fire button.
            submitCode.setEnabled(false);

        } else {
        //Selection, enable the fire button.
            submitCode.setEnabled(true);
        }
    }
}
        });

        

        /*
         * Add elements to frame
         */    
        add(jLabelHeading);
        add(list);
        add(submitCode);

        //5. Show it.
        this.setVisible(true);

    }
    

    private void getOrbit() {

        orbitButtons = new ButtonGroup();
        radioPanel.removeAll();

        saOrbits = q.getOrbits();
        for (int i = 0; i<saOrbits.length; i++) {
            JRadioButton orbitButton = new JRadioButton(saOrbits[i]);
            orbitButtons.add(orbitButton);
            radioPanel.add(orbitButton);
        }
        radioPanel.setVisible(true);
        add(radioPanel);
       
    }
}
