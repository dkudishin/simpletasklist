package dk.kudishin.simpletasklist.data;

import dk.kudishin.simpletasklist.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("JPA")
public class TaskJpaDao implements TaskDao {

    private Logger log = LoggerFactory.getLogger(TaskJpaDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> getAllTasks() {
        log.info("Running Spring JPA query to SELECT *");
        List<Task> tasks = entityManager.createQuery("select t from Task t", Task.class)
                .getResultList();
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = entityManager.find(Task.class, id);
        System.out.println(task);
        return task;
    }

    @Override
    public int insertTask(Task t) {
        return 0;
    }

    @Override
    public int updateTask(Task t) {
        return 0;
    }

    @Override
    public int deleteTaskById(int id) {
        return 0;
    }
}