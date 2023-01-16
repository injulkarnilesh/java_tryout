package in.nilesh.designpattern.structural;

import java.util.HashMap;
import java.util.Map;
/*
Flyweight is a structural design pattern that 
lets you fit more objects into the available amount of RAM by 
sharing common parts of state between multiple objects instead of keeping all of the data in each object
*/
enum FONT {
	Times, Serif, Georgia, Arial, Helvetica
};

enum FONT_STYLE {
	NORMAL, ITALIC, BOLD, STRIKED, UNDERLINED
};

interface Flyweight {
	public void displayCharacter(String color, FONT font, int size,
			FONT_STYLE fontStyle);
}

class FlyWeightImpl implements Flyweight {

	Character character;

	public FlyWeightImpl(Character character) {
		this.character = character;
	}

	@Override
	public void displayCharacter(String color, FONT font, int size,
			FONT_STYLE fontStyle) {
		System.out.println(fontStyle + " " + this.character + " in " + color
				+ ", " + font + " font, of size " + size);
	}
}

class FlyweightFactory {
	private static Map<Character, Flyweight> flyweights = new HashMap<Character, Flyweight>();

	public static Flyweight getCharacterFlyweight(Character character) {
		Flyweight flyweight = flyweights.get(character);

		if (flyweight == null) {
			flyweights.put(character, new FlyWeightImpl(character));
			flyweight = flyweights.get(character);
		}
		return flyweight;

	}

	public static int getMapSize() {
		return flyweights.keySet().size();
	}
}

public class FlyweightDesignPattern {

	public static void main(String[] args) {
		String str = "Nilesh Shripati Injulkar @ 27//02//1990";

		for (int i = 0; i < str.length(); i++) {
			Character character = str.charAt(i);
			Flyweight flyweight = FlyweightFactory
					.getCharacterFlyweight(character);
			flyweight.displayCharacter(getRandomColor(), getRandomFont(),
					getRandomSize(), getRandomFontStyle());

		}

		System.out.println("\n\n# of characters in string " + str.length());
		System.out.println("# of Flyweight objects used "
				+ FlyweightFactory.getMapSize());

	}

	private static String getRandomColor() {
		String[] colors = { "Red", "Green", "Yellow", "Blue", "Cyan" };
		return colors[(int) (Math.random() * colors.length)];
	}

	private static FONT getRandomFont() {
		FONT fonts[] = FONT.values();
		return fonts[(int) (Math.random() * fonts.length)];
	}

	private static int getRandomSize() {
		return (int) (Math.random() * 10 + 1);
	}

	private static FONT_STYLE getRandomFontStyle() {
		FONT_STYLE fontStyles[] = FONT_STYLE.values();
		return fontStyles[(int) (Math.random() * fontStyles.length)];
	}

}
