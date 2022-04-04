import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGPath;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfService {

    public static void pdfToPng() {
        try {
            String sourceDir = "/home/ubuntu/IdeaProjects/test/test.pdf"; // Pdf files are read from this folder
            String destinationDir = "/home/ubuntu/IdeaProjects/test/"; // converted images from pdf document are saved here

            File sourceFile = new File(sourceDir);
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
            }
            if (sourceFile.exists()) {
                System.out.println("Images copied to Folder Location: "+ destinationFile.getAbsolutePath());
                PDDocument document = PDDocument.load(sourceFile);
                PDFRenderer pdfRenderer = new PDFRenderer(document);

                int numberOfPages = document.getNumberOfPages();
                System.out.println("Total files to be converting -> "+ numberOfPages);

                String fileName = sourceFile.getName().replace(".pdf", "");
                String fileExtension= "svg";
                /*
                 * 600 dpi give good image clarity but size of each image is 2x times of 300 dpi.
                 * Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB
                 *      2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB
                 */
                int dpi = 300;// use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi

                for (int i = 0; i < numberOfPages; ++i) {
                    File outPutFile = new File(destinationDir + fileName +"_"+ (i+1) +"."+ fileExtension);
                    BufferedImage bImage = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
                    GeneralPath path = new GeneralPath();
                    SVGOMDocument svgomDocument = createSvgDocument(bImage.getWidth(),bImage.getHeight());

//                    putPathToSvgDocument(svgomDocument, path);
//                    saveSvgDocumentToFile(svgomDocument, outPutFile);
                    ImageIO.write(bImage, fileExtension, outPutFile);
                }

                document.close();
                System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
            } else {
                System.err.println(sourceFile.getName() +" File not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SVGOMDocument createSvgDocument(int width, int height) {
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        SVGOMDocument document = (SVGOMDocument) domImpl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);
        Element svgTag = document.getRootElement();
        svgTag.setAttribute("width", String.valueOf(width));
        svgTag.setAttribute("height", String.valueOf(height));
        return document;
    }

    private static void putPathToSvgDocument(SVGOMDocument document, GeneralPath path) {
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        SVGPath svgPath = new SVGPath(ctx);
        Element svgElement = svgPath.toSVG(path);
        svgElement.setAttribute("fill", "#000");
        document.getRootElement().appendChild(svgElement);
    }

    private static void saveSvgDocumentToFile(SVGOMDocument document, File file) throws FileNotFoundException, IOException {
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        try (Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            svgGenerator.stream(document.getDocumentElement(), out);
        }
    }
}
