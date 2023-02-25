package ca.mcgill.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.mcgill.ecse428.ESCAPE.EscapeApplication;
import ca.mcgill.ecse428.ESCAPE.model.Event;
import ca.mcgill.ecse428.ESCAPE.repository.EventRepository;

import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
    import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.ResultActions;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;
    import org.springframework.web.context.WebApplicationContext;

    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest(classes = EscapeApplication.class)
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class EventIntegrationTests {

        private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private EventRepository eventRepository;

        private Event createEvent() {
            Event event = new Event();
            event.setName("Test Event");
            event.setDate("2023-02-20");
            event.setTicketPrice(30);
            event.setDescription("Test Description");
            return event;
        }

        private ResultActions createEventRequest(Event event) throws Exception {
            return mockMvc.perform(MockMvcRequestBuilders.post("/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                    .andExpect(status().isOk());
        }

        @Test
        public void testCreateEvent() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

            Event event = createEvent();
            createEventRequest(event);

            Event savedEvent = eventRepository.findById(1).orElse(null);
            assert savedEvent != null;
            assert savedEvent.getName().equals(event.getName());
            assert savedEvent.getDescription().equals(event.getDescription());
            assert Double.compare(savedEvent.getTicketPrice(), event.getTicketPrice()) == 0;
        }

        @Test
        public void testGetEventById() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

            Event event = createEvent();
            createEventRequest(event);

            mockMvc.perform(MockMvcRequestBuilders.get("/events/1"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(event.getName()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.time").value(event.getTicketPrice()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(event.getDescription()));
        }

        @Test
        public void testUpdateEvent() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

            Event event = createEvent();
            createEventRequest(event);

            Event updatedEvent = new Event();
            updatedEvent.setName("Updated Test Event");
            updatedEvent.setDate("2023-02-21");
            updatedEvent.setTicketPrice(30);
            updatedEvent.setDescription("Updated Test Description");

            mockMvc.perform(MockMvcRequestBuilders.put("/events/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updatedEvent)))
                    .andExpect(status().isOk());

            Event savedEvent = eventRepository.findEventById(1);
            assert savedEvent != null;
            assert savedEvent.getName().equals(updatedEvent.getName());
            assert Double.compare(savedEvent.getTicketPrice(), updatedEvent.getTicketPrice()) == 0;
            updatedEvent.setDescription("Updated Test Description");

        mockMvc.perform(MockMvcRequestBuilders.put("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEvent)))
                .andExpect(status().isOk());

        event = eventRepository.findEventById(1);
        assert savedEvent != null;
        assert savedEvent.getName().equals(updatedEvent.getName());
        assert Double.compare(savedEvent.getTicketPrice(), updatedEvent.getTicketPrice()) == 0;
        assert savedEvent.getDescription().equals(updatedEvent.getDescription());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Event event = createEvent();
        createEventRequest(event);

        mockMvc.perform(MockMvcRequestBuilders.delete("/events/1"))
                .andExpect(status().isOk());

        assert eventRepository.findEventById(1) != null;
    }
}
        
