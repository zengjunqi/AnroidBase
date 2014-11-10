package com.zengyan.androidbase.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.integer;
import android.util.Xml;

import com.zengyan.androidbase.model.Student;

public class XmlParser {
	public static List<Student> PullParseXML(InputStream is) {

		List<Student> list = null;
		Student student = null;

		// 构建XmlPullParserFactory
		try {
			XmlPullParser xmlPullParser = Xml.newPullParser();
			xmlPullParser.setInput(is, "utf-8");
			// 开始
			int eventType = xmlPullParser.getEventType();

			try {
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					// 文档开始
					case XmlPullParser.START_DOCUMENT:
						list = new ArrayList<Student>();
						break;
					// 开始节点
					case XmlPullParser.START_TAG:
						// 判断如果其实节点为student
						if ("student".equals(nodeName)) {
							// 实例化student对象
							student = new Student();
							// 设置Id属性
							student.setId(Integer.parseInt(xmlPullParser
									.getAttributeValue(0)));
							// 设置Group属性
							student.setGroup(Integer.parseInt(xmlPullParser
									.getAttributeValue(1)));
						} else if ("name".equals(nodeName)) {
							// 设置name
							student.setName(xmlPullParser.nextText());
						} else if ("sex".equals(nodeName)) {
							// 设置sex
							student.setSex(xmlPullParser.nextText());
						} else if ("age".equals(nodeName)) {
							// 设置age
							student.setAge(Integer.parseInt(xmlPullParser
									.nextText()));
						} else if ("email".equals(nodeName)) {
							// 设置email
							student.setEmail(xmlPullParser.nextText());
						} else if ("birthday".equals(nodeName)) {
							// 设置birthday
							student.setBirthday(xmlPullParser.nextText());
						} else if ("memo".equals(nodeName)) {
							// 设置memo属性
							student.setMemo(xmlPullParser.nextText());
						}
						break;
					// 结束节点
					case XmlPullParser.END_TAG:
						if ("student".equals(nodeName)) {
							list.add(student);
							student = null;
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Student> PullParserXML(InputStream is) {
		List<Student> students = null;
		Student student = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, "utf-8");
			int type = parser.getEventType();// 获取节点类型
			while (type != XmlPullParser.END_DOCUMENT) {
				String nodeName = parser.getName();// 获取节点名称
				switch (type) {
				case XmlPullParser.START_DOCUMENT:
					students = new ArrayList<Student>();
					break;
				case XmlPullParser.START_TAG:
					if (nodeName.equals("student")) {
						student = new Student();
						student.setId(Integer.parseInt(parser
								.getAttributeValue(null, "id")));
						student.setId(Integer.parseInt(parser
								.getAttributeValue(null, "group")));
					} else if (nodeName.equals("name")) {
						student.setName(parser.nextText());
					} else if (nodeName.equals("age")) {
						student.setAge(Integer.parseInt(parser.nextText()));
					} else if (nodeName.equals("set")) {
						student.setSex(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (nodeName.equals("student")) {
						students.add(student);
						student = null;
					}
					break;
				default:
					break;
				}
				type = parser.next();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}



}
