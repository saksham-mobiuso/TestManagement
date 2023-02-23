package com.mobi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Answers;

@Service
public interface AnswerRepository extends CrudRepository<Answers,Integer>{

}
