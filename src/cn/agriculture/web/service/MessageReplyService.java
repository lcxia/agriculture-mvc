package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.MessageReplyForm;

@Service
public class MessageReplyService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<MessageReplyForm> searchMessageReplyList(MessageReplyForm frm) {
		List<MessageReplyForm> result = queryDao.executeForObjectList("MessageReply.selectMessageReplyList", frm);
		return result;
	}
	
	public boolean addMessageReply(MessageReplyForm frm) {
		Integer sequee = queryDao.executeForObject("MessageReply.getSeq", null, Integer.class);
		String messageReplyId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setMessageReplyId(messageReplyId);
		int result = updateDao.execute("MessageReply.addMessageReply", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
