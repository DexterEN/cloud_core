package kth.lab2_journal_core.data.message;

import kth.lab2_journal_core.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message getById(Long id);
    List<Message> getMessagesByReceiver(User user);
    List<Message> getMessagesBySender(User user);
}
