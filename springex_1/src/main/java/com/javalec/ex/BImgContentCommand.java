package com.javalec.ex;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.ex.dao.ProductDao;
import com.javalec.ex.dto.ProductDto;

public class BImgContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();	//�� ��ĵ
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
	
		ProductDao dao = ProductDao.getInstance();
		ArrayList<ProductDto> dtoList = dao.getImagelist(id);
		
		model.addAttribute("DtoList", dtoList);
		
	}

}
