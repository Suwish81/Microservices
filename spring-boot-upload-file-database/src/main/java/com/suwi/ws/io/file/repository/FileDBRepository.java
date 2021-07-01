package com.suwi.ws.io.file.repository;

import com.suwi.ws.io.file.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
