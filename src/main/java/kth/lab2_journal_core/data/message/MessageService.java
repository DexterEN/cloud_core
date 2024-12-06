package kth.lab2_journal_core.data.message;



import kth.lab2_journal_core.data.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Message saveMessage(Message message) {
        // Additional logic can be added here, like password encoding or validation
        return messageRepository.save(message);
    }
    public Message getById(Long id) {
        return messageRepository.getById(id);
    }
    public List<Message> getReceivedMessagesByEmail(User user) {
        return messageRepository.getMessagesByReceiver(user);
    }
    public List<Message> getSentMessagesByEmail(User user) {
        return messageRepository.getMessagesBySender(user);
    }

}
