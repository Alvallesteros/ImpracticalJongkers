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
	// TODO: send message from userside to vendor for orders
    public String send(String contactNo, String msg) {
        Message message = new Message();
        message.setContactNo(contactNo);
        message.setMessage(msg);

        message = msgRepo.save(message);

        return "Sent message " + message;
    }
	
	// TODO: send update of order status from vendor to userside
	
}
