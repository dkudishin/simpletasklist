package dk.kudishin.simpletasklist.services;

import dk.kudishin.simpletasklist.data.TaskDao;
import dk.kudishin.simpletasklist.domain.Task;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
Things to understand:
 - this is NOT a SpringBootTest
 - the value it brings is 0. It's only purpose in life is to demonstrate Mockito
 */
class TaskProcessorTest {

    private final TaskDao taskDao = mock(TaskDao.class);

    @Test
    void getAllTasks() {
        TaskProcessor taskProcessor = new TaskProcessor(taskDao);
        when(taskDao.getAllTasks())
                .thenReturn(Collections.emptyList());
        List<Task> allTasks = taskProcessor.getAll();
        assertEquals(0, allTasks.size());
    }
}