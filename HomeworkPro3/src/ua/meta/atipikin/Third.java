package ua.meta.atipikin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Third {
	public static void main(String[] args) {
		Car teslaModel3 = new Car("Tesla Model 3", 350, 50, 225, 39000);
//		Car volkswagenEGolf = new Car("Volkswagen e-Golf", 300, 35, 150, 36000);
//		Car fordFocusElectric = new Car("Ford Focus Electric", 185, 33, 135, 29000);
		
		try {
			serialize(teslaModel3, "2.txt"); // серіалізація об'єкту класу
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			teslaModel3 = deserialize("2.txt"); // десеріалізація об'єкту класу
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(teslaModel3);
	}
	
	public static void serialize(Car car, String fileName) throws IllegalArgumentException, IllegalAccessException { // серіалізація об'єкту класу
		Class c1 = (Class) car.getClass(); // отримуємо інформацію про клас
		Field[] f1 = c1.getDeclaredFields(); // отримуємо список полів класу
		for (Field f: f1) {
			Field f2 = null; // резервна змінна для позначення поля з дозволом серіалізації
			if(f.isAnnotationPresent(Save.class)) { // виявляємо поля, які позначені анотацією
				Annotation a1 = f.getAnnotation(Save.class); // добираємось до параметрів анотації
				Save annotation = (Save) a1;
				boolean permission = annotation.permission();
				if(permission) { // перевіряємо параметр анотації
					f2 = f; // усі поля, які мають анотацію Save з дозволом серіалізації
				}
			}
			else if(f != f2 && !f.toString().contains("static")) { // поля, які не відповідають вищевказаній умові
//				System.out.println(f);
				f.setAccessible(true);
				if(f.getType().toString().equals("int")) { // обнуляємо поля, які не помічені анотацією з дозволом серіалізації
					f.setInt(car, 0);
				}
				else {
					f.set(car, null);
				}
				f.setAccessible(false);
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(car);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static Car deserialize(String fileName) throws ClassNotFoundException { // десеріалізація об'єкту класу
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Car car = (Car) ois.readObject();
			return car;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}