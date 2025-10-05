package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Common.Variables;
import Object.SedeInfo;
import Object.Valutation;

public class PhoneList extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public PhoneList(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try(ResultSet rs=Variables.getStmt().executeQuery("select indirizzo,telefono from sedi where nazione='Italia';")) {
			ArrayList<SedeInfo> list=new ArrayList<SedeInfo>();
			while(rs.next())
				list.add(new SedeInfo(rs.getString("indirizzo"),rs.getString("telefono")));
			String json=new Gson().toJson(list);
			dos.writeUTF(json);
			System.out.println("Il cliente richiede la lista dei sedi di ristoranti, e la lista dei numeri di telefono corrispondenti, per la prenotazione del tavolo:");
			System.out.println("select indirizzo,telefono from sedi where nazione='Italia';");
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
