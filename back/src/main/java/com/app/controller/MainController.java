package com.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String redirect(HttpServletRequest request) {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
