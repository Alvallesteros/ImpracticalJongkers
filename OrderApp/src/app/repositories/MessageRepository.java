package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
