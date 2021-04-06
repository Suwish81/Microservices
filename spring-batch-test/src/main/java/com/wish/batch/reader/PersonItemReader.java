package com.wish.batch.reader;

import com.wish.batch.entity.Person;
import com.wish.batch.util.BeanWrapperFieldFlatFileItemReaderItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component("personItemReader")
public class PersonItemReader extends BeanWrapperFieldFlatFileItemReaderItemReader<Person> {

    @Autowired
    public PersonItemReader(@Value("classpath:sample-data.csv") Resource res) {
        super(res, "PersonReader", new String[]{"id","firstName", "lastName"}, 0);
    }
}
