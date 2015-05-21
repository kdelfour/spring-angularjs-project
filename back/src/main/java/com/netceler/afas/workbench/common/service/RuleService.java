package com.netceler.afas.workbench.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netceler.afas.workbench.common.dao.IRuleDao;
import com.netceler.afas.workbench.common.model.Rule;

/**
 * Rule Service
 * 
 * @author kdelfour
 *
 */
@Component
public class RuleService implements IRuleService {

	@Autowired
	private IRuleDao ruleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.service.IRuleService#list()
	 */
	@Override
	public List<Rule> list() {
		return ruleDao.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.service.IRuleService#get(long)
	 */
	@Override
	public Rule get(long id) {
		return ruleDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.service.IRuleService#delete(long)
	 */
	@Override
	public void delete(long id) {
		ruleDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.netceler.afas.workbench.common.service.IRuleService#saveOrUpdate(
	 * com.netceler.afas.workbench.common.model.Rule)
	 */
	@Override
	public Rule saveOrUpdate(Rule rule) {
		return ruleDao.saveOrUpdate(rule);
	}

}
