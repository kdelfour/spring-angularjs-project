/**
 * 
 */
package com.netceler.afas.workbench.common.dao;

import java.util.List;

import com.netceler.afas.workbench.common.model.Rule;

/**
 * Rule DAO interface - Simple CRUD
 * 
 * @author kdelfour
 *
 */
public interface IRuleDao {

	/**
	 * Get all rules
	 * 
	 * @return
	 */
	public List<Rule> list();

	/**
	 * Get a rule by Id
	 * 
	 * @param ident
	 * @return
	 */
	public Rule get(long ident);

	/**
	 * Delete a rule by Id
	 * 
	 * @param id
	 */
	public void delete(long id);

	/**
	 * Create or update a rule
	 * 
	 * @param rule
	 * @return
	 */
	public Rule saveOrUpdate(Rule rule);

}
