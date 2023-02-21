package com.example.st.repository;

import com.example.st.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Override
    List<BoardEntity> findAll();

    Page<BoardEntity> findByTitleContaining(String searchKeyword, Pageable pageable);
}
