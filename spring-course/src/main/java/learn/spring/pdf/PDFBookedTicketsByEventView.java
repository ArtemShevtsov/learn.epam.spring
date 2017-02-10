package learn.spring.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.EventAuditorium;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Created by aftor on 07.01.17.
 */
public class PDFBookedTicketsByEventView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document,
                                    com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception{
        Set<Ticket> tickets = (Set<Ticket>)map.get("tickets");
        Event event = (Event)map.get("event");
        if(tickets.size() == 0){
            document.add(new Paragraph("There is no booked tickets for event " + event.getName()));
        }
        document.add( new Paragraph( "Please, see bellow list of booked tickets for event " + event.getName()));
        tickets.forEach(ticket -> {
            EventAuditorium ea = ticket.getEventAuditorium();
            String userName = ticket.getUser().getFullName();
            String auditoryName = ea.getAuditorium().getName();
            Integer seat = ticket.getSeat();
            try {
                document.add(new Paragraph(String.format("User: \"%s\"; Auditory: \"%s\"; Seat: \"%s\"",
                        userName, auditoryName, seat.toString())));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }
}
