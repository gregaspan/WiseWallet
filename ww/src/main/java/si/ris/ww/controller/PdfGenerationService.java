package si.ris.ww.controller;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import si.ris.ww.model.Kategorija;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class PdfGenerationService {

    public byte[] generateCategoryPdf(List<Kategorija> categories, String imagePath) throws MalformedURLException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);

        document.add(image);

        categories.forEach(category -> {
            document.add(new Paragraph(category.getIme())); // Replace getName() with your actual method
        });

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
