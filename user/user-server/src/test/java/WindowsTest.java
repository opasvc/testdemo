import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowsTest extends WindowAdapter implements ActionListener {


    JFrame jFrame = new JFrame("Windows Test"); // Added a title to the JFrame
    JButton okButton = new JButton("确定");
    JTextField jTextField = new JTextField(20); // Added a preferred width for the JTextField
    int tag = 0;

    public static void main(String[] args) {
        WindowsTest windowsTest = new WindowsTest();
        windowsTest.go();
    }

    public void go() {
        // Set a layout manager for the JFrame (e.g., FlowLayout)
        jFrame.setLayout(new FlowLayout());

        // Add the button and text field to the JFrame's content pane
        jFrame.getContentPane().add(jTextField);
        jFrame.getContentPane().add(okButton);

        // Register the ActionListener with the button
        okButton.addActionListener(this);

        // Register the WindowListener to handle window closing events
        jFrame.addWindowListener(this);

        jFrame.setSize(400, 300);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close behavior
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String si = "1";
        jTextField.setText(si);
        tag++;
        System.out.println("Button clicked! Tag: " + tag); // Added some feedback
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("关闭");
        System.exit(0);
    }
}
