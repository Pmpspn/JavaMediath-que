package utils;

import java.util.Random;

public class AccessGenerator {

	private static String[] access = new String[] { "gmail.com", "outlook.fr", "groupemontroland.fr" };
	private String name;
	Random r = new Random();

	public AccessGenerator() {
		int i = r.nextInt(access.length);
		name = access[i];
	}

	public String getName() {
		return name;
	}
}
