package com.tousif.controller;

import java.util.Locale;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tousif.service.impl.ClusterService;

@RestController
@RequestMapping("/cluster")
public class ClusterController {

	@Autowired
	private ClusterService clusterService;
	
	@PostMapping(value = "/cluster-health")
	public ClusterHealthStatus clusterHealth() {
		ClusterHealthStatus healthStatus = clusterService.clusterHealth();
		return healthStatus;
	}
	
	@PostMapping(value = "/cluster-information")
	public ClusterHealthResponse clusterInformation() {
		ClusterHealthResponse healthResponse = clusterService.clusterInformation();
		return healthResponse;
	}
	
}
