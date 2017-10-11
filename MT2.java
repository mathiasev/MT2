/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import java.util.List;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;

public class MT2 extends JFrame {
    final static int SCALING = 2;
    final static Color BG_COLOR = new Color(214,217,223);
    final static Font FONT = new Font("sansserif",0,12*SCALING);
    final static int ROW_HEIGHT = 40*SCALING;
    
    private JLabel SetClientCode_Label, SetOrbitLevel_Label, SetServiceCode_Label, clientLabel, codeLabel,orbitLabel,launchCountLabel;
    private JRadioButton radiobutton1, radiobutton2, radiobutton3;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton button1;
    private JPanel panel1;
    private List<String> lsServiceStrings, lsClientStrings;
    private QuoteList qlQuotes = new QuoteList();
    private Quote tempQuote = new Quote();
    private ServiceList serviceList = new ServiceList();
    private ClientList clientList = new ClientList();
    private List<Client> clClientList;
    private List<Service> slServiceList; 
    private JComboBox combobox1, combobox2;
    private int y = 10*SCALING;
    private JSpinner launchCount;

    //Constructor 
    public MT2 (){

        this.setTitle("SpaceY Quoting System");
        this.setSize(500*SCALING,400*SCALING);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500*SCALING,400*SCALING));
        contentPane.setBackground(BG_COLOR);
        
        slServiceList =serviceList.getServiceList();
        lsServiceStrings = serviceList.getNames();
        clClientList = clientList.getClientList();
        lsClientStrings = clientList.getCodes();
        
        SetClientCode_Label = new JLabel("Client Code");
        SetClientCode_Label.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        SetClientCode_Label.setBackground(BG_COLOR);
        SetClientCode_Label.setFont(FONT);
        SetClientCode_Label.setVisible(true);
       
        combobox1 = new JComboBox(lsClientStrings.toArray());
        combobox1.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
        combobox1.setBackground(BG_COLOR);
        combobox1.setForeground(new Color(0,0,0));
        combobox1.setEnabled(true);
        combobox1.setFont(FONT);
        combobox1.setVisible(true);
        combobox1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    submitClient(evt);
                }
            });

        clientLabel = new JLabel();
        clientLabel.setBounds(240*SCALING,y,250*SCALING,30*SCALING);
        clientLabel.setBackground(BG_COLOR);
        clientLabel.setFont(FONT);
        clientLabel.setVisible(false);
            
        y +=ROW_HEIGHT;
        
        SetServiceCode_Label = new JLabel("Service Code");
        SetServiceCode_Label.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        SetServiceCode_Label.setBackground(BG_COLOR);
        SetServiceCode_Label.setFont(FONT);
        SetServiceCode_Label.setEnabled(true);
        SetServiceCode_Label.setVisible(true);
        
        combobox2 = new JComboBox(lsServiceStrings.toArray());
        combobox2.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
        combobox2.setBackground(BG_COLOR);
        combobox2.setForeground(new Color(0,0,0));
        combobox2.setEnabled(true);
        combobox2.setFont(FONT);
        combobox2.setVisible(true);
        combobox2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    submitCode(evt);
                }
            });

       codeLabel = new JLabel();
       codeLabel.setBounds(240*SCALING,y,250*SCALING,30*SCALING);
       codeLabel.setBackground(BG_COLOR);
       codeLabel.setFont(FONT);
       codeLabel.setVisible(false);

              
        y +=ROW_HEIGHT;
        
         launchCountLabel = new JLabel("Launch Count");
        launchCountLabel.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        launchCountLabel.setFont(FONT);
        launchCountLabel.setBackground(BG_COLOR);
        launchCountLabel.setForeground(new Color(0,0,0));
       
        launchCount = new JSpinner(new SpinnerNumberModel(1, 1, 15,1));
        launchCount.setBounds(120*SCALING, y, 100*SCALING, 30*SCALING);
        launchCount.setFont(FONT);
        launchCount.setBackground(BG_COLOR);
        
        y+=ROW_HEIGHT;
        
        SetOrbitLevel_Label = new JLabel("Orbit Level");
        SetOrbitLevel_Label.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        SetOrbitLevel_Label.setBackground(BG_COLOR);
        SetOrbitLevel_Label.setEnabled(true);
        SetOrbitLevel_Label.setVisible(true);
        SetOrbitLevel_Label.setFont(FONT);
        
        radiobutton1 = new JRadioButton();
        radiobutton1.setBackground(BG_COLOR);
        radiobutton1.setForeground(new Color(0,0,0));
        radiobutton1.setEnabled(true);
        radiobutton1.setFont(FONT);
        radiobutton1.setText("LEO");
        radiobutton1.setVisible(false);
        buttonGroup.add(radiobutton1);

        radiobutton2 = new JRadioButton();
        radiobutton2.setBackground(BG_COLOR);
        radiobutton2.setForeground(new Color(0,0,0));
        radiobutton2.setEnabled(true);
        radiobutton2.setFont(FONT);
        radiobutton2.setText("GTO");
        radiobutton2.setVisible(false);
        buttonGroup.add(radiobutton2);

        radiobutton3 = new JRadioButton();
        radiobutton3.setBackground(BG_COLOR);
        radiobutton3.setForeground(new Color(0,0,0));
        radiobutton3.setEnabled(true);
        radiobutton3.setFont(FONT);
        radiobutton3.setText("CSO");
        radiobutton3.setVisible(false);
        buttonGroup.add(radiobutton3);

        orbitLabel =  new JLabel();
        orbitLabel.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
        orbitLabel.setFont(FONT);
        orbitLabel.setBackground(BG_COLOR);
        orbitLabel.setForeground(new Color(0,0,0));
        

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(0*SCALING,0*SCALING,500*SCALING,400*SCALING);
        panel1.setBackground(BG_COLOR);
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(FONT);
        panel1.setVisible(true);

        //adding components to contentPane panel
       // panel1.add(button1);

       panel1.add(SetClientCode_Label);
        panel1.add(combobox1);
        panel1.add(clientLabel);
        panel1.add(SetServiceCode_Label);
        panel1.add(combobox2);
        panel1.add(codeLabel);
        panel1.add(launchCountLabel);
        panel1.add(launchCount);
        panel1.add(SetOrbitLevel_Label);
        panel1.add(radiobutton1);
        panel1.add(radiobutton2);
        panel1.add(radiobutton3);
        panel1.add(orbitLabel);
        
        contentPane.add(panel1);
        

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void submitClient(ActionEvent evt) {
     tempQuote.setClient(clClientList.get(combobox1.getSelectedIndex()));  
    clientLabel.setText(tempQuote.client().getName());
    clientLabel.setVisible(true);
    }
    
    //Method actionPerformed for button1
    private void submitCode(ActionEvent evt) {
       y = SetOrbitLevel_Label.getY();
        radiobutton1.setVisible(false);
        radiobutton2.setVisible(false);
        radiobutton3.setVisible(false);
        orbitLabel.setVisible(false);
        
        tempQuote.setService(slServiceList.get(combobox2.getSelectedIndex()));
        codeLabel.setText(tempQuote.service().getDescription());
       codeLabel.setVisible(true);

        if(tempQuote.service().askOrbit()) {
            for (int i = 0; i<tempQuote.service().getOrbits().length; i++) {

                switch(tempQuote.service().getOrbits()[i]) {
                    case "LEO":
                    radiobutton1.setVisible(true);
                    radiobutton1.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
                    y+=30*SCALING;
                    break;

                    case "GTO":
                    radiobutton2.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
                    radiobutton2.setVisible(true);
                    y+=30*SCALING;
                    break;

                    case "CSO":
                    radiobutton3.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
                    radiobutton3.setVisible(true);
                    y+=30*SCALING;
                    break;

                }
            }
        } else {
            orbitLabel.setText(tempQuote.service().firstOrbit());

            orbitLabel.setVisible(true);
        }

    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MT2();
                }
            });
    }

}