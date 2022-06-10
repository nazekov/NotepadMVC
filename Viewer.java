import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Viewer {

    private JTextArea textArea;
    private JFrame frame;
    private JFileChooser fileChooser;
    private Map<String, Object> content;
    private Font font;
    private DialogFont dialogFont;
    private DialogAbout dialogAbout;


    public Viewer() {
        Controller controller = new Controller(this);
        textArea = new JTextArea();
        font = new Font("Arial", Font.PLAIN, 15);
        textArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JMenuBar menuBar = getMenuBar(controller);

        frame = new JFrame("UN-Notepad-MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setJMenuBar(menuBar);
        frame.add("Center", scrollPane);
        frame.setLocationRelativeTo(null); // размещает по центру экрана монитора
        frame.setVisible(true);
    }


    private JMenuBar getMenuBar(Controller controller) {
        JMenu fileMenu = getFileMenu(controller);
        JMenu editMenu = getEditMenu(controller);
        editMenu.addMenuListener(controller);
        JMenu formatMenu = getFormatMenu(controller);
        JMenu viewMenu = getViewMenu(controller);
        JMenu infoMenu = getInfoMenu(controller);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(viewMenu);
        menuBar.add(infoMenu);

        return menuBar;
    }

    private JMenu getFileMenu(Controller controller) {
        JMenuItem documentMenuItemNew = new JMenuItem("New", new ImageIcon("images/create-large.png"));
        documentMenuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        documentMenuItemNew.addActionListener(controller);
        documentMenuItemNew.setActionCommand("New_File");

        JMenuItem documentMenuItemOpen = new JMenuItem("Open", new ImageIcon("images/open-large.png"));
        documentMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        documentMenuItemOpen.addActionListener(controller);
        documentMenuItemOpen.setActionCommand("Open_File");

        JMenuItem documentMenuItemSave = new JMenuItem("Save", new ImageIcon("images/save-large.png"));
        documentMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        documentMenuItemSave.addActionListener(controller);
        documentMenuItemSave.setActionCommand("Save_File");

        JMenuItem documentMenuItemSaveAs = new JMenuItem("Save as", new ImageIcon("images/save-as-large.png"));
        documentMenuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        documentMenuItemSaveAs.addActionListener(controller);
        documentMenuItemSaveAs.setActionCommand("Save_As_File");

        JMenuItem documentMenuItemPageSettings = new JMenuItem("Page settings", new ImageIcon("images/page-settings.png"));
        documentMenuItemPageSettings.addActionListener(controller);
        documentMenuItemPageSettings.setActionCommand("Page_Settings");

        JMenuItem documentMenuItemPrint = new JMenuItem("Print", new ImageIcon("images/print-large.png"));
        documentMenuItemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        documentMenuItemPrint.addActionListener(controller);
        documentMenuItemPrint.setActionCommand("Print_Document");

        JMenuItem documentMenuItemExit = new JMenuItem("Exit");
        documentMenuItemExit.addActionListener(controller);
        documentMenuItemExit.setActionCommand("Exit");

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(documentMenuItemNew);
        fileMenu.add(documentMenuItemOpen);
        fileMenu.add(documentMenuItemSave);
        fileMenu.add(documentMenuItemSaveAs);
        fileMenu.addSeparator();
        fileMenu.add(documentMenuItemPageSettings);
        fileMenu.add(documentMenuItemPrint);
        fileMenu.addSeparator();
        fileMenu.add(documentMenuItemExit);

        return fileMenu;
    }

    private JMenu getEditMenu(Controller controller) {
        JMenuItem operationUndo = new JMenuItem("Undo", new ImageIcon("images/undo.png"));
        operationUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        operationUndo.addActionListener(controller);
        operationUndo.setActionCommand("Undo");
        controller.sendLinkOperationUndo(operationUndo);

        JMenuItem operationRedo = new JMenuItem("Redo", new ImageIcon("images/redo.png"));
        operationRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        operationRedo.addActionListener(controller);
        operationRedo.setActionCommand("Redo");
        controller.sendLinkOperationRedo(operationRedo);

        JMenuItem operationCut = new JMenuItem("Cut", new ImageIcon("images/cut-large.png"));
        operationCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        operationCut.addActionListener(controller);
        operationCut.setActionCommand("Cut");
        controller.sendLinkOperationCut(operationCut);

        JMenuItem operationCopy = new JMenuItem("Copy", new ImageIcon("images/copy-large.png"));
        operationCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        operationCopy.addActionListener(controller);
        operationCopy.setActionCommand("Copy");
        controller.sendLinkOperationCopy(operationCopy);

        JMenuItem operationPaste = new JMenuItem("Paste", new ImageIcon("images/paste-large.png"));
        operationPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        operationPaste.addActionListener(controller);
        operationPaste.setActionCommand("Paste");
        controller.sendLinkOperationPaste(operationPaste);

        JMenuItem operationRemove = new JMenuItem("Remove", new ImageIcon("images/clear.png"));
        operationRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        operationRemove.addActionListener(controller);
        operationRemove.setActionCommand("Remove");
        controller.sendLinkOperationRemove(operationRemove);

        JMenuItem operationFind = new JMenuItem("Find", new ImageIcon("images/search-large.png"));
        operationFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        operationFind.addActionListener(controller);
        operationFind.setActionCommand("Find");
        controller.sendLinkOperationFind(operationFind);

        JMenuItem operationReplace = new JMenuItem("Replace", new ImageIcon("images/replace.png"));
        operationReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        operationReplace.addActionListener(controller);
        operationReplace.setActionCommand("Replace");

        JMenuItem operationGoTo = new JMenuItem("Go to", new ImageIcon("images/go-to.png"));
        operationGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        operationGoTo.addActionListener(controller);
        operationGoTo.setActionCommand("Go_To");

        JMenuItem operationSelectAll = new JMenuItem("Select All", new ImageIcon("images/select-all-large.png"));
        operationSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        operationSelectAll.addActionListener(controller);
        operationSelectAll.setActionCommand("Select_All");

        JMenuItem operationTimeAndDate = new JMenuItem("Time and Date", new ImageIcon("images/time-date-large.png"));
        operationTimeAndDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        operationTimeAndDate.addActionListener(controller);
        operationTimeAndDate.setActionCommand("Time_And_Date");

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(operationUndo);
        editMenu.add(operationRedo);
        editMenu.addSeparator();
        editMenu.add(operationCut);
        editMenu.add(operationCopy);
        editMenu.add(operationPaste);
        editMenu.add(operationRemove);
        editMenu.addSeparator();
        editMenu.add(operationFind);
        editMenu.addSeparator();
        editMenu.add(operationReplace);
        editMenu.add(operationGoTo);
        editMenu.add(operationSelectAll);
        editMenu.add(operationTimeAndDate);

        return editMenu;
    }

    private JMenu getFormatMenu(Controller controller) {
        JMenuItem formatWordWrap = new JMenuItem("Word wrap", new ImageIcon("images/word-wrap.png"));
        formatWordWrap.addActionListener(controller);
        formatWordWrap.setActionCommand("Word_Wrap");

        JMenuItem formatFont = new JMenuItem("Font", new ImageIcon("images/font-large.png"));
        formatFont.addActionListener(controller);
        formatFont.setActionCommand("Font_Set");

        JMenu formatMenu = new JMenu("Format");
        formatMenu.add(formatWordWrap);
        formatMenu.add(formatFont);

        return formatMenu;
    }

    private JMenu getViewMenu(Controller controller) {
        JMenuItem viewStatusPanel = new JMenuItem("Status Panel", new ImageIcon("images/status-bar.png"));
        viewStatusPanel.addActionListener(controller);
        viewStatusPanel.setActionCommand("Status_Panel");

        JMenuItem viewZoom = new JMenuItem("Zoom", new ImageIcon("images/zoom.png"));
        viewZoom.addActionListener(controller);
        viewZoom.setActionCommand("Zoom");

        JMenu viewMenu = new JMenu("View");
        viewMenu.add(viewStatusPanel);
        viewMenu.add(viewZoom);

        return viewMenu;
    }

    private JMenu getInfoMenu(Controller controller) {
        JMenuItem infoHelp = new JMenuItem("Help", new ImageIcon("images/help.png"));
        infoHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        infoHelp.addActionListener(controller);
        infoHelp.setActionCommand("Help");

        JMenuItem infoAbout = new JMenuItem("About", new ImageIcon("images/about.png"));
        infoAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        infoAbout.addActionListener(controller);
        infoAbout.setActionCommand("About");

        JMenu infoMenu = new JMenu("Info");
        infoMenu.add(infoHelp);
        infoMenu.add(infoAbout);

        return infoMenu;
    }

    public void update(String text) {
        textArea.setText(text);
    }

    public File showDialog(String command) {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = 0;
        if (command.equals("Open_File")) {
            returnVal = fileChooser.showOpenDialog(frame);
        } else if (command.equals("Save_File")) {
            returnVal = fileChooser.showSaveDialog(frame);
        } else {
            return null;
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }

    public void showMessageDialog(String message) {
        javax.swing.JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public Map<String, Object> getContent(String command) {
        if (content == null) {
            content = new HashMap<>();
        }

        content.put("text", textArea.getText());
        if (command.equals("Save_File")) {
            content.put("file", showDialog(command));
        } else if (command.equals("Print_Document")) {
            content.put("font", font);
        }
        return content;
    }

    public void copyText() {
        textArea.copy();
    }

    public void pasteText() {
        textArea.paste();
    }

    public void cutText() {
        textArea.cut();
    }

    public void removeText() {
        textArea.replaceSelection("");
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void showFontDialog(Controller controller, boolean isOpenDialog) {
        if (dialogFont == null) {
            dialogFont = new DialogFont(controller, frame);
            controller.setDialogFont(dialogFont);
        }
        dialogFont.showFontDialog(isOpenDialog);
    }

    public void setFontToTextArea(Font font) {
        this.font = font;
        textArea.setFont(this.font);
    }

    public void showAboutDialog(Controller controller, boolean isOpenDialog) {
        if (dialogAbout == null) {
            dialogAbout = new DialogAbout(controller, frame);
            controller.setDialogAbout(dialogAbout);
        }
        dialogAbout.showAboutDialog(isOpenDialog);
    }

//    public String getSelectedText() {
//        return textArea.getSelectedText();
//    }
}
