package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import Common.Variables;
import Object.Valutation;

public class CommentDish extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public CommentDish(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rs=null;
		try {
			String json=dis.readUTF();
			Valutation v=new Gson().fromJson(json,Valutation.class);
			String sqlcomment=String.format("insert into commenta values('%s',%d,(select codicec from cliente where username='%s'),'%s');",v.getComment(),v.getGrade(),v.getUsername(),v.getDish());
			String sqlUpdateAVG=String.format("update piatto set avg_valutazione=(select avg(valutazione) from commenta where piatto='%s') where codicep='%s';", v.getDish(),v.getDish());
			String sqlGetAVG=String.format("select avg_valutazione from piatto where codicep='%s';",v.getDish());
			Variables.getStmt().executeUpdate(sqlcomment);
			Variables.getStmt().execute(sqlUpdateAVG);
			rs=Variables.getStmt().executeQuery(sqlGetAVG);
			if(rs.next())
				dos.writeFloat(rs.getFloat("avg_valutazione"));
			System.out.println("Il cliente vuole commentare un piatto:");
			System.out.println(json);
			System.out.println(sqlcomment);
			System.out.println("Rimediare il voto medio di gradimento del piatto:");
			System.out.println(sqlUpdateAVG);
			System.out.println("Restituire il nuovo voto medio di gradimento al client:");
			System.out.println(sqlGetAVG+"\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} finally {
			try {
				rs.close();
				dos.close();
				dis.close();
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
	}
}
