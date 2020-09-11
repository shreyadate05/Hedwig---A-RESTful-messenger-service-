package org.sdate.services.Hedwig.service;

import org.sdate.services.Hedwig.model.Message;
import org.sdate.services.Hedwig.database.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		//messages.put(1L, new Message(1, "Hello World!", "Shreya"));
		//messages.put(2L, new Message(2, "Hello Jersey!", "Shreya"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList();
		Calendar cal = Calendar.getInstance();
		
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList(messages.values());
		if (start + size > list.size()) {
			return new ArrayList();
		}
		return list.subList(start, start + size);
	}

	public Message getMessage(Long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
}
