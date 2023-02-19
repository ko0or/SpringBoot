package com.example.st.controller;

import com.example.st.dto.BoardDto;
import com.example.st.entity.BoardEntity;
import com.example.st.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/dummy")
    public String createDummy() {
        for (int i=0; i<100; i++) {
            boardRepository.save( new BoardEntity( null, "더미데이터 [" + i + "]", "빈공간", null ) );
        }
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listMethod(Model model,
                             @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable ){


        Page<BoardEntity> list = boardRepository.findAll(pageable);
//      int nowPage = pageable.getPageNumber();

        int last = list.getTotalPages() - 1;
        int first = 0;

        int nowPage = list.getPageable().getPageNumber() + 1;       //우리가 보는 화면보다 1이 작음 (인덱스 개념) 그래서 +1을 해줘야함        //지금 보고있는 페이지 수를 반환
        int startPage = 1;
        int endPage = 10;

        if (nowPage > 5) {
            startPage = nowPage-4 ;
            endPage = Math.min(nowPage+5, list.getTotalPages());
        }


        model.addAttribute("model_entitys", list);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("last", last);
        model.addAttribute("first", first);


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


    @GetMapping("/edit/{id}")
    public String editMethod(@PathVariable Long id,
                               Model model)
    {
        BoardEntity Entity = boardRepository.findById(id)
                                            .orElse(null);

        model.addAttribute("Entity", Entity);
        return "edit";
    }


    @PostMapping("/update/{id}")
    public String updateMethod(@PathVariable Long id,
                               BoardDto dto) {
        System.out.println( dto.toString() );
        BoardEntity Entity = dto.toEntity();
        BoardEntity target = boardRepository.findById(id)
                        .orElse(null);

        if (target != null)
        {
         boardRepository.save(Entity);
         return "redirect:/list/" + id;
        }
        return null;
    }


}
