package ca.mcgill.ecse428.ESCAPE.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.ESCAPE.model.Event;


@Repository
public class EventRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    public Event getEventById(long id) throws EventNotFoundException {
        String sql = "SELECT * FROM events WHERE id = ?";
        List<Event> events = jdbcTemplate.query(sql, new Object[]{id}, new EventRowMapper());
        if (events.isEmpty()) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        return events.get(0);
    }

    public void createEvent(Event event) {
        String sql = "INSERT INTO events (title, description, location, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getLocation(), event.getStartTime(), event.getEndTime());
    }

    public void updateEvent(long id, Event event) throws EventNotFoundException {
        String sql = "UPDATE events SET title = ?, description = ?, location = ?, start_time = ?, end_time = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getLocation(), event.getStartTime(), event.getEndTime(), id);
        if (rowsAffected == 0) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }

    public void deleteEvent(long id) throws EventNotFoundException {
        String sql = "DELETE FROM events WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }

    private static class EventRowMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getLong("id"));
            event.setTitle(rs.getString("title"));
            event.setDescription(rs.getString("description"));
            event.setLocation(rs.getString("location"));
            event.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
            event.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
            return event;
        }
    }
}
