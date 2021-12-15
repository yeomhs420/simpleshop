package com.javalec.ex;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.ex.dao.IDao;
import com.javalec.ex.util.staticTemplate;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class BController {
	
	public JdbcTemplate template;
	
	@Autowired	//Bean 객체와 같은 타입의 객체를 찾아서 자동으로 주입
	public void setTemplate(JdbcTemplate template) {	
		this.template = template;
		staticTemplate.template = this.template;
	}
	
	@Autowired
	private SqlSession sqlSession;	//������ �������Ͽ��� ������ �� �Ҵ�
	
	BCommand command;
	
	@RequestMapping("/login")
	public String list(Model model) {
		
		return "login";
	}
	
	@RequestMapping("/simpleshop")
	public String shop(HttpServletRequest request, Model model) {
	
		return "simpleshop";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request, Model model) {
		
		System.out.println("mypage!");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("contents",dao.ContentDao(request.getParameter("id")));
		
		
		return "mypage";
	}
	@RequestMapping("/join")
	public String join(Model model) {
		return "join";
	}
	@RequestMapping("/joinOk")
	public String joinOk(Model model) {
		return "joinOk";
	}
	
	@RequestMapping("/loginOk")
	public String loginOk(Model model) {
		
		return "loginOk";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		
		
		
		return "logout";
	}
	
	@RequestMapping("/charge")
	public String charge(Model model) {
		
		
		return "charge";
	}
	
	@RequestMapping("/chargemoney")
	public String chargemoney(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BChargeCommand();
		command.execute(model);
		
		return "redirect:mypage?id="+request.getParameter("id");
	}
	
	@RequestMapping("/buyOk")
	public String buyOk(Model model) {
		
		
		return "buyOk";
	}
	
	@RequestMapping("/insertitem")
	public String insertitem(Model model, HttpServletRequest request) {
		
		return "insertOk";
	}
	
	@RequestMapping("/cart")
	public String cart(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BImgContentCommand();
		command.execute(model);
		
		return "cart";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model, HttpServletRequest request) {
		
		return "modify";
	}
	@RequestMapping("/modifyOk")
	public String modifyOk(Model model, HttpServletRequest request) {
		
		return "modifyOk";
	}
}
