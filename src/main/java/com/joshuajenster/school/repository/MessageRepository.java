package com.joshuajenster.school.repository;

import com.joshuajenster.school.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {


}
