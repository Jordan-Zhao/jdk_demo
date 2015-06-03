
public class MainThread {
	private int abc=12;
	private static int c1 = 1;
	private static int c2 = 1;
	private static final String lock="lock1";
	
	private static Runnable r1 = new Runnable() {
		public void run(){
			while(true){
				synchronized (lock) {
					System.out.println("c1:"+c1);
					c1++;
					try{
//						Thread.sleep(1000*5);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	};
	
	private static Runnable r2 = new Runnable() {
		public void run() {
			while(true){
				synchronized (lock) {
					System.out.println("c2:"+c2);
					c2++;
				}
				try{
//					Thread.sleep(1000*3);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	
	public static void main(String[] arg) throws Exception{
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
