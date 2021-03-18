package com.suwi.ws.multipleds.data.book.repository;

import com.suwi.ws.multipleds.data.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
