package com.company.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class StreamTest {

	public static void main(String[] args) throws Exception {

		/*
		 * FileReader fr = new FileReader("D:\\Temp\\sample.txt"); int c; while((c =
		 * fr.read()) != -1){ System.out.println(c); } fr.close();
		 */
		//text파일은(문자) reader image파일은(byte) stream
		//BufferedReader br = new BufferedReader(new FileReader("D:\\temp\\sample.txt"));
		//BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\temp\\sample2.txt"));
		BufferedInputStream br = new BufferedInputStream(new FileInputStream("D:\\temp\\image.jpg"));
		BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("D:\\temp\\image2.jpg"));
		
		//String line;
		int cnt;
		byte[] b = new byte[100];
		while (true) { //true(무한루프)
			cnt = br.read(b);
			if(cnt == -1) break;
			bw.write(b);
			//파일복사
		}
		br.close();
		bw.close();
		//내용을 읽었으면 읽어낸 내용의 String값을 읽어냄
	}

}
