import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class DialogAbout {
    private JPanel panel;
    private JDialog dialog;
    private JButton buttonOK;
    private JLabel labelText;
    private Font font;
    private JMenuItem iconWindows;
    private JMenuItem iconLine;
    private JMenuItem iconNotepad;
    private JLabel labelMain;

    public DialogAbout(Controller controller, JFrame frame) {
        font = new Font("Arial", Font.PLAIN, 14);

        iconWindows = new JMenuItem(new ImageIcon("images/windows-10.png"));
        iconWindows.setBounds(50, 50, 78, 78);;

        Font fontMainLabel = new Font("Arial", Font.PLAIN, 70);
        labelMain = new JLabel("Windows X");
        labelMain.setFont(fontMainLabel);
        labelMain.setBounds(140, 50, 400, 70);
        //labelMain.setBackground(Color.RED);
        labelMain.setForeground(Color.BLUE);
        labelMain.setOpaque(true);
        labelMain.setVisible(true);

        iconLine = new JMenuItem(new ImageIcon("images/line.png"));
        iconLine.setBounds(20, 130, 505, 5);

        iconNotepad = new JMenuItem(new ImageIcon("images/notepad.png"));
        iconNotepad.setBounds(30, 160, 40, 40);

        labelText = new JLabel("<html>Microsoft Windows" +
                                    "<br>My Notepad</html>");
        labelText.setFont(font);
        labelText.setBounds(100, 130, 400, 100);
        labelText.setOpaque(true);
        labelText.setVisible(true);

        buttonOK = new JButton("OK");
        buttonOK.setBounds(450, 430, 90, 36);
        //buttonOK.setFont(font);
        buttonOK.setContentAreaFilled(false);
        buttonOK.addActionListener(controller);
        buttonOK.setActionCommand("OK_About");

        //---------------Container----------------------
        panel = new JPanel();
        panel.setSize(570, 520);
        panel.setLayout(null);

        panel.add(iconWindows);
        panel.add(labelMain);
        panel.add(iconLine);
        panel.add(iconNotepad);
        panel.add(labelText);
        panel.add(buttonOK);

        dialog = new JDialog(frame, "About Notepad", true);
        dialog.setSize(570, 520);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(null);
        dialog.add(panel);
    }

    public void showAboutDialog(boolean isOpen) {
        dialog.setVisible(isOpen);
    }
}
