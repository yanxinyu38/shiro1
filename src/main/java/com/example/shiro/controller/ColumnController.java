package com.example.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/column")
@Controller
public class ColumnController {
/*
    <li><a href="column/list.html" target="right"><span class="icon-caret-right"></span>内容管理</a></li>
    <li><a href="column/add.html" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
    <li><a href="column/cate.html" target="right"><span class="icon-caret-right"></span>分类管理</a></li>
 */
    @RequestMapping("/list")
    public String list() {
        return "column/list";
    }

    @RequestMapping("/add")
    public String add() {
        return "column/add";
    }

    @RequestMapping("/cate")
    public String cate() {
        return "column/cate";
    }
}
