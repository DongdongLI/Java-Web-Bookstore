package com.dongdong.bookstore.test;

public class ThreadLocalTest2 implements Runnable{
	ThreadLocal<String> threadLocal=new ThreadLocal<>();
	int i=0;
	@Override
	public void run() {
		for(;i<10;i++){
			threadLocal.set(Thread.currentThread().getName());
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
		}
	}
	public static void main(String[] args){

		ThreadLocalTest2 tlt=new ThreadLocalTest2();
		
		new Thread(tlt,"AAAA").start();
		new Thread(tlt,"BBBB").start();
	}
	
}
/*BBBB:BBBB
AAAA:AAAA
BBBB:BBBB
AAAA:AAAA
BBBB:BBBB
AAAA:AAAA
BBBB:BBBB
AAAA:AAAA
BBBB:BBBB
AAAA:AAAA
*/