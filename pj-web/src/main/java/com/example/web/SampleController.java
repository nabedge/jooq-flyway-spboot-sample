package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SampleController {

    private final BookRepository bookRepository;

    @Autowired
    public SampleController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("")
    public String index(Model model) {
        List<Book> books = bookRepository.selectAll();
        model.addAttribute("books", books);
        return "index";
    }
}
