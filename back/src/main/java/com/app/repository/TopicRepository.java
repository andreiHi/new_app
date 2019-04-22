package com.app.repository;

import com.app.entities.Topic;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public interface TopicRepository extends CrudRepository<Topic, Long>, ExtendedRepository<Topic, Long> {
}
