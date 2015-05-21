package com.netceler.afas.workbench.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.netceler.afas.workbench.common.model.Rule;

/**
 * Rule DAO
 * @author kdelfour
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class RuleDaoImpl implements IRuleDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Hibernate constructor
	 */
	public RuleDaoImpl() {

	}

	/**
	 * Rule Dao constructor with session factory
	 * 
	 * @param sessionFactory
	 */
	public RuleDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.service.IRuleService#getAll()
	 */

	@Override
	@Transactional
	public List<Rule> list() {
		List<Rule> listRule = (List<Rule>) sessionFactory.getCurrentSession()
				.createCriteria(Rule.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listRule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.netceler.afas.workbench.common.dao.IRuleDao#saveOrUpdate(com.netceler
	 * .afas.workbench.common.model.Rule)
	 */
	/* (non-Javadoc)
	 * @see com.netceler.afas.workbench.common.dao.IRuleDao#saveOrUpdate(com.netceler.afas.workbench.common.model.Rule)
	 */
	@Override
	@Transactional
	public Rule saveOrUpdate(Rule rule) {
		sessionFactory.getCurrentSession().saveOrUpdate(rule);
		return rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.dao.IRuleDao#delete(long)
	 */
	@Override
	@Transactional
	public void delete(long id) {
		final Rule ruleToDelete = new Rule();
		ruleToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(ruleToDelete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netceler.afas.workbench.common.dao.IRuleDao#get(long)
	 */
	@Override
	@Transactional
	public Rule get(long id) {
		String hql = "from Rule where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Rule) query.uniqueResult();
	}

}
