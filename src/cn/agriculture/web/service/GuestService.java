package cn.agriculture.web.service;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.common.util.MD5Util;
import cn.agriculture.web.form.GuestForm;

@Service
public class GuestService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;

	public GuestForm searchGuest(GuestForm frm) {
		GuestForm param = new GuestForm();
		param.setGuestId(frm.getGuestId());
		param.setPassword(MD5Util.getMD5(frm.getPassword()));
		GuestForm result = queryDao.executeForObject("Guest.selectGuest", param, GuestForm.class);
		return result;
	}
	
	public GuestForm searchAddressId(GuestForm guestForm){
		return queryDao.executeForObject("Guest.selectAddressId", guestForm,GuestForm.class);
	}
	public boolean addGuest(GuestForm frm) {
		GuestForm guestForm = new GuestForm();
		guestForm.setEmail(frm.getEmail());
		guestForm.setGender(frm.getGender());
		guestForm.setGuestId(frm.getGuestId());
		guestForm.setGuestName(frm.getGuestName());
		guestForm.setMobile(frm.getMobile());
		guestForm.setPassword(MD5Util.getMD5(frm.getPassword()));
		guestForm.setPhone(frm.getPhone());
		guestForm.setQq(frm.getQq());
		guestForm.setUpdateTime(frm.getUpdateTime());
		guestForm.setUpdateUser(frm.getUpdateUser());
		guestForm.setZip(frm.getZip());
		try {
			int result = updateDao.execute("Guest.addGuest", guestForm);
			if (result == 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean editGuest(GuestForm frm) {
		frm.setPassword(MD5Util.getMD5(frm.getPassword()));
		int result = updateDao.execute("Guest.editGuest", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	public boolean editGuestAddressId(GuestForm frm) {
		int result = updateDao.execute("Guest.editGuestAddressId", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
