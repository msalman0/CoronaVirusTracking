package com.voda.coronaVirus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.voda.coronaVirus.model.CoronaState;
import com.voda.coronaVirus.service.CornoaVirusData;


@Controller
public class HomeController
{
	@Autowired
	private CornoaVirusData coronaVirusdata;

	@GetMapping(path = "/")
	public String hello(final Model model)
	{
		final List<CoronaState> allstats = coronaVirusdata.getCoronateStateList();
		final int totalCases = allstats.stream().mapToInt(state -> state.getCount()).sum();
		model.addAttribute("CoronaDataList", allstats);
		model.addAttribute("totalCases", totalCases);
		return "index";
	}
}
