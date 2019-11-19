package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.IGraphBusiness;

@RestController
@RequestMapping(Constantes.URL_GRAPH)
public class GraphRestService {

	@Autowired
	private IGraphBusiness graphService;

	@GetMapping(value = { "/push" })
	public void push() {

		graphService.pushGraphData();

	}

}
