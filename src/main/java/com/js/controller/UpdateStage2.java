package com.js.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.CustomerCRUD;
import com.js.dao.ProductCRUD;
import com.js.dto.Product;

public class UpdateStage2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product p=new Product();
		int id=Integer.parseInt(req.getParameter("id");
		p.setId(id);
		String name=req.getParameter("name");
		p.setName(name);
		String brand=req.getParameter("brand");
		p.setBrand(brand);
		double price=Double.parseDouble(req.getParameter("price");
		p.setPrice(price);
		int quality=Integer.parseInt(req.getParameter("quality"));
		p.setQuality(quality);
		
		ProductCRUD pc=new ProductCRUD();
		int res= pc.updateProduct(p.getId())
		RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
		if(res>0) {
			RequestDispatcher rd=req.getRequestDispatcher("view");
			rd.forward(req, resp);
			
		}else {
			RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			req.setAttribute("msg", "Failed to Update Product");
			rd.forward(req, resp);
			
		}
		
		
	}
	
	


	}

}