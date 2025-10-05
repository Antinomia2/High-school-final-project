package Server;

public class Main {
	public static void main(String[] args) {
		System.out.println("Avvio Server\n");
		new Server(5000).run();
	}
}