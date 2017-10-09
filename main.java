
/**
 * Main Program Wrapper.
 *
 * @author u3160264 (Mathias Everson)
 * @version 20171007
 */
public class main
{
    public static void main(String[] args)
    {
        System.out.println("\u000c");
        // initialise instance variables
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
MT2 mt2 = new MT2();

    }
}
