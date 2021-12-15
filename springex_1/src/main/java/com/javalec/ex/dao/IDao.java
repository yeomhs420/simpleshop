package com.javalec.ex.dao;

import java.util.ArrayList;

import com.javalec.ex.dto.MemberDto;

public interface IDao {
	
	public MemberDto ContentDao(String id);
	public void Chargemoney(int money, String id);
	
}
