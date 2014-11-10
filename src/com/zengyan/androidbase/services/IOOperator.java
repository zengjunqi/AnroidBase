package com.zengyan.androidbase.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOOperator {

	public static Boolean writeFile(File dir,String filename, String text) {

		File file = new File(dir,filename);

		try {
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(text.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String readerFile(File dir,String filename) {
		StringBuilder builder = new StringBuilder();
		try {
			File file = new File(dir,filename);

			FileInputStream fileInputStream = new FileInputStream(file);
			 String msg = null; 
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileInputStream));

			while ((msg=bufferedReader.readLine()) != null) {
				builder.append(msg);

			}
			fileInputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder.toString();
	}

}
