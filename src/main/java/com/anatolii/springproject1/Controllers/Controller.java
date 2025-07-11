package com.anatolii.springproject1.Controllers;

import com.anatolii.springproject1.TaskDAO;
import com.anatolii.springproject1.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class Controller {
    private TaskDAO taskDAO;
    @Autowired
    public Controller(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping("/")
    public ModelAndView getTasks(ModelAndView modelAndView) {
        modelAndView.addObject("listTasks", taskDAO.getAllByPage(1,11));
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/page/{page}")
    public ModelAndView getTasksWithPaging(@PathVariable String page,ModelAndView modelAndView) {
        modelAndView.addObject("listTasks", taskDAO.getAllByPage(Integer.parseInt(page),11));
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @PostMapping(value = "/edit")
    public void UpdateTasks(@RequestBody Task task) {
        taskDAO.update(task);
    }
    @DeleteMapping(value = "/delete")
    public void deleteTasks(@RequestBody String task) {
        taskDAO.delete(Integer.parseInt(task));
    }
    @PostMapping(value = "/create")
    public void CreateTasks(@RequestBody Task task) {
        taskDAO.create(task);
    }
}
