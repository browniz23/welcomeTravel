package passionfive.welcometravel.util;

public class Zeller {
	public static int one = 1;	//AllLayout 에서 현재 날짜를 받아오는 것을 한번만 하기 위한 필드
	
	public String calculateWeek(int year, int month, int day){	//요일 계산하는 메소드
		String week = null;
		
		//제라의 공식
		int zellerMonth = 0;
		int zellerYear = 0;
		if(month < 3){
			zellerMonth = month +12;
			zellerYear = year - 1;
		} else {
			zellerMonth = month;
			zellerYear = year;
		}
		int computation = day + (26*(zellerMonth +1))/10 + zellerYear + zellerYear / 4 + 6 * (zellerYear/100)+zellerYear/400;
		int dayOfWeek = computation % 7;
		
		switch (dayOfWeek) {
		case 0:
			week = "토";
			break;
		case 1:
			week = "일";
			break;
		case 2:
			week = "월";
			break;
		case 3:
			week = "화";
			break;
		case 4:
			week = "수";
			break;
		case 5:
			week = "목";
			break;
		case 6:
			week = "금";
			break;
		}
		
		return week;
	}
	
	public int checkDay(int year, int month){	//31일까지인 달인지 30일까지인 달인지 윤년인지 확인 메소드
		int dayCount = 0;
		switch (month) {
		case 1:
			dayCount = 31;
			break;
		case 2:
			if((year%4==0) && (year%100!=0) || (year%400==0)){
				dayCount = 29;
			} else {
				dayCount = 28;
			}
			break;
		case 3:
			dayCount = 31;
			break;
		case 4:
			dayCount = 30;
			break;
		case 5:
			dayCount = 31;
			break;
		case 6:
			dayCount = 30;
			break;
		case 7:
			dayCount = 31;
			break;
		case 8:
			dayCount = 31;
			break;
		case 9:
			dayCount = 30;
			break;
		case 10:
			dayCount = 31;
			break;
		case 11:
			dayCount = 30;
			break;
		case 12:
			dayCount = 31;
			break;
		}
		return dayCount;
	}
}
