import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
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
    // instance variables - replace the example below with your own
    private ServiceList slServiceList = new ServiceList();
    private List<Service> lsServiceList = slServiceList.getServiceList();
    private ClientList clClientList = new ClientList();
    private Quote q;
    ButtonGroup orbitButtons = new ButtonGroup();
    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
    String[] saOrbits = new String[2];

    /**
     * Constructor for objects of class MT2
     */
    public MT2()
    {
        q = new Quote();
        init();

    }

    private void init() {
        /*
         *  0. Hide items not yet required
         */
        radioPanel.setVisible(false);

        /*
         * 1. Create Heading
         */
        JLabel jLabelHeading = new JLabel("Space Y Quoting System");
        Font headingFont = new Font("Helvetica",Font.BOLD, 14);
        jLabelHeading.setFont(headingFont);

        //1. Create the frame.
        //JFrame frame = new JFrame("Space Y Quoting System");

        //2. Optional: What happens when the frame closes?
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        /*
         * ListSelectionListener listSelectionListener = new ListSelectionListener() {
         *        public void valueChanged(ListSelectionEvent listSelectionEvent) {
         *            boolean adjust = listSelectionEvent.getValueIsAdjusting();
         *            if (!adjust) {
         *                System.out.println(list.getSelectedValue());
         *            }
         *        }
         *    };
         * list.addListSelectionListener(listSelectionListener);
         */


        JButton submitCode = new JButton("Submit Code");
        submitCode.setActionCommand("Set Service Code");
        submitCode.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if ("Set Service Code".equals(e.getActionCommand())) {
                        // display/center the jdialog when the button is pressed
                        q.setService(lsServiceList.get(list.getSelectedIndex()));

                        getOrbit();

                    }
                }
            });

        //Create the radio buttons.
        //String[] 

        /*
         * Add elements to frame
         */    
        this.getContentPane().add(jLabelHeading, BorderLayout.NORTH);
        this.getContentPane().add(list, BorderLayout.CENTER);
        this.getContentPane().add(submitCode, BorderLayout.SOUTH); 
        this.getContentPane().add(radioPanel, BorderLayout.SOUTH);

        //4. Size the frame.
        this.pack();

        //5. Show it.
        this.setVisible(true);

        WebQuote wq = new WebQuote(q);   
    }

    private void getOrbit() {

        radioPanel.setVisible(true);

        saOrbits = q.getOrbits();
        for (String orbit:saOrbits) {
            JRadioButton orbitButton = new JRadioButton(orbit);
            orbitButtons.add(orbitButton);
            radioPanel.add(orbitButton);
        }

    }
}
