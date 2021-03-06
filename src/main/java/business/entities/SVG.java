package business.entities;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;


    // SVG dimensioner
    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
//  private final String textTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(30,300) rotate(-90)\">600 cm</text>";
    private final String textTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d) rotate(%d)\">%d cm</text>";


    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(int x, int y, double height, double width) {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }


    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }

    public void addText(int x, int y, int rotationdegrees, int text) {
        svg.append(String.format(textTemplate,x,y,rotationdegrees, text));
    }
}
