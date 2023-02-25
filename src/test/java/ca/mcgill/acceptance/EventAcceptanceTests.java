package ca.mcgill.acceptance;
/* 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.mcgill.ecse428.ESCAPE.EscapeApplication;
import ca.mcgill.ecse428.ESCAPE.controller.EventController;
import ca.mcgill.ecse428.ESCAPE.dto.EventRequestDto;
import ca.mcgill.ecse428.ESCAPE.dto.EventResponseDto;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = { EscapeApplication.class })
public class EventAcceptanceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createEventTest() throws Exception {
        // Given
        EventRequestDto eventRequestDto = new EventRequestDto();
        eventRequestDto.setName("Test Event");
        eventRequestDto.setDescription("This is a test event");
        eventRequestDto.setTicketPrice(19.99);
        eventRequestDto.setUserId(1);

        String jsonRequest = objectMapper.writeValueAsString(eventRequestDto);

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/events/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andReturn();

        // Then
        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), statusCode);

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        EventResponseDto eventResponseDto = objectMapper.readValue(jsonResponse, EventResponseDto.class);

        assertNotNull(eventResponseDto.getId());
        assertEquals(eventRequestDto.getName(), eventRequestDto.getName());
        assertEquals(eventRequestDto.getDescription(), eventRequestDto.getDescription());
        assertEquals(eventRequestDto.getTicketPrice(), eventRequestDto.getTicketPrice(), 0.001);
    }

    @Test
    public void deleteEventTest() throws Exception {
        // Given
        EventRequestDto eventRequestDto = new EventRequestDto();
        eventRequestDto.setName("Test Event");
        eventRequestDto.setDescription("This is a test event");
        eventRequestDto.setTicketPrice(19.99);
        eventRequestDto.setUserId(1);

        String jsonRequest = objectMapper.writeValueAsString(eventRequestDto);

        MvcResult createEventResult = mockMvc.perform(MockMvcRequestBuilders.post("/events/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andReturn();

        int createEventStatusCode = createEventResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), createEventStatusCode);

        String createEventJsonResponse = createEventResult.getResponse().getContentAsString();
        EventResponseDto createdEvent = objectMapper.readValue(createEventJsonResponse, EventResponseDto.class);

        // When
        MvcResult deleteEventResult = mockMvc.perform(MockMvcRequestBuilders.delete("/events/event/{id}", createdEvent.getId()))
                .andReturn();

        // Then
        int deleteEventStatusCode = deleteEventResult.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), deleteEventStatusCode);
    }

}
*/