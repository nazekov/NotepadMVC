import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Controller implements ActionListener, MenuListener {

    private Viewer viewer;
    private DialogFont dialogFont;
    private DialogAbout dialogAbout;
    private JMenuItem operationUndo;
    private JMenuItem operationRedo;
    private JMenuItem operationFind;
    private JMenuItem operationCut;
    private JMenuItem operationCopy;
    private JMenuItem operationRemove;
    private JMenuItem operationPaste;
    private JTextArea textArea;
    private boolean isUndoReady;
    private boolean isUndoWorked;

    public Controller(Viewer viewer) {

        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        String textFromFile = "";

        if (command.equals("New_File")) {
            textFromFile = "New_File";
        } else if (command.equals("Open_File")) {
            File file = viewer.showDialog("Open_File");
            if (file != null) {
                textFromFile = readFile(file);
                if (textFromFile == null) {
                    textFromFile = "default 123";
                }
            } else {
                viewer.showMessageDialog("File isn't selected");
            }
        } else if (command.equals("Save_File")) {
            //textFromFile = "Save_File";
            Map<String, Object> content = viewer.getContent("Save_File");

            String text = (String) content.get("text");
            File file = (File) content.get("file");

            if (text != null && file != null) {
                if (saveToFile(file, text)) {
                    viewer.showMessageDialog("File saved succesful!");
                } else {
                    viewer.showMessageDialog("Failed to save");
                }
            } else {
                viewer.showMessageDialog("File isn't selected");
            }
            content.clear();
            content = null;
            return;
        } else if (command.equals("Save_As_File")) {
            textFromFile = "Save_As_File";
        } else if (command.equals("Page_Settings")) {
            textFromFile = "Page_Settings";
        } else if (command.equals("Print_Document")) {
            Map<String, Object> content = viewer.getContent("Print_Document");
            String textFromTextArea = (String) content.get("text");
            Font fontFromTextArea = (Font) content.get("font");

            if (textFromTextArea != null && !textFromTextArea.equals("")) {
                if (printDocument(textFromTextArea, fontFromTextArea)) {
                    viewer.showMessageDialog("Document successfuly printed!");
                } else {
                    viewer.showMessageDialog("Print job did not successfully complete");
                }
            }
            content.clear();
            content = null;
            return;
        } else if (command.equals("Exit")) {
            textFromFile = "Exit";
        } else if (command.equals("Undo")) {
            isUndoWorked = true;

            //textFromFile = "Undo";
        } else if (command.equals("Redo")) {
            //textFromFile = "Redo";
        } else if (command.equals("Cut")) {
            viewer.cutText();
            return;
        } else if (command.equals("Copy")) {
            viewer.copyText();
            return;
        } else if (command.equals("Paste")) {
            viewer.pasteText();
            return;
        } else if (command.equals("Remove")) {
            viewer.removeText();
            return;
        } else if (command.equals("Find")) {
            textFromFile = "Find";
        } else if (command.equals("Replace")) {
            textFromFile = "Replace";
        } else if (command.equals("Go_To")) {
            textFromFile = "Go_To";
        } else if (command.equals("Select_All")) {
            textFromFile = "Select_All";
        } else if (command.equals("Time_And_Date")) {
            textFromFile = "Time_And_Date";
        } else if (command.equals("Word_Wrap")) {
            textFromFile = "Word_Wrap";
        } else if (command.equals("Font_Set")) {
            //textFromFile = "Font";
            viewer.showFontDialog(this, true);
            return;
        } else if (command.equals("Status_Panel")) {
            textFromFile = "Status_Panel";
        } else if (command.equals("Zoom")) {
            textFromFile = "Zoom";
        } else if (command.equals("Help")) {
            textFromFile = "Help";
        } else if (command.equals("About")) {
            //textFromFile = "About";
            viewer.showAboutDialog(this,true);
        } else if(command.equals("OK_About")) {
            viewer.showAboutDialog(this, false);
        } else if (command.equals("Cancel_Font")) {
            viewer.showFontDialog(this, false);
            return;
        } else if (command.equals("OK_Font")) {
            if (dialogFont != null) {
                Font font = dialogFont.getFont();
                viewer.setFontToTextArea(font);
            }

            viewer.showFontDialog(this, false);
            return;
        }

        viewer.update(textFromFile);
    }


    public String readFile(File file) {
        FileReader fileReader = null;
        BufferedReader inputStream = null;
        try {
            fileReader = new FileReader(file);
            inputStream = new BufferedReader(fileReader);

            List<String> list = new ArrayList<String>();

            String line;
            while ((line = inputStream.readLine()) != null) {
                list.add(line);
            }

            String text = String.join("\n", list);
            list.clear();
            list = null;

            return text;
        } catch (FileNotFoundException fne) {
            System.out.println("Error");
            return null;
        } catch (IOException ioe) {
            System.out.println("Error in reading");
            return null;
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                // System.out.println("Error in reading");
                return null;
            }

            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioe) {
                // System.out.println("Error in reading");
                return null;
            }
        }
    }

    private boolean saveToFile(File file, String text) {
        FileWriter fileWriter = null;
        PrintWriter outputStream = null;

        try {
            fileWriter = new FileWriter(file);
            outputStream = new PrintWriter(fileWriter);
            outputStream.write(text);

        } catch (FileNotFoundException fne) {
            System.out.println("Error");
            return false;
        } catch (IOException ioe) {
            System.out.println("Error in saving");
            return false;
        } finally {

            if (outputStream != null) {
                outputStream.close();
            }

            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error in saving");
                return false;
            }

        }
        return true;
    }

    private boolean printDocument(String text, Font font) {
        TextPrinter textPrinter = new TextPrinter(text, font);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(textPrinter);
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
                ArrayList<String> textLines = textPrinter.getTextLines();
                textLines.clear();
                textLines = null;
                return true;
            } catch (PrinterException ex) {
                return false;
            }
        }
        return false;
    }

    public void setDialogFont(DialogFont dialogFont) {
        this.dialogFont = dialogFont;
    }


    public void sendLinkOperationUndo(JMenuItem operationUndo) {
        this.operationUndo = operationUndo;
    }

    public void sendLinkOperationRedo(JMenuItem operationRedo) {
        this.operationRedo = operationRedo;
    }

    public void sendLinkOperationFind(JMenuItem operationFind) {
        this.operationFind = operationFind;
    }

    public void sendLinkOperationCut(JMenuItem operationCut) {
        this.operationCut = operationCut;
    }

    public void sendLinkOperationCopy(JMenuItem operationCopy) {
        this.operationCopy = operationCopy;
    }

    public void sendLinkOperationRemove(JMenuItem operationRemove) {
        this.operationRemove = operationRemove;
    }

    public void sendLinkOperationPaste(JMenuItem operationPaste) {
        this.operationPaste = operationPaste;
    }


    @Override
    public void menuSelected(MenuEvent e) {

        textArea = viewer.getTextArea();

//-------------START Checking Selected Text in textArea------------
        String content = textArea.getSelectedText();
        if (content != null && !content.equals("")) {
            operationCut.setEnabled(true);
            operationCopy.setEnabled(true);
            operationRemove.setEnabled(true);
        } else {
            operationCut.setEnabled(false);
            operationCopy.setEnabled(false);
            operationRemove.setEnabled(false);
        }
        content = null;
//-------------END Checking Selected Text in textArea------------



//--------------START Checking  Undo / Redo  Enable-------------
        String text = textArea.getText();
        //System.out.println(text.length());
        if (text.length() > 0) {
            isUndoReady = true;
            operationFind.setEnabled(true);
        } else {
            operationFind.setEnabled(false);
            operationCut.setEnabled(false);
            operationCopy.setEnabled(false);
            operationRemove.setEnabled(false);
        }

        if (isUndoReady) {
            operationUndo.setEnabled(true);
        } else {
            operationUndo.setEnabled(false);
            operationRedo.setEnabled(false);
        }

        if(isUndoWorked) operationRedo.setEnabled(true);
        text = null;
//--------------END Checking Undo / Redo  Enable-------------



//-------------START Checking Clipboard in OS-----------------
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        Object dataClipBoard = null;
        try {
            dataClipBoard = clipboard.getData(DataFlavor.stringFlavor);
            //System.out.println(dataClipBoard);
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
            //System.out.println(dataClipBoard);
        } catch (IOException ex) {
            ex.printStackTrace();
            //System.out.println(data);
        }

        if (dataClipBoard == null) operationPaste.setEnabled(false);
        else operationPaste.setEnabled(true);
//-------------END Checking Clipboard in OS-----------------

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }


    public void setDialogAbout(DialogAbout dialogAbout) {
        this.dialogAbout = dialogAbout;
    }
}
