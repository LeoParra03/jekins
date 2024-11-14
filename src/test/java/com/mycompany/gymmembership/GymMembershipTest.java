package com.mycompany.gymmembership;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GymMembershipTest {

	private Membership basicMembership = new Membership("Basic", 50);
	private Membership premiumMembership = new Membership("Premium", 100);
	private Membership familyMembership = new Membership("Family", 150);

	private Feature personalTraining = new Feature("Personal Training", 30);
	private Feature groupClasses = new Feature("Group Classes", 20);
	private Feature exclusiveFacilities = new Feature("Access to exclusive gym facilities", 50);
	private Feature specializedTraining = new Feature("Specialized Training Programs", 100);

	@Test
	public void testMembershipAvailability() {
		Membership extraMembership = new Membership("Extra", 200, false);

		assertTrue(basicMembership.isAvailable(), "Basic membership should be available");
		assertTrue(premiumMembership.isAvailable(), "Premium membership should be available");
		assertTrue(familyMembership.isAvailable(), "Family membership should be available");
		assertTrue(!extraMembership.isAvailable(), "Extra membership should not be available");
	}

	@Test
	public void testFeatureAvailability() {
		Feature extraFeature = new Feature("Extra Feature", 150, false);

		assertTrue(personalTraining.isAvailable(), "Personal Training should be available");
		assertTrue(groupClasses.isAvailable(), "Group Classes should be available");
		assertTrue(exclusiveFacilities.isAvailable(), "Access to exclusive gym facilities should be available");
		assertTrue(specializedTraining.isAvailable(), "Specialized Training Programs should be available");
		assertTrue(!extraFeature.isAvailable(), "Extra Feature should not be available");
	}

	@Test
	public void testCalculateTotalCostWithNoFeatures() {
		MembershipPlan plan = new MembershipPlan(basicMembership, List.of());
		plan.calculateTotalCost();
		assertEquals(50, plan.getTotalCost(), "Cost should be the base membership cost with no additional features.");
	}

	@Test
	public void testCalculateTotalCostWithStandardFeatures() {
		MembershipPlan plan = new MembershipPlan(basicMembership, List.of(personalTraining, groupClasses));
		plan.calculateTotalCost();
		double expectedCost = 50 + 30 + 20; // Base cost + personal training + group classes
		assertEquals(expectedCost, plan.getTotalCost(), "Cost should include base membership and standard features.");
	}

	@Test
	public void testPremiumFeatureSurcharge() {
		MembershipPlan plan = new MembershipPlan(premiumMembership, List.of(exclusiveFacilities));
		plan.calculateTotalCost();
		double baseCostWithFeature = 100 + 50;
		double expectedCost = baseCostWithFeature + baseCostWithFeature * 0.15; // Base + premium feature + 15%
																				// surcharge
		assertEquals(expectedCost, plan.getTotalCost(), 0.01,
				"Cost should include a 15% surcharge for premium features.");
	}

	@Test
	public void testApplyDiscountAndPremiumFeatureSurcharge() {
		MembershipPlan plan = new MembershipPlan(familyMembership,
				List.of(personalTraining, exclusiveFacilities, specializedTraining));
		plan.calculateTotalCost();
		double baseCostWithFeatures = 150 + 30 + 50 + 100 - 20; // Base + features - discount of $20
		double expectedCost = baseCostWithFeatures + baseCostWithFeatures * 0.15; // Add 15% surcharge for premium
																					// features
		assertEquals(expectedCost, plan.getTotalCost(), 0.01,
				"Cost should include both discount and premium feature surcharge.");
	}
}
