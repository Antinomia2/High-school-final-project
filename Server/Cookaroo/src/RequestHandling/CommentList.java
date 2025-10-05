package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import Common.Variables;
import Object.Category;
import Object.Valutation;

public class CommentList extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public CommentList(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rs=null;
		try {
			String dish=dis.readUTF();
			String sql=String.format("select cliente.username,commenta.commento,commenta.valutazione from cliente,commenta where commenta.cliente=cliente.codicec and commenta.piatto='%s';", dish);
			rs=Variables.getStmt().executeQuery(sql);
			ArrayList<Valutation> list=new ArrayList<Valutation>();
			while(rs.next())
				list.add(new Valutation(rs.getString("username"),null,rs.getString("commento"),rs.getInt("valutazione")));
			String json=new Gson().toJson(list);
			dos.writeUTF(json);
			System.out.println("Il cliente vuole ottenere la lista di commenti per il piatto:");
			System.out.println(sql);
			System.out.println(json+"\n");
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
