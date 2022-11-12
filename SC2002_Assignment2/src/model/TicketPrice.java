package model;

import java.io.Serializable;

public class TicketPrice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double monWed = 8.5;
	private double monWed3d = 11;
	private double thu = 9.5;
	private double thu3d = 11;
	private double fri  =9.5;
	private double friEve = 9.5;
	private double fri3d =9.5;
	private double friEve3d = 15;
	
	private double weekEnd = 11;
	private double weekEnd3d = 15;
	
    private double elderlyWeekDay = 4;
    private double studentWeekDay = 7;
    private double studentWeekDay3d = 9;
    private double blockBusterAdd = 1;
    private double platPriceAdd = 2;
    private double goldPriceAdd = 1;
    private double holidayAdd = 2;

    private double Card = 9.5;
   
    public TicketPrice() {}
    
	public double getMonWed() {
		return monWed;
	}

	public void setMonWed(double monWed) {
		this.monWed = monWed;
	}

	public double getMonWed3d() {
		return monWed3d;
	}

	public void setMonWed3d(double monWed3d) {
		this.monWed3d = monWed3d;
	}

	public double getThu() {
		return thu;
	}

	public void setThu(double thu) {
		this.thu = thu;
	}

	public double getThu3d() {
		return thu3d;
	}

	public void setThu3d(double thu3d) {
		this.thu3d = thu3d;
	}

	public double getFri() {
		return fri;
	}

	public void setFri(double fri) {
		this.fri = fri;
	}

	public double getFriEve() {
		return friEve;
	}

	public void setFriEve(double friEve) {
		this.friEve = friEve;
	}

	public double getFri3d() {
		return fri3d;
	}

	public void setFri3d(double fri3d) {
		this.fri3d = fri3d;
	}

	public double getFriEve3d() {
		return friEve3d;
	}

	public void setFriEve3d(double friEve3d) {
		this.friEve3d = friEve3d;
	}

	public double getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(double weekEnd) {
		this.weekEnd = weekEnd;
	}

	public double getWeekEnd3d() {
		return weekEnd3d;
	}

	public void setWeekEnd3d(double weekEnd3d) {
		this.weekEnd3d = weekEnd3d;
	}

	public double getElderlyWeekDay() {
		return elderlyWeekDay;
	}

	public void setElderlyWeekDay(double elderlyWeekDay) {
		this.elderlyWeekDay = elderlyWeekDay;
	}

	public double getStudentWeekDay() {
		return studentWeekDay;
	}

	public void setStudentWeekDay(double studentWeekDay) {
		this.studentWeekDay = studentWeekDay;
	}

	public double getStudentWeekDay3d() {
		return studentWeekDay3d;
	}

	public void setStudentWeekDay3d(double studentWeekDay3d) {
		this.studentWeekDay3d = studentWeekDay3d;
	}

	public double getBlockBusterAdd() {
		return blockBusterAdd;
	}

	public void setBlockBusterAdd(double blockBusterAdd) {
		this.blockBusterAdd = blockBusterAdd;
	}

	public double getPlatPriceAdd() {
		return platPriceAdd;
	}

	public void setPlatPriceAdd(double platPriceAdd) {
		this.platPriceAdd = platPriceAdd;
	}

	public double getGoldPriceAdd() {
		return goldPriceAdd;
	}

	public void setGoldPriceAdd(double goldPriceAdd) {
		this.goldPriceAdd = goldPriceAdd;
	}

	public double getHolidayAdd() {
		return holidayAdd;
	}

	public void setHolidayAdd(double holidayAdd) {
		this.holidayAdd = holidayAdd;
	}

	public double getCard() {
		return Card;
	}

	public void setCard(double card) {
		Card = card;
	}

	


    
    
}
