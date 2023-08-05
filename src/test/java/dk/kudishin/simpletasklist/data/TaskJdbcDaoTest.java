package dk.kudishin.simpletasklist.data;

import dk.kudishin.simpletasklist.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskJdbcDaoTest {

    @Autowired
    private TaskJdbcDao taskJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insertTask() {

        List<Task> tasks = jdbcTemplate.query(
                "select * from tasks",
                new BeanPropertyRowMapper<>(Task.class));
        int initialCount = tasks.size();

        taskJdbcDao.insertTask(new Task("Test task"));

        tasks = jdbcTemplate.query(
                "select * from tasks",
                new BeanPropertyRowMapper<>(Task.class));

        assertEquals(initialCount+1, tasks.size());
    }

    @Test
    void updateTask() {
        taskJdbcDao.insertTask(new Task("Test task"));
        Task loaded = jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE TASKDESC = ?",
                new BeanPropertyRowMapper<>(Task.class),
                "Test task");
        String testDesc = "New task desc";
        Task task = taskJdbcDao.getTaskById(loaded.getId());
        task.setTaskDesc(testDesc);
        int id = taskJdbcDao.updateTask(task);
        Task newTask = taskJdbcDao.getTaskById(id);
        assertEquals(testDesc, newTask.getTaskDesc());
    }

    @Test
    void deleteTaskById() {
        taskJdbcDao.insertTask(new Task("Deletion task"));
        List<Task> tasks = jdbcTemplate.query(
                "select * from tasks",
                new BeanPropertyRowMapper<>(Task.class));
        int initialCount = tasks.size();

        Task loaded = jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE TASKDESC = ?",
                new BeanPropertyRowMapper<>(Task.class),
                "Deletion task");

        taskJdbcDao.deleteTaskById(loaded.getId());
        tasks = jdbcTemplate.query(
                "select * from tasks",
                new BeanPropertyRowMapper<>(Task.class));
        int newSize = tasks.size();
        assertEquals(initialCount-1, newSize);

    }
}