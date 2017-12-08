package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.repository.CrudRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;

/**
 * Created by George Stratulat on 23/11/2017.
 */
@Controller
public class HomeController {

    @Autowired
    UserRepository ur;

    @GetMapping("/")
    public ModelAndView doHome() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/logOut")
    public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public ModelAndView doLoginCheck(@RequestParam("authCode") int authCode, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("index");

        User user = new User();
        user = ur.findOne(authCode);

        if (user != null) {
            request.getSession().setAttribute("userId", authCode);

            if (user.type.equals("student")) {
                mv.setViewName("redirect:/student");
            } else if (user.type.equals("teacher")) {
                mv.setViewName("redirect:/teacher");
            } else if (user.type.equals("admin")) {
                mv.setViewName("redirect:/admin");
            }
        }

        return mv;
    }
}