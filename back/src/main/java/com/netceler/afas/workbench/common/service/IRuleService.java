/**
 * 
 */
package com.netceler.afas.workbench.common.service;

import java.util.List;

import com.netceler.afas.workbench.common.model.Rule;

/**
 * Rule service interface
 * @author kdelfour
 *
 */
public interface IRuleService {
	
	/**
	 * Get all rules
	 * @return
	 */
	public List<Rule> list();
	
	/**
	 * Get a rule by Id
	 * @param ident
	 * @return
	 */
	public Rule get(long ident);

	/**
	 * Delete a rule by Id
	 * @param id
	 */
	void delete(long id);

	/**
	 * Create or update a rule
	 * @param rule
	 * @return
	 */
	Rule saveOrUpdate(Rule rule);

}
