package dk.kudishin.data;

import dk.kudishin.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAllTasks() {
        return jdbcTemplate.queryForList("select * from tasks", Task.class);
    }
}