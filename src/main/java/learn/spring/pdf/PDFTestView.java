package learn.spring.pdf;

import com.lowagie.text.Paragraph;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by artem_shevtsov on 1/6/17.
 */
//@Component
public class PDFTestView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        document.add( new Paragraph( "Hello, World!" ) );
    }
}
