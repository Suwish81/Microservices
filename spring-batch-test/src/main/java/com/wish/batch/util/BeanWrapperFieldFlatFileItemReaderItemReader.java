package com.wish.batch.util;

import com.wish.batch.entity.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BeanWrapperFieldFlatFileItemReaderItemReader<T> extends FlatFileItemReader<T> {

    public  BeanWrapperFieldFlatFileItemReaderItemReader(Resource resource, String readerName, String[] names, int linesToSkip) {
        this.setResource(resource);
        this.setName(readerName);
        this.setLinesToSkip(linesToSkip);
        this.setLineMapper(lineMapper(names,","));

    }
    public  BeanWrapperFieldFlatFileItemReaderItemReader(Resource resource, String readerName, String[] names, int linesToSkip,String delimitter) {
        this.setResource(resource);
        this.setName(readerName);
        this.setLinesToSkip(linesToSkip);
        this.setLineMapper(lineMapper(names,delimitter));

    }

    private LineMapper<T> lineMapper(String[] names,String delimitter) {
        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];


        DefaultLineMapper<T> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(delimitter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names);

        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(type);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
