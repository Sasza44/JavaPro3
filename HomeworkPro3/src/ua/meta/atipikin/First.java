package ua.meta.atipikin;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class First {

	public static void main(String[] args) {
		callTest();
	}
	
	@Test
	public static void callTest() {
		Class c1 = First.class; // створюємо об'єкт, який позначає існуючий клас
		
		Method m1 = null; // шукаємо метод, позначений анотацією
		Method[] meth = c1.getMethods();
		for(int i = 0; i < meth.length; i++) {
			if(meth[i].toString().contains("callTest")) {
				m1 = meth[i];
			}
		}
		
		Annotation a1 = m1.getAnnotations()[0]; // об'єкт, який позначає анотацію
		Test annotation = (Test) a1; // отримуємо параметри анотації
		int a = annotation.a();
		int b = annotation.b();
		test(a, b);
	}
	
	public static void test(int a, int b) {
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		int sum = a + b;
		int multiply = a * b;
		System.out.println("a + b = " + sum);
		System.out.println("a * b = " + multiply);
	}
}