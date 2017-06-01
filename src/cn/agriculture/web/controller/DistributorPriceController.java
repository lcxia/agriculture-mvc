package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.DistributorForm;
import cn.agriculture.web.form.DistributorPriceForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.DistributorPriceService;
import cn.agriculture.web.service.DistributorService;

@Slf4j
@Controller("DistributorPriceController")
@RequestMapping("/")
public class DistributorPriceController {

	@Autowired
	DistributorPriceService distributorPriceService;
	@Autowired
	DistributorService distributorService;
	
    @RequestMapping(value = "initDistributorPrice", method = RequestMethod.GET)
    public String initDistributorPrice(Model model, DistributorForm distributorForm) {
    	log.info("分销商所属价格列表初始化");
    	
    	DistributorForm result = distributorService.searchDistributorForPrice(distributorForm);
		model.addAttribute("distributorForm", result);
		DistributorPriceForm distributorPriceForm = new DistributorPriceForm();
		distributorPriceForm.setPriceIncrement(result.getPriceIncrement());
		distributorPriceForm.setDistributorId(result.getDistributorId());
    	model.addAttribute("list", distributorPriceService.searchDistributorPriceList(distributorPriceForm));
        return "manager/distributorPrice/distributorPriceList";
    }
    
    @RequestMapping(value = "initDistributorPriceEdit", method = RequestMethod.GET)
    public String initDistributorPriceEdit(Model model, HttpSession session) {
    	log.info("分销商所属价格列表初始化分销商用");
    	UVO uvo = (UVO)session.getAttribute("UVO");
		DistributorPriceForm distributorPriceForm = new DistributorPriceForm();
		distributorPriceForm.setPriceIncrement(uvo.getPriceIncrement());
		distributorPriceForm.setDistributorId(uvo.getUserId());
    	model.addAttribute("list", distributorPriceService.searchDistributorPriceList(distributorPriceForm));
        return "manager/distributorPrice/distributorPriceEditList";
    }
    
    @RequestMapping(value = "initEditDistributorPrice", method = RequestMethod.GET)
    public String initEditDistributorPrice(Model model, HttpSession session, DistributorPriceForm distributorPriceForm) {
    	log.info("修改分销商价格初始化");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	distributorPriceForm.setDistributorId(uvo.getUserId());
    	model.addAttribute("distributorPriceForm", distributorPriceService.searchDistributorPrice(distributorPriceForm));
        return "manager/distributorPrice/editDistributorPrice";
    }
    
    @RequestMapping(value = "editDistributorPrice", method = RequestMethod.POST)
    public String executeEditDistributorPrice(Model model, HttpSession session,@Valid @ModelAttribute("distributorPriceForm") DistributorPriceForm distributorPriceForm, BindingResult results) throws SQLException {
    	if (results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/distributorPrice/editDistributorPrice";
		}
    	log.info("修改分销商价格");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	distributorPriceForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		distributorPriceForm.setUpdateTime(dateformat.format(date));
    	distributorPriceForm.setDistributorId(uvo.getUserId());
    	boolean result = distributorPriceService.editDistributorPrice(distributorPriceForm);
    	if (!result) {
    		model.addAttribute("message", "分销商价格禁止低于指导价！");
    		model.addAttribute("distributorPriceForm", distributorPriceService.searchDistributorPrice(distributorPriceForm));
            return "manager/distributorPrice/editDistributorPrice";
    	}
    	distributorPriceForm.setPriceIncrement(uvo.getPriceIncrement());
    	distributorPriceForm.setDistributorId(uvo.getUserId());
    	model.addAttribute("list", distributorPriceService.searchDistributorPriceList(distributorPriceForm));
        return "manager/distributorPrice/distributorPriceEditList";
    }
}
