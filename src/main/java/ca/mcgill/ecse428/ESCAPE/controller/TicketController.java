package ca.mcgill.ecse428.ESCAPE.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ca.mcgill.ecse428.ESCAPE.dto.TicketRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.TicketResponseDto;
import ca.mcgill.ecse428.ESCAPE.model.Ticket;
import ca.mcgill.ecse428.ESCAPE.service.TicketService;

@RestController
@RequestMapping("/Tickets")
public class TicketController {

    @Autowired
    private TicketService TicketService;

    // create Ticket
 	@PostMapping("/Ticket")
 	public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketRequestDto request) {
 		TicketResponseDto response = TicketService.createTicket(request);
 		return new ResponseEntity<TicketResponseDto>(response, HttpStatus.CREATED);
 	}
    
    @PostMapping("/{TicketId}/delete")
    public String deleteTicket(@PathVariable("TicketId") int TicketId, Model model) {

        TicketService.deleteTicket(TicketId);

        return "redirect:/Tickets";
    }

    @GetMapping
    public String showTickets(Model model) {
        Iterable<Ticket> Tickets = TicketService.getAllTickets();
        model.addAttribute("Tickets", Tickets);
        return "view-Tickets";
    }


}