import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;


public class DialogFont implements ListSelectionListener {
    private Font fontDialogMenuFont;
    private JLabel labelFont;
    private JTextField textFieldFont;
    private JList<String> fontsList;
    private JScrollPane listFontsScroller;

    private JLabel labelStyle;
    private JTextField textFieldStyle;
    private JList<String> stylesList;
    private JScrollPane listStylesScroller;

    private JLabel labelSize;
    private JTextField textFieldSize;
    private JList<Integer> sizesList;
    private JScrollPane listSizesScroller;

    private JLabel labelExample;
    private JPanel panelFontExample;

    private JLabel labelCharacterSet;
    private JComboBox<String> comboBoxSymbols;

    private JButton buttonOK;
    private JButton buttonCancel;

    private JPanel panel;
    private JDialog dialog;

    public DialogFont(Controller controller, JFrame frame) {

        fontDialogMenuFont = new Font("Arial", Font.PLAIN, 14);

// ---------------------Start First Column (Font)--------------------
        labelFont = new JLabel("Font: ");
        labelFont.setBounds(15, 20, 100, 20);
        labelFont.setFont(fontDialogMenuFont);
        labelFont.setOpaque(true);
        labelFont.setVisible(true);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] dataFontArray = ge.getAvailableFontFamilyNames();
        textFieldFont = new JTextField();
        textFieldFont.setBounds(15, 45, 200, 30);
        textFieldFont.setFont(fontDialogMenuFont);
        textFieldFont.setText(dataFontArray[0]);
        textFieldFont.setVisible(true);

        fontsList = new JList<String>(dataFontArray);
        fontsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontsList.setLayoutOrientation(JList.VERTICAL);
        fontsList.setFont(fontDialogMenuFont);
        fontsList.addListSelectionListener(this);

        listFontsScroller = new JScrollPane(fontsList);
        listFontsScroller.setBounds(15, 75, 200, 140);

//-------------------End First Column (Font)------------------------------


//--------------------Start Second Colum (Style)-------------------------------
        labelStyle = new JLabel("Style: ");
        labelStyle.setBounds(230, 20, 200, 20);
        labelStyle.setFont(fontDialogMenuFont);
        labelStyle.setOpaque(true);
        labelStyle.setVisible(true);

        String[] dataStyleArray = new String[]{"Plain", "Bold", "Italic"};
        textFieldStyle = new JTextField();
        textFieldStyle.setBounds(230, 45, 250, 30);
        textFieldStyle.setFont(fontDialogMenuFont);
        textFieldStyle.setText(dataStyleArray[0]);
        textFieldStyle.setVisible(true);

        stylesList = new JList<String>(dataStyleArray);
        stylesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stylesList.setLayoutOrientation(JList.VERTICAL);
        stylesList.setFont(fontDialogMenuFont);
        stylesList.addListSelectionListener(this);

        listStylesScroller = new JScrollPane(stylesList);
        listStylesScroller.setBounds(230, 75, 250, 140);
//--------------------End Second Column (Style) --------------------------------


//--------------------Start Third Column (Size)-------------------------------
        labelSize = new JLabel("Size: ");
        labelSize.setBounds(495, 20, 70, 20);
        labelSize.setFont(fontDialogMenuFont);
        labelSize.setOpaque(true);
        labelSize.setVisible(true);

        Integer[] dataSizeArray = new Integer[]{8, 9, 24, 26, 45, 55, 60, 72};
        textFieldSize = new JTextField();
        textFieldSize.setBounds(495, 45, 80, 30);
        textFieldSize.setFont(fontDialogMenuFont);
        textFieldSize.setText("" + dataSizeArray[0]);
        textFieldSize.setVisible(true);

        sizesList = new JList<Integer>(dataSizeArray);
        sizesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sizesList.setLayoutOrientation(JList.VERTICAL);
        sizesList.setFont(fontDialogMenuFont);
        sizesList.addListSelectionListener(this);

