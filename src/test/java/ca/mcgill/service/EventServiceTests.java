package ca.mcgill.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;
import ca.mcgill.ecse428.ESCAPE.service.EventService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
@SpringBootTest
class EventServiceTests {

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @Test
    void testGetAllEvents() {
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = Arrays.asList(event1, event2);
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = (List<Event>) eventService.getAllEvents();

        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
        assertEquals(events, result);
    }
/* 
@Test
void testDeleteEvent() throws Exception {
    int eventId = 1;
    MockMvc mockMvc;
    mockMvc.perform(MockMvcRequestBuilders.post("/events/{id}/delete", eventId))
           .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/events"));

    Mockito.verify(eventService, Mockito.times(1)).deleteEvent(eventId);
}
*/


}