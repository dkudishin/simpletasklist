package dk.kudishin.simpletasklist.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler
    public String intercept(TaskNotFoundException e,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("e", e);
        return "error";
    }
}