package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.GoodsForm;

@Service
public class GoodsService {
	
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<GoodsForm> searchGoodsList(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListLimit", frm);
		return result;
	}
	public List<GoodsForm> searchGoodsListLimit(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListLimit", frm,0,6);
		return result;
	}
	public List<GoodsForm> searchGoodsListrelative(GoodsForm frm) {
		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListrelative", frm);
		return result;
	}
	
	public GoodsForm searchGoods(GoodsForm frm) {
		GoodsForm result = queryDao.executeForObject("Goods.selectGoods", frm, GoodsForm.class);
		return result;
	}
	
	public List<GoodsForm> getType() {
		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectType", null);
		return result;
	}
	
	public List<GoodsForm> getTypeList(GoodsForm goodsForm) {
		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectTypeList", goodsForm);
		return result;
	}
	public List<GoodsForm> searchGoodsListOrder() {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListOder", null,0,4);
		return result;
	}
	public List<GoodsForm> searchGoodsListByPriceDesc(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListByPriceDesc", frm);
		return result;
	}
	public List<GoodsForm> searchGoodsListByPrice(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListByPrice", frm);
		return result;
	}
	public List<GoodsForm> searchGoodsListByPopularDesc(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListByPopularDesc", frm);
		return result;
	}
	public List<GoodsForm> searchGoodsListByPopular(GoodsForm frm) {

		List<GoodsForm> result = queryDao.executeForObjectList("Goods.selectGoodsListByPopular", frm);
		return result;
	}
}
