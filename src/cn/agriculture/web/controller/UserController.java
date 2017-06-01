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
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.form.UserForm;
import cn.agriculture.web.service.UserService;

@Slf4j
@Controller("UserController")
@RequestMapping("/")
public class UserController {
	
	@Autowired
	UserService userService;
	
    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String initLogin(Model model) {
    	log.info("系统初始化");
    	model.addAttribute("message", "请输入您的用户名和密码 :");
        return "manager/index";
    }
    
	@RequestMapping(value = "login", method = RequestMethod.POST, params = "login")
    public String login(HttpSession session, UserForm userForm, Model model) {
		log.info("用户登录，验证用户信息，成功后进入系统主菜单");
		if ("on".equals(userForm.getAdmin())) {
			UserForm result = userService.searchUser(userForm);
			if(result != null) {
				UVO uvo = new UVO();
				uvo.setUserId(result.getUserId());
				uvo.setUserName(result.getUserName());
				uvo.setPassword(userForm.getPassword());
				uvo.setIdCard(result.getIdCard());
				uvo.setBirthday(result.getBirthday());
				uvo.setGender(result.getGender());
				uvo.setCompanyName(result.getCompanyName());
				uvo.setAddress(result.getAddress());
				uvo.setEmail(result.getEmail());
				uvo.setTelephone(result.getTelephone());
				uvo.setDepartment(result.getDepartment());
				uvo.setPosition(result.getPosition());
				uvo.setAdmin(userForm.getAdmin());
				session.setAttribute("UVO", uvo);
		        return "manager/menu";
			} else {
				model.addAttribute("message", "用户名或密码错误！");
				return "manager/index";
			}
		} 
		UserForm result = userService.searchDistributor(userForm);
		if(result != null) {
			UVO uvo = new UVO();
			uvo.setUserId(result.getUserId());
			uvo.setUserName(result.getUserName());
			uvo.setPassword(userForm.getPassword());
			uvo.setAddress(result.getAddress());
			uvo.setEmail(result.getEmail());
			uvo.setTelephone(result.getTelephone());
			uvo.setPriceIncrement(result.getPriceIncrement());
			uvo.setAmount(result.getAmount());
			session.setAttribute("UVO", uvo);
	        return "manager/menu";
		} else {
			model.addAttribute("message", "用户名或密码错误！");
			return "manager/index";
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, params = "register")
    public String initRegister(Model model,HttpSession session) {
		log.info("注册分销商信息");
	    UVO uvo=new UVO();
	    session.setAttribute("UVO", uvo);
		DistributorForm distributorForm = new DistributorForm();
		model.addAttribute("distributorForm", distributorForm);
		return "manager/distributor/addDistributorSelf";
	}
	
	@RequestMapping(value = "initEditUser", method = RequestMethod.GET)
	public String initEditUser(HttpSession session, Model model) {
		log.info("修改用户初始化");
		UVO uvo = (UVO)session.getAttribute("UVO");
		UserForm userForm = new UserForm();
		userForm.setUserId(uvo.getUserId());
		userForm.setPassword(uvo.getPassword());
		UserForm result = userService.searchUser(userForm);
		model.addAttribute("userForm", result);
		return "manager/editUser";
	}
	
	@RequestMapping(value = "editUser", method = RequestMethod.POST)
	public String executeEdit(Model model, HttpSession session, @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult results) throws SQLException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/editUser";
		}
		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			log.info("密码验证出错");
			model.addAttribute("message", "密码和密码确认必须一致！");
			return "manager/editUser";
		}
		log.info("修改客户信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		userForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userForm.setUpdateTime(dateformat.format(date));
		boolean result = userService.editUser(userForm);
		if(!result) {
			throw new SQLException("客户信息添加失败！");
		}
		return "manager/menu";
	}
	
	@RequestMapping(value = "initMenu", method = RequestMethod.GET)
	public String initMenu(HttpSession session) {
		log.info("菜单信息初始化");
		UVO uvo = (UVO)session.getAttribute("UVO");
		if (uvo == null) {
			return "manager/index";
		}
		return "manager/menu";
	}
}
