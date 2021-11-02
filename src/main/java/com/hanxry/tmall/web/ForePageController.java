package com.hanxry.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 与AdminPageController类比的，还有 ForePageController 来专门做前台页面的跳转。
 *
 * @author hanxry
 * @since 2021/11/1
 */
@Controller
public class ForePageController {
    @GetMapping(value = "/")
    public String index() {
        return "redirect:home";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "fore/home";
    }
}
