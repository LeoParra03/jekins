/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymmembership;

/**
 *
 * @author leo
 */
public class Feature {
    private String name;
    private double cost;
    private boolean available;

    public Feature(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.available = true;
    }

	public Feature(String name, double cost, boolean available) {
		this.name = name;
		this.cost = cost;
		this.available = available;
	}

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isAvailable() {
        return available;
    }
}