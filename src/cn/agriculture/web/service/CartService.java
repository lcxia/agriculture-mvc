package cn.agriculture.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.AlipayForm;
import cn.agriculture.web.form.CartForm;

@Service
@PropertySource("classpath:system.properties")
public class CartService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	private Environment env;
	
	public List<CartForm> searchCartList(CartForm frm) {
		frm.setStatus("未付款");
		List<CartForm> result = queryDao.executeForObjectList("Cart.selectCartList", frm);
		return result;
	}
	
	public List<AlipayForm> searchAlipayHistoryList(CartForm frm) {
		List<AlipayForm> result = queryDao.executeForObjectList("Cart.selectAlipayHistoryList", frm);
		return result;
	}
	
	public List<Integer> searchOrderListCount(CartForm frm) {
		Double count = queryDao.executeForObject("Cart.selectAlipayHistoryListCount", frm, Double.class);
		List<Integer> list = new ArrayList<>();
		Integer pages = (int) Math.ceil(count/5);
		for (int i=1; i<=pages; i++) {
			list.add(i);
		}
		return list;
	}
	
	public List<AlipayForm> searchOrderList(CartForm frm, Integer index) {
		List<AlipayForm> result = queryDao.executeForObjectList("Cart.selectAlipayHistoryList", frm, index*5, 5);
		return result;
	}
	
	public AlipayForm searchAlipayHistory(AlipayForm frm) {
		AlipayForm result = queryDao.executeForObject("Cart.selectAlipayHistory", frm, AlipayForm.class);
		return result;
	}
	
	public List<CartForm> searchBuyedCartList(CartForm frm) {
		frm.setStatus("已付款");
		List<CartForm> result = queryDao.executeForObjectList("Cart.selectCartList", frm);
		return result;
	}
	
	public boolean addCart(CartForm frm) {
		frm.setStatus("未付款");
		CartForm cartResult = queryDao.executeForObject("Cart.selectCart", frm, CartForm.class);
		if (cartResult == null) {
			Integer sequee = queryDao.executeForObject("Cart.getSeq", null, Integer.class);
			
			String cartId = env.getProperty("id.prefix.cart") + frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
			frm.setCartId(cartId);
			frm.setStatus("未付款");
			int result = updateDao.execute("Cart.addCart", frm);
			if (result == 1) {
				Integer stock = queryDao.executeForObject("Cart.selectStock", frm, Integer.class);
				stock = stock - Integer.valueOf(frm.getCount());
				if (stock < 0) {
					return false;
				}
				frm.setCount(stock.toString());
				if (updateDao.execute("Cart.editStock", frm) == 1) {
					return true;
				}
				
			}
		} else {
			String tempCount = frm.getCount();
			Integer count = Integer.valueOf(cartResult.getCount()) + Integer.valueOf(frm.getCount());
			frm.setCount(String.valueOf(count));
			frm.setCartId(cartResult.getCartId());
			frm.setStatus("未付款");
			int result = updateDao.execute("Cart.editCart", frm);
			if (result == 1) {
				Integer stock = queryDao.executeForObject("Cart.selectStock", frm, Integer.class);
				if (stock == null) {
					// 新商品没有库存
					return false;
				}
				stock = stock - Integer.valueOf(tempCount);
				if (stock < 0) {
					return false;
				}
				frm.setCount(stock.toString());
				if (updateDao.execute("Cart.editStock", frm) == 1) {
					return true;
				}
				return true;
			}
		}
		
		return false;
	}	
	public boolean delCart(CartForm frm) {
		Integer count = queryDao.executeForObject("Cart.selectCount", frm, Integer.class);
		int result = updateDao.execute("Cart.delCart", frm);
		if (result == 1) {
			Integer stock = queryDao.executeForObject("Cart.selectStock", frm, Integer.class);
			stock = stock + Integer.valueOf(count);
			frm.setCount(stock.toString());
			if (updateDao.execute("Cart.editStock", frm) == 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addAlipayHistory(AlipayForm frm) {
		// 做成支付宝历史单据，为了付款操作不成功时补款用。
		int result = updateDao.execute("Cart.addAlipayHistory", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editCartStatus(CartForm frm) {
		frm.setStatus("已付款");
		int result = updateDao.execute("Cart.updateCartStatus", frm);
		if (result != 0) {
			return true;
		}
		return false;
	}
	
	public boolean editStatus(CartForm frm) {
		frm.setStatus("已付款");
		int result = updateDao.execute("Cart.updateStatus", frm);
		if (result != 0) {
			return true;
		}
		return false;
	}
	
	public AlipayForm searchAlipay(CartForm frm) {
		CartForm cartForm = queryDao.executeForObject("Cart.selectGuestPrice", frm, CartForm.class);
		if (Double.valueOf(frm.getCount()) > Double.valueOf(cartForm.getCount())) {
			return null;
		}
		AlipayForm alipayForm = new AlipayForm();
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		alipayForm.setOutTradeNo(frm.getGuestId() + dateformat.format(date));
		alipayForm.setSubject(frm.getGuestId() +"匿名购买的商品订单");
		Double price = Double.valueOf(frm.getCount()) * Double.valueOf(cartForm.getRetailPrice());
		// 不满88元加8元邮费
		String body;
    	if(price < 88) {
    		price = price + 8;
    		alipayForm.setSubject(alipayForm.getSubject()+ "(由于本次订单未满88元，加收您邮费8元)");
    		body = "品名：" + cartForm.getCommodityName() +", 数量："+ frm.getCount() +", 总价："+ price + "(由于本次订单未满88元，加收您邮费8元)";
    	} else {
    		body = "品名：" + cartForm.getCommodityName() +", 数量："+ frm.getCount() +", 总价："+ price;
    	}
		alipayForm.setPrice(String.valueOf(price));
		alipayForm.setBody(body);
		String host = env.getProperty("host.web");
		alipayForm.setShowUrl(host + "/");
		alipayForm.setGuestId(frm.getGuestId());
		alipayForm.setCommodityId(cartForm.getCommodityId());
		alipayForm.setCount(frm.getCount());
		return alipayForm;
	}

	public AlipayForm searchAlipayImmediately(CartForm frm) {
		CartForm cartForm = queryDao.executeForObject("Cart.selectGuestPrice", frm, CartForm.class);
		if (Double.valueOf(frm.getCount()) > Double.valueOf(cartForm.getCount())) {
			return null;
		}
		AlipayForm alipayForm = new AlipayForm();
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		alipayForm.setOutTradeNo(frm.getGuestId() + dateformat.format(date));
		alipayForm.setSubject(frm.getGuestId() +"立即购买的商品订单");
		Double price = Double.valueOf(frm.getCount()) * Double.valueOf(cartForm.getRetailPrice());
		// 不满88元加8元邮费
		String body;
    	if(price < 88) {
    		price = price + 8;
    		alipayForm.setSubject(alipayForm.getSubject()+ "(由于本次订单未满88元，加收您邮费8元)");
    		body = "品名：" + cartForm.getCommodityName() +", 数量："+ frm.getCount() +", 总价："+ price + "(由于本次订单未满88元，加收您邮费8元)";
    	} else {
    		body = "品名：" + cartForm.getCommodityName() +", 数量："+ frm.getCount() +", 总价："+ price;
    	}

		alipayForm.setPrice(String.valueOf(price));
		alipayForm.setBody(body);
		String host = env.getProperty("host.web");
		alipayForm.setShowUrl(host + "/");
		alipayForm.setGuestId(frm.getGuestId());
		alipayForm.setCommodityId(cartForm.getCommodityId());
		alipayForm.setCount(frm.getCount());
		return alipayForm;
	}
	public int deleteOrder(AlipayForm frm) {
		int result = updateDao.execute("Cart.deleteOrder", frm);
		return result;
	}
	
	public boolean updateCart(CartForm frm) {
		frm.setStatus("未付款");
		int result = updateDao.execute("Cart.updateCart", frm);
		if (result != 0) {
			return true;
		}
		return false;

	}

	public CartForm selectCount(CartForm frm) {
		frm.setStatus("未付款");
		CartForm result = queryDao.executeForObject(
				"Cart.selectCount", frm,CartForm.class);
		return result;
	}

	public List<CartForm> searchCartListForCartId(CartForm frm) {
		frm.setStatus("未付款");
		List<CartForm> result = queryDao.executeForObjectList(
				"Cart.selectCartListForCartId", frm);
		return result;
	}
	       

	public boolean editStockByCart(CartForm frm) {
		frm.setStatus("未付款");
		CartForm cartResult = queryDao.executeForObject("Cart.selectCart", frm,
				CartForm.class);
		if (cartResult == null) {
			;
		} else {
			// String tempCount = frm.getCount();
			Integer count = Integer.valueOf(frm.getCount())
					- Integer.valueOf(cartResult.getCount());
			// frm.setCount(String.valueOf(count));
			frm.setCartId(cartResult.getCartId());
			frm.setStatus("未付款");
			// int result = updateDao.execute("Cart.editCart", frm);
			// if (result == 1) {
			Integer stock = queryDao.executeForObject("Cart.selectStock", frm,
					Integer.class);
			// if (stock == null) {
			// 新商品没有库存
			// return false;
			// }
			stock = stock - Integer.valueOf(count);
			if (stock < 0) {
				return false;
			}
			frm.setStock(stock.toString());
			if (updateDao.execute("Cart.editStockAgain", frm) == 1) {
				return true;
			}
			// return true;
		}

		return false;
	}

}
