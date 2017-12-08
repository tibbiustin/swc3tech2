package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by coag on 24-10-2017.
 */
@Controller
public class UserController {
    private static AtomicLong counter = new AtomicLong();


    @PostMapping("/user/saveandget")
    public ModelAndView saveAndShow(
            @RequestParam(name = "id", defaultValue = "-1")
                    long id,
            @RequestParam(name = "name", defaultValue = "NO_NAME")
                    String name,
            @RequestParam(name = "email", defaultValue = "NO_EMAIL")
                    String email) {

        User u;
        System.out.println("in saveandget and id="+id);
        if (id != -1) {
            System.out.println("inside if and id="+id);
            User.getUserById(id).setName(name);
            User.getUserById(id).setEmail(email);
            u = User.getUserById(id);
        } else {
            u = new User(counter.incrementAndGet(), name, email);
            User.getUserList().add(u);
        }


        ModelAndView mv = new ModelAndView("user");
        mv.getModel().put("usersList", User.getUserList());
        mv.getModel().put("user", u);
        return mv;
    }

    @GetMapping("/user/show")
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView("user");
        mv.getModel().put("usersList", User.getUserList());
        mv.getModel().put("user", "Here are the users");
        return mv;
    }

    @GetMapping("/user/edit")
    public ModelAndView editUser(
            @RequestParam(name = "id", defaultValue = "0")
                    long id) {
        System.out.println("id = " + id);
        ModelAndView mv = new ModelAndView("edit");

        mv.getModel().put("user", User.getUserById(id));

        return mv;
    }
}
