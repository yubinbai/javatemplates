package javaLang.enumSample;

public class EnumSample {
	Day day;

	public EnumSample(Day day) {
		this.day = day;
	}

	public void tellItLikeItIs() {
		switch (day) {
		case MONDAY:
			System.out.println("Mondays are bad.");
			break;

		case FRIDAY:
			System.out.println("Fridays are better.");
			break;
		// if no break, they continue to the next option
		case SATURDAY:
		case SUNDAY:
			System.out.println("Weekends are best.");
			break;

		default:
			System.out.println("Midweek days are so-so.");
			break;
		}
	}
}
