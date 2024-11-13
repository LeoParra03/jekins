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

    public Membership(String name, double baseCost) {
        this.name = name;
        this.baseCost = baseCost;
    }

    public String getName() {
        return name;
    }

    public double getBaseCost() {
        return baseCost;
    }
}
