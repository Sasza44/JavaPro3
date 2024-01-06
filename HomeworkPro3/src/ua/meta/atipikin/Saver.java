package ua.meta.atipikin;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
	public static void main(String[] args) {
		String text = TextContainer.getText();
		save(text);
	}
	
	@SaveTo(path = "1.txt")
	public static void save(String string) {
		Class c1 = Saver.class; // створюємо об'єкт, який позначає існуючий клас
		Method m1 = null; // шукаємо метод, позначений анотацією
		Method[] meth = c1.getMethods();
		for(int i = 0; i < meth.length; i++) {
			if(meth[i].toString().contains("save")) {
				m1 = meth[i];
			}
		}
		Annotation a1 = m1.getAnnotations()[0]; // об'єкт, який позначає анотацію
		SaveTo annotation = (SaveTo) a1; // отримуємо параметри анотації
		String path = annotation.path(); // шлях запису, вказаний в анотації
		try { // запис тексту в файл
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(string);
			bw.close();
		} catch (IOException e) {
			System.out.println("Write error.");
		}
	}
}