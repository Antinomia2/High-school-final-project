package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import Common.Methods;
import Common.Variables;
import Object.Cart;
import Object.RegisterInfo;

public class AddToCart extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public AddToCart(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String json=dis.readUTF();
			Cart c=new Gson().fromJson(json,Cart.class);
			String sql=String.format("insert into aggiunge_al_carrello values(%d,(select codicec from cliente where username='%s'),'%s');", c.getQuantity(),c.getUsername(),c.getDish());
			Variables.getStmt().executeUpdate(sql);
			System.out.println("Il cliente aggiunge un articolo nel carrello:");
			System.out.println(json);
			System.out.println(sql+"\n");
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
