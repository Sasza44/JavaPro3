package ua.meta.atipikin;

import java.io.Serializable;

public class Car implements Serializable {
	private static final long serialVersionUID = 11L;
	@Save
	private String brend; // модель електромобіля
	@Save
	private int reserve; // запас ходу
	private int batteryCapacity; // ємність батареї
	private int speed; // максимальна швидкість
	@Save
	private int price; // орієнтовна ціна
	public Car(String brend, int reserve, int batteryCapacity, int speed, int price) {
		super();
		this.brend = brend;
		this.reserve = reserve;
		this.batteryCapacity = batteryCapacity;
		this.speed = speed;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [brend=" + brend + ", reserve=" + reserve + ", batteryCapacity=" + batteryCapacity + ", speed="
				+ speed + ", price=" + price + "]";
	}
}