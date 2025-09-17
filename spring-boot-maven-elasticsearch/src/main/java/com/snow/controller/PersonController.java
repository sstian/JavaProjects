package com.snow.controller;

import com.snow.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PersonController {
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public PersonController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }
    @PostMapping("/person")
    public String save(@RequestBody Person person) {
        Person savedEntity = elasticsearchOperations.save(person);
        return savedEntity.getId();
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  Long id) {
        Person person = elasticsearchOperations.get(id.toString(), Person.class);
        return person;
    }
}
