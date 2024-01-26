package model;

import java.util.List;

import dao.ShohinDAO;

public class GetShohinLogic {

//	商品をSQLから取り出して、リスト化
	public List<Shohin_Customer> execute(){
		ShohinDAO dao = new ShohinDAO();
		List<Shohin_Customer> shohinList = dao.selectAll();
		return shohinList;
	}
	

}
