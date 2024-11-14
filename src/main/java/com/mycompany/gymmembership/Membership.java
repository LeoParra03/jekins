/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymmembership;

/**
 *
 * @author leo
 */
public class Membership {
    private String name;
    private double baseCost;
    private boolean available;

    public Membership(String name, double baseCost) {
        this.name = name;
        this.baseCost = baseCost;
        this.available = true;
    }

	public Membership(String name, double baseCost, boolean available) {
		this.name = name;
		this.baseCost = baseCost;
		this.available = available;
	}

    public String getName() {
        return name;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public boolean isAvailable() {
        return available;
    }
}
