package utils;

public class AddressGenerator {

	public static String[] GenerateAddress(int nb) {
		NameGenerator n = new NameGenerator(8);
		String[] list = new String[nb];
		for (int i = 0; i < list.length; i++) {
			String access = new AccessGenerator().getName();
			String address = n.getName() + "@" + access;
			list[i] = address;
		}
		return list;
	}

}
