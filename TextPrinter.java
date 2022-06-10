import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;


public class TextPrinter implements Printable {
    private String text;
    private int[] pageBreaks;
    private ArrayList<String> textLines;
    private Font font;
    private Font fontForPrintIndexPage;
    private FontMetrics metrics;

    public TextPrinter(String text, Font font) {
        this.text = text;
        this.font = font;
        fontForPrintIndexPage = new Font("Arial", Font.PLAIN, 11);
    }

    public ArrayList<String> getTextLines() {
        return textLines;
    }

    private void initTextLines(int widthPage) {
        if (textLines == null) {
            textLines = new ArrayList<>();
        }

        String[] arrayTempText = text.split("\n");
        for (String lineText : arrayTempText) {
            int widthLine = metrics.stringWidth(lineText);
            if (widthLine <= widthPage) {
                textLines.add(lineText);
            } else {
                int startIndex = 0;
                int countEndIndex = 0;
                while (true) {
                    int widthCount = 0;
                    while (widthCount < widthPage) {
                        String str = lineText.substring(startIndex, countEndIndex);
                        countEndIndex = countEndIndex + 1;
                        widthCount = metrics.stringWidth(str);
                    }
                    countEndIndex = countEndIndex - 1;
                    textLines.add(lineText.substring(startIndex, countEndIndex));

                    String nextSubText = lineText.substring(countEndIndex);
                    int widthNextSubText = metrics.stringWidth(nextSubText);
                    if (widthNextSubText <= widthPage) {
                        textLines.add(nextSubText);
                        break;
                    }
                    startIndex = countEndIndex;
                }
            }
        }
        arrayTempText = null;

        // Second Variant Wrap String Line. You must comment code above and uncomment code below
        //textLines = (ArrayList<String>) StringUtils.wrap(text, metrics, widthPage);
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

        metrics = g.getFontMetrics(font);
        //g.clipRect(50, 50, 500, 800);
        g.setFont(font);

        int lineHeight = metrics.getHeight();
        int widthPage = (int) pf.getImageableWidth() - 100;
        int heightPage = (int) pf.getImageableHeight() - 110;
        if (pageBreaks == null) {
            initTextLines(widthPage);
            int linesPerPage = heightPage / lineHeight;
            int numBreaks = (textLines.size() - 1) / linesPerPage;

            pageBreaks = new int[numBreaks];
            for (int b = 0; b < numBreaks; b++) {
                pageBreaks[b] = (b + 1) * linesPerPage;
            }
        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int y = 50;
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
        int end = (pageIndex == pageBreaks.length) ? textLines.size() : pageBreaks[pageIndex];
        for (int line = start; line <= end; line++) {
            if(line < end) {
                y += lineHeight;
                g.drawString(textLines.get(line), 50, y);
            } else {
                g.setFont(fontForPrintIndexPage);
                g.drawString("page: " + pageIndex, widthPage / 2, heightPage + 90);
                g.setFont(font);
            }
        }

        if (start == pageBreaks[pageBreaks.length - 1]) {
            g.setFont(fontForPrintIndexPage);
            g.drawString("page: " + pageIndex, widthPage / 2, heightPage + 90);
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;

    }
}
