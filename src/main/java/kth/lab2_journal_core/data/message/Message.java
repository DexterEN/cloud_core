package kth.lab2_journal_core.data.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "T_Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime time;


    @Column(name = "sender_email", nullable = true)
    private String sender;


    @Column(name = "receiver_email",nullable = true)
    private String receiver;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = true)
    @JsonIgnore
    private Message previousMessage;

    @Column(nullable = true)
    private String imagePath;

    // To store responses to a message, create a one-to-many relationship with responses
    @OneToMany(mappedBy = "previousMessage")
    private List<Message> responses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Message getPreviousMessage() {
        return previousMessage;
    }

    public void setPreviousMessage(Message previousMessage) {
        this.previousMessage = previousMessage;
    }

    public List<Message> getResponses() {
        return responses;
    }

    public void setResponses(List<Message> responses) {
        this.responses = responses;
    }

    public String getImagePath() {return imagePath;}

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
