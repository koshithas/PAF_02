package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PaymentDao;


@WebServlet("/PaymentsAPI")
public class PaymentsAPI  extends HttpServlet{
	
	
	PaymentDao paymentDao = new PaymentDao();
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) {
			Map<String, String> map = new HashMap<String, String>();
			try {
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				String[] params = queryString.split("&");
				for (String param : params) {
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			} catch (Exception e) {
			}
			return map;
		}
		
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);

		
			//Gson gson = new Gson();
			String res;

			String email = request.getParameter("email");
			float amount = Float.valueOf(request.getParameter("amount"));
			String type = request.getParameter("type");
			String number = request.getParameter("number");
			String date = request.getParameter("exp");
			String cvv = request.getParameter("cvv");

			String output = paymentDao.insertPayment(email, amount, type, number, date, cvv, 0);

			// Get items
			String payments = paymentDao.viewPayment();
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			if (output != "error") {
				res = "{\"status\":\"success\", \"data\": \"" + payments + "\"}";
			} else {
				res = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
			}

			response.getWriter().write(output);
		}

		
		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Map paras = getParasMap(request);

			String res;

			String email = paras.get("email").toString();
			String type = paras.get("type").toString();
			
			String date = paras.get("exp").toString();
			String number = paras.get("number").toString();
			String cvv = paras.get("cvv").toString();

			String output =paymentDao.updatePayment(email, type, number, date, cvv);

			// Get items
			String payments = paymentDao.viewPayment();

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			if (output != "error") {
				res = "{\"status\":\"success\", \"data\": \"" + payments + "\"}";
			} else {
				res = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
			}

			response.getWriter().write(res);

		}
		
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Map paras = getParasMap(request);
			String output = paymentDao.deletePayment(paras.get("email").toString());

			String cards = paymentDao.viewPayment();

			if (output != "error") {
				output = "{\"status\":\"success\", \"data\": \"" + cards + "\"}";
			} else {
				output = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
			}
			response.getWriter().write(output);
		}


}
