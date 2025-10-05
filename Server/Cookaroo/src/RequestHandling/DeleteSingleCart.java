package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import Common.Variables;
import Object.Cart;

public class DeleteSingleCart extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public DeleteSingleCart(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String json=dis.readUTF();
			Cart c=new Gson().fromJson(json,Cart.class);
			String sql=String.format("delete from aggiunge_al_carrello where cliente=(select codicec from cliente where username='%s') and piatto='%s' and quantita='%s';", c.getUsername(),c.getDish(),c.getQuantity());
			Variables.getStmt().executeUpdate(sql);
			System.out.println("Il cliente richiede di rimuovere un singolo articolo dal carrello:");
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
