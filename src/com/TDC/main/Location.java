package com.TDC.main;

public enum Location {
	DOMESTIC(750), INTERNATIONAL(2000);
	
	private int cost;
	
	private Location(int cost) {
		this.cost = cost;
	}
	
	public int getMonthyCost() {
		return cost;
	}
}
