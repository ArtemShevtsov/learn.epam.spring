package learn.spring.web.messageconverters;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by artem_shevtsov on 2/8/17.
 */
public class TicketPdfMessageConverter implements HttpMessageConverter<Ticket> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_PDF);
    }

    @Override
    public Ticket read(Class<? extends Ticket> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Ticket ticket, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Document document = new Document();
        OutputStream outputStream = httpOutputMessage.getBody();
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph(ticket.toString()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
}
