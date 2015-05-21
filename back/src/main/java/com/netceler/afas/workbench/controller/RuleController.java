package com.netceler.afas.workbench.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netceler.afas.workbench.common.model.Rule;
import com.netceler.afas.workbench.common.service.IRuleService;

/**
 * Rule REST controller
 * 
 * @author kdelfour
 *
 */
@RestController
public class RuleController {

	@Autowired
	private IRuleService ruleService;

	/**
	 * Get all rules
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Rule> list() {
		final List<Rule> rules = new ArrayList<Rule>();
		rules.addAll(ruleService.list());
		return rules;
	}

	/**
	 * Get a rule by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Rule getRuleByIdent(
			@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
		final Rule rule = ruleService.get(id);
		return rule;
	}

	/**
	 * Create a new rule
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Rule create(@RequestBody Rule rule) {
		return ruleService.saveOrUpdate(rule);
	}

	/**
	 * Update a rule
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Rule rule) {
		final ResponseEntity<String> response;
		if (null != rule && null != rule.getId()
				&& null != ruleService.get(rule.getId())) {
			response = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			ruleService.saveOrUpdate(rule);
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}

	/**
	 * Delete a rule
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> delete(
			@RequestParam(value = "id", required = true) Long id) {
		final ResponseEntity<String> response;
		if (null != ruleService.get(id)) {
			response = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {

			ruleService.delete(id);
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}

}
