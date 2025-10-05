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
import Object.OrderedTimes;

public class TimesOrderedByDish extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public TimesOrderedByDish(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rs=null;
		try {
			String username=dis.readUTF();
			String sql=String.format("select piatto.nome,subordine.quantita from piatto,subordine,ordine,cliente where subordine.piatto=piatto.codicep and subordine.ordine=ordine.codiceo and ordine.cliente=(select codicec from cliente where username='%s') group by subordine.piatto;", username);
			rs=Variables.getStmt().executeQuery(sql);
			ArrayList<OrderedTimes> list=new ArrayList<OrderedTimes>();
			while(rs.next())
				list.add(new OrderedTimes(rs.getString("nome"),rs.getInt("quantita")));
			String json=new Gson().toJson(list);
			dos.writeUTF(json);
			System.out.println("Il cliente vuole quante quali piatti ha ordinato di piu:");
			System.out.println(sql);
			System.out.println(json+"\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				dos.close();
				dis.close();
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
