package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.MessageForm;

@Service
public class MessageService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<MessageForm> searchMessageList() {
		List<MessageForm> result = queryDao.executeForObjectList("Message.selectMessageList", null);
		return result;
	}
	
	public List<MessageForm> searchMessageList(MessageForm frm) {
		List<MessageForm> result = queryDao.executeForObjectList("Message.selectMessageList", frm);
		return result;
	}
	
	public MessageForm searchMessage(MessageForm frm) {
		MessageForm result = queryDao.executeForObject("Message.selectMessage", frm, MessageForm.class);
		return result;
	}
	
	public boolean addMessage(MessageForm frm) {
		Integer sequee = queryDao.executeForObject("Message.getSeq", null, Integer.class);
		String messageId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setMessageId(messageId);
		int result = updateDao.execute("Message.addMessage", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delMessage(MessageForm frm) {
		updateDao.execute("MessageReply.delMessageReply", frm);
		
		int result = updateDao.execute("Message.delMessage", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
