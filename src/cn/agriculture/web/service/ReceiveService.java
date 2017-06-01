package cn.agriculture.web.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.GuestForm;
import cn.agriculture.web.form.ReceiveForm;

@Service
public class ReceiveService {
	@Autowired
	QueryDAO queryDao;
	@Autowired
	UpdateDAO updateDao;
	public List<ReceiveForm> searchlist(ReceiveForm frm){
		return queryDao.executeForObjectList("Receive.selectReceiveAddresslist", frm);
	}
    public boolean insertReceive(ReceiveForm frm) throws SQLException{
    	Integer addressId=queryDao.executeForObject("Receive.getAddressId", null, java.lang.Integer.class);
    	Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String addressId1=dateformat.format(date).toString().substring(0, 4)+String.format("%011d", addressId);
    	frm.setAddressId(addressId1);
    	int result= updateDao.execute("Receive.insertReceive", frm);
    	if(result!=1)
    	{
    	return false;	
    	}
    	if("on".equals(frm.getCheck())){
    		int result1=updateDao.execute("Receive.updateDefaultaddress", frm);
    		if(result1!=1){
    			return false;
    		}
    	}
    	return true;
    }
    
    
    
    
    public ReceiveForm searchReceive(ReceiveForm frm){
		return queryDao.executeForObject("Receive.selectReceiveAddress", frm,ReceiveForm.class);
    }
	public int updateAddress(ReceiveForm frm) {
		if("on".equals(frm.getCheck()))
		{   GuestForm guestForm=new GuestForm();
		    guestForm.setGuestId(frm.getGuestId());
		    guestForm.setAddressId(frm.getAddressId());
		    int result= updateDao.execute("Guest.editGuestAddressId", guestForm);
		    if(result!=1)
				 return result;
		}
		return updateDao.execute("Receive.updateAddress", frm);
	}
	
	public int deleteAddress(ReceiveForm frm) {
		return updateDao.execute("Receive.deleteAddress", frm);
	}
}
