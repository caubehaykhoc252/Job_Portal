package com.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;


@WebServlet("/update_user")
public class update_user extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String qua = req.getParameter("qua");
			String email = req.getParameter("email");
			String ps = req.getParameter("ps");
		
		
			User u = new User();
			u.setId(id);
			u.setName(name);
			u.setQualification(qua);
			u.setPassword(ps);
			u.setEmail(email);
			
			HttpSession session = req.getSession();
			
			UserDAO dao = new UserDAO(DBConnect.getConn());
			boolean f = dao.updateUser(u);
			if(f) {
				session.setAttribute("succMsg", "Cập nhật thông tin thành công!");
				resp.sendRedirect("view_user.jsp");
			} else {
				session.setAttribute("succMsg", "Đã xảy ra lỗi!");
				resp.sendRedirect("edit_user.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
