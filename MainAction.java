package com.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBService;

@WebServlet("/MainAction")
public class MainAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String active = objToStr(request.getParameter("active"));
		String id = objToStr(request.getParameter("id"));
		String name = objToStr(request.getParameter("name"));
		String weight = objToStr(request.getParameter("weight"));
		String height = objToStr(request.getParameter("height"));
		String color = objToStr(request.getParameter("color"));
		String gpa = objToStr(request.getParameter("gpa"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("active", active);
		map.put("id", id);
		map.put("name", name);
		map.put("weight", weight);
		map.put("height", height);
		map.put("color", color);
		map.put("gpa", gpa);
		int ret;
		if("search".equals(active)){
			//search
//			List<Map<String, Object>> resultList = DBService.getAllResult();
//			request.setAttribute("rs", resultList);
		}else if("add".equals(active)){
			//add
			ret = DBService.insert(map);
		}else if("update".equals(active)){
			//update
			ret = DBService.update(map);
		}else if("delete".equals(active)){
			//delete
			ret = DBService.delete(id);
		}
		List<Map<String, Object>> resultList = DBService.getAllResult();
		request.setAttribute("rs", resultList);
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//		response.sendRedirect(basePath+"view/index.jsp");
		request.getRequestDispatcher("view/index.jsp").forward(request, response);
	}
	
	public String objToStr(Object obj){
		String strReturnValue;
		try{
			if(obj != null){
				strReturnValue = String.valueOf(obj);
			}else{
				strReturnValue = "";
			}
		}catch(Exception e){
			strReturnValue = "";
		}
		return strReturnValue;
	}

}
