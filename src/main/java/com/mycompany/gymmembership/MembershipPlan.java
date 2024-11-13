/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymmembership;

import java.util.List;

/**
 *
 * @author leo
 */
public class MembershipPlan {
    private Membership membership;
    private List<Feature> features;
    private double totalCost;

    public MembershipPlan(Membership membership, List<Feature> features) {
        this.membership = membership;
        this.features = features;
        this.totalCost = membership.getBaseCost();  // Start with the base membership cost
    }

    public void calculateTotalCost() {
        // 3. Calculate the total membership cost, including additional features
        double featuresCost = 0;
        for (Feature feature : features) {
            featuresCost += feature.getCost();
        }
        totalCost += featuresCost;

        System.out.println("Total membership cost (before discounts): $" + totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }
}
