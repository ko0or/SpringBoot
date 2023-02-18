package com.example.st.controller;

import com.example.st.dto.BoardDto;
import com.example.st.entity.BoardEntity;
import com.example.st.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/list")
    public String listMethod(Model model) {
        List<BoardEntity> entitys = boardRepository.findAll();
        model.addAttribute("model_entitys", entitys);
        return "list";
    }

    @GetMapping("/list/{id}")
    public String viewMethod(@PathVariable Long id,
                             Model model) {
        BoardEntity entity = boardRepository.findById(id).orElse(null);
        model.addAttribute("model_entity", entity);

        return "view";
    }


    @GetMapping("/write")
    public String writeMethod() {
        return "home";
    }

    @PostMapping("/post")
    public String post(BoardDto dto) {
        System.out.println( dto.toString() );

        BoardEntity entity = dto.toEntity();
        System.out.println( entity.toString() );

        BoardEntity saved = boardRepository.save(entity);
        System.out.println( saved );
        
        
        return "redirect:/list/" + saved.getId();
    }

    @GetMapping("/del/{id}")
    public String deleteMethod(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return "redirect:/list";
    }





}
