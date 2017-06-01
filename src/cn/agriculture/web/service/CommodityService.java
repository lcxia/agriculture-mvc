package cn.agriculture.web.service;

import java.sql.SQLException;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.agriculture.web.form.CommodityForm;

@Service
public class CommodityService {
	@Autowired
	QueryDAO queryDao;

	@Autowired
	UpdateDAO updateDao;

	@Autowired
	Cache pictureCache;

	public List<CommodityForm> searchCommodityList() {
		List<CommodityForm> result = queryDao.executeForObjectList(
				"Commodity.selectCommodityList", null);
		return result;
	}

	public CommodityForm searchCommodity(CommodityForm frm) {
		CommodityForm result = queryDao.executeForObject(
				"Commodity.selectCommodity", frm, CommodityForm.class);
		return result;
	}

	public boolean addCommodity(CommodityForm frm) {
		if (frm.getPicture().length != 0) {
			Integer sequee0 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId0 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee0));
			frm.setPictureId(commodityId0);
			int picResult = updateDao.execute("Commodity.addPicture", frm);
			if (picResult != 1) {
				return false;
			}
		}

		if (frm.getPicture1().length != 0) {
			Integer sequee1 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId1 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee1));
			frm.setPictureId1(commodityId1);
			int picResult1 = updateDao.execute("Commodity.addPicture1", frm);
			if (picResult1 != 1) {
				return false;
			}
		}

		if (frm.getPicture2().length != 0) {
			Integer sequee2 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId2 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee2));
			frm.setPictureId2(commodityId2);
			int picResult2 = updateDao.execute("Commodity.addPicture2", frm);
			if (picResult2 != 1) {
				return false;
			}
		}

		if (frm.getPicture3().length != 0) {
			Integer sequee3 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId3 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee3));
			frm.setPictureId3(commodityId3);
			int picResult3 = updateDao.execute("Commodity.addPicture3", frm);
			if (picResult3 != 1) {
				return false;
			}
		}

		if (frm.getPicture4().length != 0) {
			Integer sequee4 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId4 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee4));
			frm.setPictureId4(commodityId4);
			int picResult4 = updateDao.execute("Commodity.addPicture4", frm);
			if (picResult4 != 1) {
				return false;
			}
		}

		if (frm.getPicture5().length != 0) {
			Integer sequee5 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId5 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee5));
			frm.setPictureId5(commodityId5);
			int picResult5 = updateDao.execute("Commodity.addPicture5", frm);
			if (picResult5 != 1) {
				return false;
			}
		}

		if (frm.getPicture6().length != 0) {
			Integer sequee6 = queryDao.executeForObject("Commodity.getSeq1",
					null, Integer.class);
			String commodityId6 = (String) (frm.getUpdateTime().substring(0, 4) + String
					.format("%011d", sequee6));
			frm.setPictureId6(commodityId6);
			int picResult6 = updateDao.execute("Commodity.addPicture6", frm);
			if (picResult6 != 1) {
				return false;
			}
		}
		Integer sequee = queryDao.executeForObject("Commodity.getSeq", null,
				Integer.class);
		String commodityId = (String) (frm.getUpdateTime().substring(0, 4) + String
				.format("%011d", sequee));
		frm.setCommodityId(commodityId);
		int result = updateDao.execute("Commodity.addCommodity", frm);
		if (result == 1) {
			return true;
		}

		return false;
	}

	public boolean editCommodity(CommodityForm frm) {
		Integer sequee;
		if (frm.getPicture().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId(pictureId);
				picResult = updateDao.execute("Commodity.addPicture", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture", frm);
			}
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId());
				pictureCache.flush();
			}
		}
		if (frm.getPicture1().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId1())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId1 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId1(pictureId1);
				picResult = updateDao.execute("Commodity.addPicture1", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture1", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId1());
				pictureCache.flush();
			}
		}
		if (frm.getPicture2().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId2())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId2 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId2(pictureId2);
				picResult = updateDao.execute("Commodity.addPicture2", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture2", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId2());
				pictureCache.flush();
			}
		}
		if (frm.getPicture3().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId3())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId3 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId3(pictureId3);
				picResult = updateDao.execute("Commodity.addPicture3", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture3", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId3());
				pictureCache.flush();
			}
		}
		if (frm.getPicture4().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId4())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId4 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId4(pictureId4);
				picResult = updateDao.execute("Commodity.addPicture4", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture4", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId4());
				pictureCache.flush();
			}
		}
		if (frm.getPicture5().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId5())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId5 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId5(pictureId5);
				picResult = updateDao.execute("Commodity.addPicture5", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture5", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId5());
				pictureCache.flush();
			}
		}
		if (frm.getPicture6().length != 0) {
			int picResult = 0;
			if (StringUtils.isEmpty(frm.getPictureId6())) {
				sequee = queryDao.executeForObject("Commodity.getSeq1", null,
						Integer.class);
				String pictureId6 = frm.getUpdateTime().substring(0, 4)
						+ String.format("%011d", sequee);
				frm.setPictureId6(pictureId6);
				picResult = updateDao.execute("Commodity.addPicture6", frm);
			} else {
				picResult = updateDao.execute("Commodity.editPicture6", frm);
			}

			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId6());
				pictureCache.flush();
			}
		}
		int result = updateDao.execute("Commodity.editCommodity", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean delCommodity(CommodityForm frm) {
		frm = queryDao.executeForObject("Commodity.selectPictureId", frm,
				CommodityForm.class);
		if (!StringUtils.isEmpty(frm.getPictureId())) {
			int picResult = updateDao.execute("Commodity.delPicture", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId1())) {
			int picResult = updateDao.execute("Commodity.delPicture1", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId1());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId2())) {
			int picResult = updateDao.execute("Commodity.delPicture2", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId2());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId3())) {
			int picResult = updateDao.execute("Commodity.delPicture3", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId3());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId4())) {
			int picResult = updateDao.execute("Commodity.delPicture4", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId4());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId5())) {
			int picResult = updateDao.execute("Commodity.delPicture5", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId5());
				pictureCache.flush();
			}
		}
		if (!StringUtils.isEmpty(frm.getPictureId6())) {
			int picResult = updateDao.execute("Commodity.delPicture6", frm);
			if (picResult != 1) {
				return false;
			} else {

				pictureCache.remove(frm.getPictureId6());
				pictureCache.flush();
			}
		}
		updateDao.execute("Commodity.delStock", frm);
		int result = updateDao.execute("Commodity.delCommodity", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public byte[] getPicture(CommodityForm frm) throws SQLException {
		if (pictureCache.get(frm.getPictureId()) == null) {
			frm = queryDao.executeForObject("Commodity.selectPicture", frm,
					CommodityForm.class);
			if (frm != null) {
				Element element = new Element(frm.getPictureId(),
						frm.getPicture());
				pictureCache.put(element);
				pictureCache.flush();
				System.out.println("从DB取得图片");
				return frm.getPicture();
			}
			return null;
		} else {
			System.out.println("从缓存取得图片");
			return (byte[]) pictureCache.get(frm.getPictureId())
					.getObjectValue();
		}
	}
}
