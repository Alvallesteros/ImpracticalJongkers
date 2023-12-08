package app.components;

import java.util.List;

import app.entities.Message;
import app.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterComponent {

    @Autowired
    private MessageRepository msgRepo;
    public String send(String contactNo, String msg) {
        Message message = new Message();
        message.setContactNo(contactNo);
        message.setMessage(msg);

        message = msgRepo.save(message);

        return "Sent message " + message;
    }
	
}
