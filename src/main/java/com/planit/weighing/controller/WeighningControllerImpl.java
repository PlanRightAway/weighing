package com.planit.weighing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.planit.journey_common.endpoint.WeighingController;
import com.planit.journey_common.model.Journey;
import com.planit.journey_common.model.PlanCriteria;

@RestController
public class WeighningControllerImpl implements WeighingController {

	private Map<Long, PlanCriteria> idCriteriaMap = new HashMap<Long, PlanCriteria>();

	public void registerPlan(Long planId, PlanCriteria planCriteria) {
		System.out.println("Registering plan for plani Id: "+ planId);
		idCriteriaMap.put(planId, planCriteria);
	}

	public Double getWeight(Journey journey, Long planId) {
		PlanCriteria planCriteria = idCriteriaMap.get(planId);
		System.out.println("Plan criteria: "+planCriteria);
		return planCriteria.getTimeWeightage()
				* (journey.getDurationInMillies() + journey.getWaitingTimeInMillies()
						+ journey.getEndBufferTimeInMillies() + journey.getStartBufferTimeInMillies())
				+ planCriteria.getCostWeightage() * journey.getTravellingCost();
	}

}
