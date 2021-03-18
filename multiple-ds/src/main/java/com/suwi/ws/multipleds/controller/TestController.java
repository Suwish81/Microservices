package com.suwi.ws.multipleds.controller;

import com.suwi.ws.multipleds.data.book.entity.Book;
import com.suwi.ws.multipleds.data.book.repository.BookRepository;
import com.suwi.ws.multipleds.data.log.entity.Log;
import com.suwi.ws.multipleds.data.log.repository.LogRepository;
import com.suwi.ws.multipleds.data.student.entity.Student;
import com.suwi.ws.multipleds.data.student.repository.StudentRepository;
import com.suwi.ws.multipleds.data.user.entity.User;
import com.suwi.ws.multipleds.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LogRepository logRepository;

    @PostConstruct
    public void init(){
        bookRepository.saveAll(Stream.of(new Book( Long.getLong("1"),"Book1"),new Book(Long.getLong("2"),"Book2")).collect(Collectors.toList()));
        userRepository.saveAll(Stream.of(new User(Long.getLong("1"),"User1"),new User(Long.getLong("2"),"User2")).collect(Collectors.toList()));
        studentRepository.saveAll(Stream.of(new Student(Long.getLong("1"),"Stud1"),new Student(Long.getLong("2"),"Stud2")).collect(Collectors.toList()));
        logRepository.saveAll(Stream.of(new Log(Long.getLong("1"),"Log1"),new Log(Long.getLong("2"),"Log2")).collect(Collectors.toList()));
    }

    @GetMapping("/book/add")
    public ResponseEntity<Book> addBook(@RequestParam("name") String name){
        return ResponseEntity.ok(bookRepository.save(new Book(null,name)));
    }
    @GetMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestParam("name") String name){
        return ResponseEntity.ok(userRepository.save(new User(null,name)));
    }

    @GetMapping("/student/add")
    public ResponseEntity<Student> addStudent(@RequestParam("name") String name){
        return ResponseEntity.ok(studentRepository.save(new Student(null,name)));
    }

    @GetMapping("/log/add")
    public ResponseEntity<Log> addLog(@RequestParam("name") String name){
        return ResponseEntity.ok(logRepository.save(new Log(null,name)));
    }
}
