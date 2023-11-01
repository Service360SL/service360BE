package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.TodoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList,Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TodoList t where t.job.jobid = :jobid")
    boolean existsByJobid(@Param("jobid") Long jobid);
    @Query("SELECT t.todolistid FROM TodoList t where t.job.jobid = :jobid")
    Long getTodoListIdByJobId(@Param("jobid") Long jobid);
    @Query("SELECT t FROM TodoList t where t.todolistid = :todolistid")
    Optional<TodoList> getTodoListByTodolistid(@Param ("todolistid") Long todolistid);
}
