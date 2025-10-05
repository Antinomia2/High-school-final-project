package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import Common.Methods;
import Common.Variables;
import Object.Cart;
import Object.Order;

public class Checkout extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public Checkout(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String json=dis.readUTF();
			Order order=new Gson().fromJson(json,Order.class);
			String codiceo=Methods.generateRandomID();
			String sqlordine=String.format("insert into ordine values ('%s','%f','%s',(select codicec from cliente where username='%s'));",codiceo,order.getCostototale(),order.getTempoconsegna(),order.getCliente());
			System.out.println("Il cliente vuole effettuare il pagamento:");
			System.out.println(json);
			System.out.println(sqlordine);
			Variables.getStmt().execute(sqlordine);
			for(int i=0;i<order.getCartItems().length;i++) {
				String sqlsubordine=String.format("insert into subordine values('%s',%d,%f,'%s','%s')",Methods.generateRandomID(),order.getCartItems()[i].getQuantity(),order.getCartItems()[i].getSubtotal(),codiceo,order.getCartItems()[i].getCodicep());
				System.out.println(sqlsubordine);
				Variables.getStmt().executeUpdate(sqlsubordine);
			}
			String sqlclearcart=String.format("delete from aggiunge_al_carrello where cliente=(select codicec from cliente where username='%s')",order.getCliente());
			Variables.getStmt().executeUpdate(sqlclearcart);
			System.out.println("Pagamento avvenuto con successo, si procede col svuotamento del carrello del cliente:");
			System.out.println(sqlclearcart+"\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} finally {
			try {
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
	}
}
