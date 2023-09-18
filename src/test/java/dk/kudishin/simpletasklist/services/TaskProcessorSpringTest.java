package dk.kudishin.simpletasklist.services;

import dk.kudishin.simpletasklist.data.TaskDao;
import dk.kudishin.simpletasklist.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/*
Things to understand:
 - this is a SpringBootTest
 - the value it brings is 0. It's only purpose in life is to demonstrate Mockito
 */
@SpringBootTest
class TaskProcessorSpringTest {

    @Autowired
    private TaskProcessor taskProcessor;

    @MockBean
    @Qualifier
    private TaskDao taskDao;

    @Test
    void getAllTasks() {
        when(taskDao.getAllTasks())
                .thenReturn(Collections.emptyList());
        List<Task> allTasks = taskProcessor.getAll();
        assertEquals(0, allTasks.size());
    }
}