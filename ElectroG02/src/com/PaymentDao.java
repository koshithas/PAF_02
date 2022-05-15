package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentDao {
	
	public String insertPayment(String email, float amount, String cardType, String cardNumber, String expDate,String cvv,int cardPaymentID)
	{ 
		Connection con = DBConnection.connect();
		String output = "";
		try
		 { 
			  
			 if (con == null) 
			 { 
			    return "Error while connecting to the database"; 
			 } 
			 
			 // create a prepared statement
			 String query = " insert into cardpayment (`email`,`amount`,`cardType`,`cardNumber`,`expDate`,`cvv`,`cardPaymentID`)"+ " values (?, ?, ?, ?, ?,?,?)"; 
			 PreparedStatement Pstatement = con.prepareStatement(query); 
			 
			 // binding values
			 Pstatement.setString(1, email); 
			 Pstatement.setFloat(2, amount); 
			 Pstatement.setString(3, cardType); 
			 Pstatement.setString(4, cardNumber); 
			 Pstatement.setString(5, expDate);
			 Pstatement.setString(6, cvv);
			 Pstatement.setInt(7,cardPaymentID);
			 
			 
			//execute the statement
			 
			 Pstatement.execute(); 
			 con.close();
			 //System.out.println(query);
			 String newPayment = viewPayment(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPayment + "\"}"; 
			 
			
		 } 
		
		catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting .\"}"; 
			
			 
			System.err.println(e.getMessage()); 
		 } 
		//binding values
		return output; 
	}

	
	//Read the bill
	public String viewPayment()
	{ 
		 String output = ""; 
		 
		 try
		 { 
		
	     Connection con = DBConnection.connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for reading the Payment..."; 
		 } 
		 
		 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"
					+ "<th>Email</th>"
					+ "<th>Amount</th>"
					+ "<th>Card Type</th>"
					+ "<th>Payment Id</th>"
					+ "</tr> ";
				/*	+ "<th>Card Number</th>"
					+ "<th>Exp Month</th>"
					+ " <th>Exp Year</th>"
					+ "<th>CVV</th>*/

			//Quarry statement for select all in table
			String query = "select * from cardpayment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				//String accountNumber = rs.getString(1);
				float amount = rs.getFloat(2);
				String email = rs.getString(1);
				String cardType = rs.getString(4);
//				String cardNumber = rs.getString(5);
//				String expMonth = rs.getString(6);
//				String expYear = rs.getString(7);
//				String cvv = rs.getString(8);
				int paymentId = rs.getInt(7);
				

				// Add into the html table
				output += "<td>" + email + "</td>";
				output += "<td>" + amount + "</td>";
				
				output += "<td>" + cardType + "</td>";
//				output += "<td>" + cardNumber + "</td>";
//				output += "<td>" + expMonth + "</td>";
//				output += "<td>" + expYear + "</td>";
//				output += "<td>" + cvv + "</td>";
				output += "<td>" + paymentId + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-billid='" + email  + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-billid='" + email + "'></td></tr>";
			 } 
			 
			con.close(); 
			
			     // Complete the html table
			     output += "</table>"; 
			 } 
			 
			catch (Exception e) 
			 { 
				 output = "Error while reading the details."; 
				 System.err.println(e.getMessage()); 
			 } 
			
			
			return output; 
		}
	
	// Update buyers in the table
	public String updatePayment(String email, String cardType, String cardNumber, String expDate,String cvv)
			{ 
				 String output = ""; 
				 try
				 { 
				 Connection con = DBConnection.connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating the Payment."; 
					 
				 } 
				 // create a prepared statement
				 String query = "UPDATE cardpayment SET cardType=?, cardNumber=?,  expDate=?, cvv=? WHERE email=?";
					
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setString(1, cardType); 
				 preparedStmt.setString(2, cardNumber); 
				 preparedStmt.setString(3, expDate); 
				 preparedStmt.setString(4, cvv); 
				 preparedStmt.setString(5, email); 
				 
				 
				 // execute the statement
				    preparedStmt.execute(); 
				    con.close(); 
				    String newPayment = viewPayment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
							 newPayment + "\"}"; 
					 
			 
				 } 
				 
				 catch (Exception e) 
				 { 
					 output = "{\"status\":\"error\", \"data\": \"Error while Updating the card payment details.\"}"; 
				    
				     System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
				 }
	
	public String deletePayment(String email) {
		String output = "";

		try {
			Connection con = DBConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for deleting ..";
			}

			// create a prepared statement
			String query = "delete from cardpayment where email=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, email);

			// execute the statement
			preparedStmt.execute();
			con.close();

			  String newP = viewPayment(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
						 newP + "\"}"; 
		

		} catch (Exception e) {
			 output = "{\"status\":\"error\", \"data\": \"Error while Deleting the card payment details.\"}"; 
		
			System.err.println(e.getMessage());
		}

		return output;
	}


}
