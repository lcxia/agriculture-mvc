package cn.agriculture.web.service;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.common.util.MD5Util;
import cn.agriculture.web.form.UserForm;

@Service
public class UserService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public UserForm searchUser(UserForm frm) {
		UserForm param = new UserForm();
		param.setUserId(frm.getUserId());
		param.setPassword(MD5Util.getMD5(frm.getPassword()));
		UserForm result = queryDao.executeForObject("User.selectUser", param, UserForm.class);
		return result;
	}
	
	public UserForm searchDistributor(UserForm frm) {
		UserForm param = new UserForm();
		param.setUserId(frm.getUserId());
		param.setPassword(MD5Util.getMD5(frm.getPassword()));
		UserForm result = queryDao.executeForObject("User.selectDistributor", param, UserForm.class);
		return result;
	}
	
	
	public boolean addUser(UserForm frm) {
		frm.setPassword(MD5Util.getMD5(frm.getPassword()));
		int result = updateDao.execute("User.addUser", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editUser(UserForm frm) {
		frm.setPassword(MD5Util.getMD5(frm.getPassword()));
		int result = updateDao.execute("User.editUser", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
