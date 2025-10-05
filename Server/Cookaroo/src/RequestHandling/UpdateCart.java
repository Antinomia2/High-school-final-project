package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import Common.Variables;
import Object.Cart;

public class UpdateCart extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public UpdateCart(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String json=dis.readUTF();
			System.out.println("Il cliente vuole aggiornare gli articoli nel carrello:");
			System.out.println(json);
			Cart[] c=new Gson().fromJson(json,Cart[].class);
			for(int i=0;i<c.length;i++) {
				String sql=String.format("update aggiunge_al_carrello set quantita=%d where cliente=(select codicec from cliente where username='%s') and piatto='%s';",c[i].getQuantity(),c[i].getUsername(),c[i].getDish());
				System.out.println(sql);
				Variables.getStmt().executeUpdate(sql);
			}
			System.out.print("\n");
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
