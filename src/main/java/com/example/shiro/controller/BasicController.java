package com.example.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/basic")
@Controller
public class BasicController {

    @RequestMapping("/info")
    public String info() {
        return "/basic/info";
    }

    @RequestMapping("/page")
    public String page() {
        return "/basic/page";
    }

    @RequestMapping("/adv")
    public String adv() {
        return "/basic/adv";
    }

    @RequestMapping("/add/adv")
    public String addAdv() {
        return null;
    }

    @RequestMapping("/del/adv")
    public String delAdv() {
        return null;
    }

    @RequestMapping("/upd/adv")
    public String updAdv() {
        return null;
    }

    @RequestMapping("/book")
    public String book() {
        return "/basic/book";
    }

    @RequestMapping("/column")
    public String column() {
        return "/basic/column";
    }
}
