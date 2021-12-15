package com.javalec.ex;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javalec.ex.dao.MemberDao;

public class BChargeCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();	//�� ��ĵ
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int money = Integer.parseInt(request.getParameter("money")); //getParameter는 항상 String 형태로 반환
		String id = request.getParameter("id");
		
		MemberDao dao = MemberDao.getInstance();
		dao.chargemoney(money, id);

	}

}
