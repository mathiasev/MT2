import java.util.List;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
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

    private JLabel SetClientCode_Label,
    SetOrbitLevel_Label,
    SetServiceCode_Label,
    clientLabel,
    codeLabel,
    orbitLabel,
    launchCountLabel,
    nitroLabel,
    insuranceLabel,
    htmlLabel = new JLabel();
    private JRadioButton radiobutton1,
    radiobutton2,
    radiobutton3;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton button1;
    private JPanel panel1, panel2;
    private List<String> lsServiceStrings, lsClientStrings;
    private QuoteList qlQuotes = new QuoteList();
    private Quote tempQuote = new Quote();
    private ServiceList serviceList = new ServiceList();
    private ClientList clientList = new ClientList();
    private List<Client> clClientList;
    private List<Service> slServiceList; 
    private JComboBox combobox1, combobox2;
    private int y = 10*SCALING;
    private JSpinner launchCount,
    insuranceValue;
    private JToggleButton   nitroButton,
    insuranceButton;
    private boolean bGoodToGo = false;

    /**
     * MT2 Constructor
     *
     */
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
        SetClientCode_Label.setFont(FONT);
        SetClientCode_Label.setVisible(true);

        combobox1 = new JComboBox(lsClientStrings.toArray());
        combobox1.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
        combobox1.setFont(FONT);
        combobox1.setVisible(true);
        combobox1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    submitClient(evt);
                }
            });

        clientLabel = new JLabel();
        clientLabel.setBounds(240*SCALING,y,250*SCALING,30*SCALING);
        clientLabel.setFont(FONT);
        clientLabel.setVisible(false);

        y +=ROW_HEIGHT;

        SetServiceCode_Label = new JLabel("Service Code");
        SetServiceCode_Label.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        SetServiceCode_Label.setFont(FONT);
        SetServiceCode_Label.setEnabled(true);
        SetServiceCode_Label.setVisible(true);

        combobox2 = new JComboBox(lsServiceStrings.toArray());
        combobox2.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
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
        codeLabel.setFont(FONT);
        codeLabel.setVisible(false);

        y +=ROW_HEIGHT;
        launchCountLabel = new JLabel("Launch Count");
        launchCountLabel.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        launchCountLabel.setFont(FONT);

        launchCount = new JSpinner(new SpinnerNumberModel(1, 1, 15,1));
        launchCount.setBounds(120*SCALING, y, 100*SCALING, 30*SCALING);
        launchCount.setFont(FONT);

        y+=ROW_HEIGHT;

        SetOrbitLevel_Label = new JLabel("Orbit Level");
        SetOrbitLevel_Label.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        SetOrbitLevel_Label.setVisible(false);
        SetOrbitLevel_Label.setFont(FONT);
        
        ActionListener listener = new ActionListener(){
      public void actionPerformed(ActionEvent evt) {
        System.out.println(((JRadioButton)evt.getSource()).getText());
      }
    };

        radiobutton1 = new JRadioButton();
        radiobutton1.setBackground(BG_COLOR);
        radiobutton1.setEnabled(true);
        radiobutton1.setFont(FONT);
        radiobutton1.setText("LEO");
        radiobutton1.setVisible(false);
        radiobutton1.addActionListener(listener);
        buttonGroup.add(radiobutton1);

        radiobutton2 = new JRadioButton();
        radiobutton2.setBackground(BG_COLOR);
        radiobutton2.setEnabled(true);
        radiobutton2.setFont(FONT);
        radiobutton2.setText("GTO");
        radiobutton2.setVisible(false);
        radiobutton2.addActionListener(listener);
        buttonGroup.add(radiobutton2);

        radiobutton3 = new JRadioButton();
        radiobutton3.setEnabled(true);
        radiobutton3.setBackground(BG_COLOR);
        radiobutton3.setFont(FONT);
        radiobutton3.setText("CSO");
        radiobutton3.setVisible(false);
        radiobutton3.addActionListener(listener);
        buttonGroup.add(radiobutton3);

        orbitLabel =  new JLabel();
        orbitLabel.setBounds(120*SCALING,y,100*SCALING,30*SCALING);
        orbitLabel.setFont(FONT);

        y+=ROW_HEIGHT;

        nitroLabel = new JLabel("Nitrogen Flush");
        nitroLabel.setBounds(10*SCALING,y,100*SCALING,30*SCALING);
        nitroLabel.setFont(FONT);
        nitroLabel.setVisible(false);

        nitroButton  = new JToggleButton("Include Nitrogen"); 
        nitroButton.setVisible(false);
        nitroButton.setFont(FONT);
        nitroButton.setBounds(120*SCALING, y, 100*SCALING,30*SCALING);

        y+=ROW_HEIGHT;

        insuranceLabel = new JLabel("Insurance");
        insuranceLabel.setBounds(10*SCALING, y, 100*SCALING, 30*SCALING);
        insuranceLabel.setFont(FONT);
        insuranceLabel.setVisible(false);

        insuranceButton  = new JToggleButton("Include Insurance"); 
        insuranceButton.setVisible(false);
        insuranceButton.setFont(FONT);
        insuranceButton.setBounds(120*SCALING, y, 100*SCALING,30*SCALING);
        insuranceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if(insuranceButton.isSelected()) {
                        insuranceValue.setVisible(true);
                    } 
                    else {insuranceValue.setVisible(false);}
                }
            });

        insuranceValue = new JSpinner(new SpinnerNumberModel(0,0,1000000000000000.00, 100000));
        insuranceValue.setBounds(240*SCALING, y, 100*SCALING, 30*SCALING);
        insuranceValue.setFont(FONT);
        insuranceValue.setVisible(false);
        insuranceValue.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    setPayloadValue(e);
                }
            });

            
        y+=ROW_HEIGHT;
        y+=ROW_HEIGHT;

        button1 = new JButton("Generate Web Quote");
        button1.setBounds(10*SCALING, y, 200*SCALING,40*SCALING);
        button1.setFont(FONT);
        button1.setVisible(false);
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if(bGoodToGo) {
                        qlQuotes.add(tempQuote);
                        WebQuote webQ = new WebQuote(tempQuote);
                        htmlLabel.setText(webQ.getHTML());
                        panel1.setVisible(false);
                        panel2.setVisible(true);
                    } 
                    else {System.out.println("Error Somewhere");}
                }
            });

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(0*SCALING,0*SCALING,500*SCALING,400*SCALING);
        panel1.setBackground(BG_COLOR);
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(FONT);
        panel1.setVisible(true);
        
        htmlLabel.setFont(FONT);
        htmlLabel.setBounds(0,0,500*SCALING, 400*SCALING);
        
        panel2 = new JPanel(null);
        panel2.setBorder(BorderFactory.createEtchedBorder(1));
        panel2.setBounds(0*SCALING,0*SCALING,500*SCALING,400*SCALING);
        panel2.setBackground(BG_COLOR);
        panel2.setForeground(new Color(0,0,0));
        panel2.setEnabled(false);
        panel2.setFont(FONT);
        panel2.setVisible(false);
        panel2.add(htmlLabel);
        

        //adding components to contentPane panel
        panel1.add(button1);

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
        panel1.add(nitroLabel);
        panel1.add(nitroButton);
        panel1.add(insuranceLabel);
        panel1.add(insuranceButton);
        panel1.add(insuranceValue);

        
        contentPane.add(panel1);
        contentPane.add(panel2);
        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

  
    private void setPayloadValue(ChangeEvent e) {
        tempQuote.setPayloadValue((Double)insuranceValue.getValue());
    }

    private void submitClient(ActionEvent evt) {
        tempQuote.setClient(clClientList.get(combobox1.getSelectedIndex()));  
        clientLabel.setText(tempQuote.client().getName());
        clientLabel.setVisible(true);
    }

    //Method actionPerformed for button1
    private void submitCode(ActionEvent evt) {
        tempQuote.setService(slServiceList.get(combobox2.getSelectedIndex()));
        codeLabel.setText(tempQuote.service().getDescription());
        codeLabel.setVisible(true);

        if(!tempQuote.service().askOrbit() && tempQuote.service().isManned()) {
            bGoodToGo = true;
            button1.setVisible(true);
        }
        else {
            setOrbit();
            setNitro();
            setInsurance();

        }

    }

    private void setInsurance() {
        if (!tempQuote.service().isManned()) {
            insuranceButton.setVisible(true);
            insuranceValue.setVisible(true);
            insuranceLabel.setVisible(true);
        }
        else {
            insuranceButton.setVisible(false);
            insuranceValue.setVisible(false);
            insuranceLabel.setVisible(false);
        }
    }

    private void setNitro() {
        if (!tempQuote.service().isManned()) {
            nitroButton.setVisible(true);
            nitroLabel.setVisible(true);
        }
        else {
            nitroButton.setVisible(false);
            nitroLabel.setVisible(false);  
        }
    }

    private void setOrbit() {
        SetOrbitLevel_Label.setVisible(true);
        y = SetOrbitLevel_Label.getY();
        int x = 120*SCALING;
        radiobutton1.setVisible(false);
        radiobutton2.setVisible(false);
        radiobutton3.setVisible(false);
        orbitLabel.setVisible(false);

        if(tempQuote.service().askOrbit()) {
            for (int i = 0; i<tempQuote.service().getOrbits().length; i++) {
                switch(tempQuote.service().getOrbits()[i]) {
                    case "LEO":
                    radiobutton1.setVisible(true);
                    radiobutton1.setBounds(x,y,60*SCALING,30*SCALING);
                    x+=70*SCALING;
                    break;

                    case "GTO":
                    radiobutton2.setBounds(x,y,60*SCALING,30*SCALING);
                    radiobutton2.setVisible(true);
                    x+=70*SCALING;
                    break;

                    case "CSO":
                    radiobutton3.setBounds(x,y,60*SCALING,30*SCALING);
                    radiobutton3.setVisible(true);
                    x+=70*SCALING;
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