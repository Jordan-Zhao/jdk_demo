
public class MainDump {
	public static native void hello(String str);
	
	static {  
        System.loadLibrary("hello");  
    } 
	
	public static void main(String[] arg){
		hello("abc");
	}
}
