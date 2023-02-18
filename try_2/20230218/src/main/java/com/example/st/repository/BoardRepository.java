package com.example.st.repository;

import com.example.st.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
    @Override
    ArrayList<BoardEntity> findAll();
}
