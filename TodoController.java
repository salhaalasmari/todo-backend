package com.example.todolistbackend.control;


import com.example.todolistbackend.dto.ApiResponse;
import com.example.todolistbackend.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/todo")
@CrossOrigin
public class TodoController {
    private ArrayList <Todo> todoArrayList=new ArrayList<>();
    @GetMapping
    public ResponseEntity getTodos(){
        return ResponseEntity.status(200).body(todoArrayList);
    }
    @PostMapping
    public ResponseEntity addTodos(@RequestBody Todo todo){
        todo.setId(UUID.randomUUID());
        todoArrayList.add(todo);
        return ResponseEntity.status(201).body(new ApiResponse("New todo added !",201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable UUID id){
        for (int i = 0; i < todoArrayList.size(); i++) {
            if(todoArrayList.get(i).getId().equals(id)){
                todoArrayList.remove(todoArrayList.get(i));
                return ResponseEntity.status(200).body(new ApiResponse("Todo deleted ",200));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id",400));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateTodo(@RequestBody Todo todo,@PathVariable UUID id){
        for (int i = 0; i < todoArrayList.size(); i++) {
            if(todoArrayList.get(i).getId().equals(id)){
                todo.setId(id);
                todoArrayList.set(i,todo);
                return ResponseEntity.status(200).body(new ApiResponse("Todo update ",200));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid id",400));

    }
}
