package Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import RequestHandling.*;

public class Server extends Thread {
	private int port;
	public Server(int port) {
		this.port=port;
	}
	public void run() {
		ServerSocket ss=null;
		try {
			ss=new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			Socket s=null;
			DataInputStream dis=null;
			DataOutputStream dos=null;
			String RequestPattern=null;
			try{
				s=ss.accept();
				dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
				dos=new DataOutputStream(s.getOutputStream());
				RequestPattern=dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			System.out.println("Tipo di richiesta: "+RequestPattern);
			switch(RequestPattern) {
			case "Login":
				new Login(dis,dos).start();
				break;
			case "Register":
				new Register(dis,dos).start();
				break;
			case "CategoryList":
				new CategoryList(dis,dos).start();
				break;
			case "FoodList":
				new FoodList(dis,dos).start();
				break;
			case "CommentDish":
				new CommentDish(dis,dos).start();
				break;
			case "CommentList":
				new CommentList(dis,dos).start();
				break;
			case "AddToCart":
				new AddToCart(dis,dos).start();
				break;
			case "CartList":
				new CartList(dis,dos).start();
				break;
			case "UpdateCart":
				new UpdateCart(dis,dos).start();
				break;
			case "DeleteCart":
				new DeleteCart(dis,dos).start();
				break;
			case "DeleteSingleCart":
				new DeleteSingleCart(dis,dos).start();
				break;
			case "Checkout":
				new Checkout(dis,dos).start();
				break;
			case "TimesOrderedByDish":
				new TimesOrderedByDish(dis,dos).start();
				break;
			case "PhoneList":
				new PhoneList(dis,dos).start();
				break;
			}
		}
	}
}
