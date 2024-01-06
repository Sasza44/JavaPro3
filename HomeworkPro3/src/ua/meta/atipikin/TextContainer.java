package ua.meta.atipikin;

public class TextContainer {
	private static String text = "Alice and her big sister sat under a tree one sunny day."
			+ "Alice's sister had a book, but Alice had nothing with her."
			+ "She looked at her sisters book. There were no pictures or conversations in it.";
	
	public static String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}