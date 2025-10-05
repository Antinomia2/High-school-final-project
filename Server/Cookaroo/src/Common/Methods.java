package Common;

public class Methods {
	public static String Atbash(String s,char start,char end) {
		StringBuilder sb=new StringBuilder();
		for(char c:s.toCharArray())
			sb=Character.isLetter(c)?sb.append((char)((start-c)+end)):sb.append(c);
		return sb.toString();
	}
	public static String generateRandomID(){
        String c="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder();
        int length=(int)(Math.random()*5)+8;
        while(sb.length()<length)
        	sb.append(c.charAt((int)(Math.random()*c.length())));
        return sb.toString();
    }
}
