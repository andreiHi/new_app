package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