        listSizesScroller = new JScrollPane(sizesList);
        listSizesScroller.setBounds(495, 75, 80, 140);
//--------------------End Third Column (Size)---------------------------------

//--------------------Start JPanel with Example------------------------------------
        labelExample = new JLabel("AaBbYyZz", SwingConstants.CENTER);
        labelExample.setBounds(230, 245, 345, 100);
        labelExample.setFont(fontDialogMenuFont);
        labelExample.setOpaque(true);
        labelExample.setVisible(true);

        panelFontExample = new JPanel(new BorderLayout());
        panelFontExample.setBounds(230, 245, 345, 106);
        TitledBorder exampleTitle = new TitledBorder("Example");
        exampleTitle.setTitleFont(fontDialogMenuFont);
        panelFontExample.setBorder(exampleTitle);
        panelFontExample.add(labelExample);
//--------------------End JPanel with Example------------------------------------


//-----------------Start Combobox-------------------------
        labelCharacterSet = new JLabel("Character Set: ");
        labelCharacterSet.setBounds(230, 360, 140, 20);
        labelCharacterSet.setFont(fontDialogMenuFont);
        labelCharacterSet.setOpaque(true);
        labelCharacterSet.setVisible(true);

        String[] symbolsArray = {"Cyrillic", "Western European", "Greek"};
        comboBoxSymbols = new JComboBox<String>(symbolsArray);
        comboBoxSymbols.setFont(fontDialogMenuFont);
        comboBoxSymbols.setBounds(230, 385, 345, 27);
        comboBoxSymbols.setSelectedIndex(1);
        comboBoxSymbols.setEditable(true);
//-----------------End Combobox----------------------------

// ----------------Start Buttons in footer---------------------
        buttonOK = new JButton("OK");
        buttonOK.setBounds(385, 530, 90, 36);
        buttonOK.setContentAreaFilled(false);
        buttonOK.addActionListener(controller);
        buttonOK.setActionCommand("OK_Font");

        buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(485, 530, 90, 36);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.addActionListener(controller);
        buttonCancel.setActionCommand("Cancel_Font");
//-----------------End Buttons in footer----------------

//---------------Container----------------------
        panel = new JPanel();
        panel.setSize(600, 620);
        panel.setLayout(null);
        panel.add(labelFont);
        panel.add(textFieldFont);
        panel.add(listFontsScroller);
        panel.add(labelStyle);
        panel.add(textFieldStyle);
        panel.add(listStylesScroller);
        panel.add(labelSize);
        panel.add(textFieldSize);
        panel.add(listSizesScroller);
        panel.add(panelFontExample);
        panel.add(labelCharacterSet);
        panel.add(comboBoxSymbols);
        panel.add(buttonOK);
        panel.add(buttonCancel);

        dialog = new JDialog(frame, "Fonts", true);
        dialog.setSize(600, 620);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(null);
        dialog.add(panel);
    }


    public void showFontDialog(boolean isOpen) {
        dialog.setVisible(isOpen);
    }

    public Font getFont() {
        String fontName = textFieldFont.getText();

        String fontStyle = textFieldStyle.getText();
        int style = 0;
        switch (fontStyle) {
            case "Plain":
                style = Font.PLAIN;
                break;
            case "Bold":
                style = Font.BOLD;
                break;
            case "Italic":
                style = Font.ITALIC;
                break;
            default:
                break;
        }

        String fontSize = textFieldSize.getText();
        Font font = new Font(fontName, style, Integer.parseInt(fontSize));
        return font;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

        JList obj = (JList) e.getSource();

        if (obj == fontsList) {
            String font = fontsList.getSelectedValue();
            textFieldFont.setText(font);
        } else if (obj == stylesList) {
            String style = stylesList.getSelectedValue();
            textFieldStyle.setText(style);
        } else if (obj == sizesList) {
            Integer size = sizesList.getSelectedValue();
            textFieldSize.setText("" + size);
        }

        Font fontExample = getFont();
        labelExample.setFont(fontExample);
    }
}