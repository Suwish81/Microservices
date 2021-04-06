package com.wish.batch.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStep {
    String name;
    ItemReader reader;
    ItemProcessor processor;
    ItemWriter writer;
    int chunkSize;

}
