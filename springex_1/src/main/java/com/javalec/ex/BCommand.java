package com.javalec.ex;

import org.springframework.ui.Model;

public interface BCommand {
	void execute(Model model);	//파라미터로 model 전달
}
