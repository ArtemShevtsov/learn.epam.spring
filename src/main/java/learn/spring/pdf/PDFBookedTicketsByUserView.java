package learn.spring.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.sun.javafx.binding.StringFormatter;
import learn.spring.core.entity.EventAuditorium;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Created by artem_shevtsov on 1/6/17.
 */
public class PDFBookedTicketsByUserView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document,
                                    com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception{
        Set<Ticket> tickets = (Set<Ticket>)map.get("tickets");
        User user = (User)map.get("user");
        if(tickets.size() == 0){
            document.add(new Paragraph("There is no booked tickets for user " + user.getFullName()));
        }
        document.add( new Paragraph( "Please, see bellow list of booked tickets for user " + user.getFullName()));
        tickets.forEach(ticket -> {
            EventAuditorium ea = ticket.getEventAuditorium();
            String eventName = ea.getEvent().getName();
            String auditoryName = ea.getAuditorium().getName();
            Integer seat = ticket.getSeat();
            try {
                document.add(new Paragraph(String.format("Film: \"%s\"; Auditory: \"%s\"; Seat: \"%s\"",
                        eventName, auditoryName, seat.toString())));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }
}
