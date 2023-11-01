package com.service360.group50.repo;

import com.service360.group50.entity.Complaints;
import com.service360.group50.entity.TodoListDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListDetailsRepo extends JpaRepository<TodoListDetails, Long> {
    @Override
    List<TodoListDetails> findAll();
    List<TodoListDetails> findByTodolistdetailsid ( Long todoListDetailsid );

}
