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
    private boolean hasPremiumFeatures;

    public MembershipPlan(Membership membership, List<Feature> features) {
        this.membership = membership;
        this.features = features;
        this.hasPremiumFeatures = false;
        this.totalCost = membership.getBaseCost();  // Start with the base membership cost
    }

    public void calculateTotalCost() {
        // 3. Calculate the total membership cost, including additional features
        double featuresCost = 0;
        for (Feature feature : features) {
            if (feature.getName().equals("Access to exclusive gym facilities") || feature.getName().equals("Specialized Training Programs"))
                this.hasPremiumFeatures = true;
            featuresCost += feature.getCost();
        }
        totalCost += featuresCost;

        System.out.println("Total membership cost (before discounts): $" + totalCost);
        if (totalCost > 400){
            totalCost -= 50;
        } else if (totalCost > 200) {
            totalCost -= 20;
        }
        if (this.hasPremiumFeatures)
            totalCost = totalCost + totalCost*0.15;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
