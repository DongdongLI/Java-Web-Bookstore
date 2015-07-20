package com.dongdong.bookstore.test;

public class ThreadLocalTest implements Runnable{
	
	String name=null;
	int i=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;i<10;i++){
			name=Thread.currentThread().getName();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+": "+name);
		}
	}
	public static void main(String[] args){
		ThreadLocalTest tlt=new ThreadLocalTest();
		
		new Thread(tlt,"AAAA").start();
		new Thread(tlt,"BBBB").start();
		
	}
	
}
/*
 * AAAA: BBBB
BBBB: AAAA
AAAA: BBBB
BBBB: AAAA
BBBB: BBBB
AAAA: BBBB
BBBB: AAAA
AAAA: AAAA
BBBB: AAAA
AAAA: BBBB
BBBB: BBBB*/
 