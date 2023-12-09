package ru.sberbank.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/finance")
public class FinanceController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView calculator(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/calculator.jsp");

        return  modelAndView;
    }

}