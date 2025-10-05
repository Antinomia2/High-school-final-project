package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import com.google.gson.Gson;
import Common.Methods;
import Common.Variables;
import Object.RegisterInfo;

public class Register extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public Register(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String json=dis.readUTF();
			RegisterInfo ri=new Gson().fromJson(json,RegisterInfo.class);
			String sql=String.format("insert into cliente values(%s,'%s','%s','%s','%s','%s','%s','%s');","uuid()",ri.getUsername(),Methods.Atbash(ri.getPassword(), 'a', 'z'),ri.getNome(),ri.getCognome(),ri.getTelefono(),ri.getIndirizzo(),"null");
			Variables.getStmt().executeUpdate(sql);
			dos.writeBoolean(false);
			System.out.println("Il cliente richiede la registrazione:");
			System.out.println(json);
			System.out.println(sql);
			System.out.println("Registrazione avvenuto con successo\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			System.out.println("Username duplicato\n");
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
