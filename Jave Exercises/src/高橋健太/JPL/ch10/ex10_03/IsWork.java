package 高橋健太.JPL.ch10.ex10_03;

public class IsWork {
	static boolean isWork_If_Else(Week week) {
		if     (week == Week.SUNDAY)	return false;
		else if(week == Week.MONDAY)	return true;
		else if(week == Week.TUESDAY)	return true;
		else if(week == Week.WEDNESDAY)	return true;
		else if(week == Week.THURSDAY)	return true;
		else if(week == Week.FRIDAY)	return true;
		else if(week == Week.SATURDAY)	return false;
		else                            return false;//入り得ない
	}

	static boolean isWork_Switch(Week week) {
		switch (week) {
		case SUNDAY:
			return false;
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			return true;
		case SATURDAY:
			return false;
		default:
			return false;
		}
	}
	public static void main(String[] args) {
		String work;

		System.out.println("use If_Else-----------------");

		for(Week week : Week.values()) {
			work = isWork_If_Else(week)? "Working": "not Working";
			System.out.println(week.toString() + " is " + work);
		}

		System.out.println("use Switch-----------------");

		for(Week week : Week.values()) {
			work = isWork_Switch(week)? "Working": "not Working";
			System.out.println(week.toString() + " is " + work);
		}
	}
}
