//package ca.mcgill.ecse428.ESCAPE.features;
//
//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//import ca.mcgill.ecse428.ESCAPE.model.*;
//
//
//public class TicketAcceptanceTests {
//
//    @Test
//    public void adminDeletesTicket_TicketNoLongerDisplayedInList() {
//        // Arrange
//        Ticket Ticket = new Ticket();
//        Admin admin = new Admin();
//        Ticket.create();
//        List<Ticket> Tickets = Ticket.getAllTickets();
//        assertTrue(Tickets.contains(Ticket));
//
//        // Act
//        admin.login();
//        admin.navigateToTicketManagementPage();
//        admin.selectTicket(Ticket);
//        admin.deleteTicket();
//
//        // Assert
//        Tickets = Ticket.getAllTickets();
//        assertFalse(Tickets.contains(Ticket));
//    }
//
//}
//
