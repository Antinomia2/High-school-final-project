package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import Common.Variables;
import Object.CartItem;
import Object.Valutation;

public class CartList extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public CartList(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rs=null;
		try {
			String user=dis.readUTF();
			String sql=String.format("select piatto.nome,piatto.image,aggiunge_al_carrello.quantita,aggiunge_al_carrello.quantita*(select prezzo from piatto where codicep=aggiunge_al_carrello.piatto) as subtotale,codicep from piatto,aggiunge_al_carrello where aggiunge_al_carrello.piatto=piatto.codicep and aggiunge_al_carrello.cliente=(select codicec from cliente where username='%s');", user);
			rs=Variables.getStmt().executeQuery(sql);
			ArrayList<CartItem> list=new ArrayList<CartItem>();
			while(rs.next())
				list.add(new CartItem(rs.getString("nome"),rs.getString("image"),rs.getInt("quantita"),rs.getFloat("subtotale"),rs.getString("codicep")));
			String json=new Gson().toJson(list);
			System.out.println("Il cliente vuole ottenere la lista di articoli nel carrello:");
			System.out.println(sql);
			System.out.println(json+"\n");
			dos.writeUTF(json);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
