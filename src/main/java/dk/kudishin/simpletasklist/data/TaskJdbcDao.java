package dk.kudishin.simpletasklist.data;

import dk.kudishin.simpletasklist.exceptions.TaskNotFoundException;
import dk.kudishin.simpletasklist.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Qualifier("JDBC")
public class TaskJdbcDao implements TaskDao {

    private final Logger log = LoggerFactory.getLogger(TaskJdbcDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> getAllTasks() {
        log.info("Running Spring JDBC query to SELECT *");
        return jdbcTemplate.query(
                "select * from tasks",
                new BeanPropertyRowMapper<>(Task.class));

    }

    @Override
    public Task getTaskById(int id) {
        log.info("Running Spring JDBC query to SELECT one task");
        Task task;
        try {
            task = jdbcTemplate.queryForObject(
                    "SELECT * FROM tasks WHERE ID = ?",
                    new BeanPropertyRowMapper<>(Task.class),
                    id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("No such task in DB for given ID");
        }
        return task;
    }

    @Override
    public void insertTask(Task t) {
        log.info("Running Spring JDBC query to INSERT");
        String sql = "insert into tasks (taskdesc) values (?)";
        jdbcTemplate.update(sql, t.getTaskDesc());
    }

    @Override
    public int updateTask(Task t) {
        log.info("Running Spring JDBC query to UPDATE");
        String sql = "update tasks set taskdesc=? where id=?";
        return jdbcTemplate.update(sql, t.getTaskDesc(), t.getId());
    }

    @Override
    public void deleteTaskById(int id) {
        log.info("Running Spring JDBC query to DELETE");
        String sql = "delete from tasks where id=?";
        jdbcTemplate.update(sql, id);
    }

}