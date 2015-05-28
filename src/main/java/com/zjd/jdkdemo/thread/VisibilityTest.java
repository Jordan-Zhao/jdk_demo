package com.zjd.jdkdemo.thread;

public class VisibilityTest extends Thread {
    private volatile boolean stop;
    public int z=0;
     
    public void run() {
        System.out.println("start loop,i=" + z);
        while(!stop) {
            z++;
//            System.out.println("zzzzzzzzzzz:"+z);
        }
        System.out.println("finish loop,i=" + z);
    }
     
    public void stopIt() {
        stop = true;
    }
     
    public boolean getStop(){
        return stop;
    }
    public static void main(String[] args) throws Exception {
        VisibilityTest v = new VisibilityTest();
        v.start();
         
        Thread.sleep(1000*50);
        System.out.println("准备发送stop"+v.z);
        v.stopIt();
        System.out.println("已发送stop"+v.z);
        Thread.sleep(2000*1000);
        System.out.println("finish main");
        System.out.println(v.getStop());
    }
}
