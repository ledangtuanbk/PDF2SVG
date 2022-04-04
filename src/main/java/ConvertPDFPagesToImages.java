import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ConvertPDFPagesToImages {

    static String preamble = "<?xml version=\"1.0\" standalone=\"no\"?>\n<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<svg xmlns=\"http://www.w3.org/2000/svg\"\n     version=\"1.1\" width=\"$widthpx\" height=\"$heightpx\">\n";
    static String end = "</svg>";

    public static void main(String[] args) {

//        String inImage = "/home/ubuntu/IdeaProjects/test/test_1.png";
//        String outImage = "/home/ubuntu/IdeaProjects/test/test_1.svg";
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(inImage));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int width = img.getWidth();
//        int height = img.getHeight();
//        preamble = preamble.replaceAll("\\$width", "" + width);
//        preamble = preamble.replaceAll("\\$height", "" + height);
//        String midSVG = "";
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                Color color = new Color(img.getRGB(x, y), true);
//                if ((color.getAlpha() == 0) == false) {
//                    midSVG += ("    <rect x=\"" + x + "px\" y=\"" + y + "px\" width=\"1px\" height=\"1px\" fill=\"" + "#" + Integer.toHexString(color.getRGB()).substring(2) + "\"/>\n");
//                }
//
//            }
//        }
//        try {
//            Files.write(Paths.get(outImage), (preamble + midSVG + end).getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        new PdfService().pdfToPng();
    }

//    public static void main(String[] args) throws IOException {
////        addText();
//    }

//    private static void addText() throws IOException {
//        String sourceDir = "/home/ubuntu/IdeaProjects/test/test.pdf"; // Pdf files are read from this folder
//        PDDocument newPdf = PDDocument.load(new File(sourceDir));
//        PDPage firstPage=newPdf.getPage(1);
//        PDFont pdfFont= PDType1Font.HELVETICA_BOLD;
//        int fontSize = 14;
//        PDPageContentStream contentStream = new PDPageContentStream(newPdf, firstPage, PDPageContentStream.AppendMode.APPEND,true,true);
//        contentStream.setFont(pdfFont, fontSize);
//        contentStream.beginText();
//        contentStream.newLineAtOffset(200,685);
//        contentStream.showText("XXXXXXXXXXXXXXXXXXXXX");
//        contentStream.endText();
//        contentStream.close();
//        newPdf.save(new File("/home/ubuntu/IdeaProjects/test/test_result.pdf"));
//    }


}
