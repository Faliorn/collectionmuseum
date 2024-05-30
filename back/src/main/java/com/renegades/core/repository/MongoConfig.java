package com.renegades.core.repository;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.renegades.core.repository",
        "com.renegades.collectionmuseum.repository" }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BaseRepository.class))
public class MongoConfig {
}