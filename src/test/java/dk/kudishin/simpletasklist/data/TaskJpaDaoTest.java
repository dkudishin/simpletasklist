package dk.kudishin.simpletasklist.data;

import dk.kudishin.simpletasklist.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@Import(TaskJpaDao.class)
@DataJpaTest
class TaskJpaDaoTest {

    @Autowired
    private TaskJpaDao dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getAllTasks() {
        dao.getAllTasks().forEach(System.out::println);
    }

    @Test
    void insertAndFindTask() {
        int initialCount = dao.getAllTasks().size();

        dao.insertTask(new Task("TEST"));

        int updatedCount = dao.getAllTasks().size();
        assertEquals(initialCount+1, updatedCount);

        Task loaded = jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE TASKDESC = ?",
                new BeanPropertyRowMapper<>(Task.class),
                "TEST");

        Task found = dao.getTaskById(loaded.getId());

        assertEquals(loaded, found);
    }
}