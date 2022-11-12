package utils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;
import database.Data;
import model.*;

public class PriceCalculator {
	public static double calculate(CinemaType cinemaType, SeatType seatType, int age, DateUtils showDate, 
			TimeUtils showTime,MovieType movieType, boolean isMember) {
		TicketPrice tp = Data.ticketPrice;
		LocalDate date = LocalDate.of(showDate.getYear(), showDate.getMonth(), showDate.getDay());
		LocalTime time = LocalTime.of(showTime.getHour(), showTime.getMinute());
		
		double price = 0;
		if(!isWeekend(date)) {
			
			if(isMonWed(date)) {
				if(is3D(movieType)) {
					price = tp.getMonWed3d();
				}
				else {
					price = tp.getMonWed();
				}
			}
			
			if(isThu(date)) {
				if(is3D(movieType)) {
					price = tp.getThu3d();
				}
				else {
					price = tp.getThu();
				}
			}
			
			if(isFri(date)) {
				if(isBeforeSix(time)) {
					if(is3D(movieType)) {
						price = tp.getFri3d();
					}
					else {
						price = tp.getFri();
					}
				}
				else {
					if(is3D(movieType)) {
						price = tp.getFriEve3d();
					}
					else {
						price = tp.getFriEve3d();
					}
				}
			}
			
			if(isElderly(age)) {
				if(isBeforeSix(time)) {
					price = tp.getElderlyWeekDay();
				}
			}
			
			if(isStudent(age)) {
				if(isBeforeSix(time)) {
					if(is3D(movieType)) {
						price = tp.getStudentWeekDay3d();
					}
					else {
						price = tp.getStudentWeekDay();
					}
				}
			}
		}else {
			price = tp.getWeekEnd();
			if(is3D(movieType)) {
				price = tp.getWeekEnd3d();
			}
		}
		
		if(isHoliday(showDate)) {
			price = price +tp.getHolidayAdd();
		}
		
		if(cinemaType == CinemaType.GOLD) {
			price = price +tp.getGoldPriceAdd();
		}
		
		if(cinemaType == CinemaType.PLATINIUM) {
			price = price +tp.getPlatPriceAdd();
		}
		
		if(movieType == MovieType.BLOCKBUSTER) {
			price = price +tp.getBlockBusterAdd();
		}
		
		if(isMember) {
			price = tp.getCard();
		}
		if(seatType == SeatType.COUPLE_1_T || seatType == SeatType.COUPLE_2_T) {
			price = price*2;
		}
		
		return price;
	
	}
	private static boolean is3D(MovieType type) {
		if(type == MovieType.THREED) {
			return true;
		}
		return false;
	}
	
	private static boolean isHoliday(DateUtils date) {
		HashMap<Integer,Holiday> holidayList = Data.holidayList;
		for(Holiday holiday: holidayList.values()) {
			if(DateUtils.equal(holiday.getHolidayDate(), date)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isElderly(int age) {
		if(age>=55) {
			return true;
		}
		return false;
	}
	
	private static boolean isStudent(int age) {
		if(age>=7 && age<=18) {
			return true;
		}
		return false;
	}
	
	private static boolean isMonWed(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.MONDAY|| day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY;
    }
	
	private static boolean isThu(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.THURSDAY;
    }
	
	private static boolean isBeforeSix(final LocalTime time)
    {
		LocalTime t = LocalTime.of(18, 0);
        return time.isBefore(t);
    }
	
	private static boolean isFri(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.FRIDAY;
    }
	
	 private static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }
}