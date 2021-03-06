package com.netceler.afas.workbench.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.netceler.afas.workbench.common.config.AppTestConfig;
import com.netceler.afas.workbench.common.model.Rule;

@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { AppTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleDaoImplTest {

	@Autowired
	private IRuleDao ruleDao;

	@Test
	public void test_list_all_rules_have_been_initiated() {
		final List<Rule> allRules = ruleDao.list();
		assertNotNull(allRules);
		assertNotEquals(0, allRules.size());
	}

	@Test
	public void test_get_an_existant_rule() {
		Rule rule = ruleDao.get(1L);
		assertNotNull(rule);
		assertEquals("R2", rule.getTitle());
		assertEquals("R2 DESC", rule.getDescription());
		assertNull(rule.getCode());
	}

	@Test
	public void test_get_an_unexistant_rule() {
		Rule rule = ruleDao.get(10L);
		assertNull(rule);
	}

	@Test
	public void test_create_a_new_rule() {
		List<Rule> allRules = ruleDao.list();
		int rulesCounter = allRules.size();

		Rule rule = new Rule();
		rule.setTitle("Rx");
		rule.setDescription("Rx DESC");
		rule.setCode("JAVA");

		Rule newRule = ruleDao.saveOrUpdate(rule);
		assertNotNull(newRule);
		assertNotNull(newRule.getId());

		allRules = ruleDao.list();
		assertNotNull(allRules);
		assertEquals(rulesCounter + 1, allRules.size());
	}

	@Test(expected = ConstraintViolationException.class)
	public void test_create_a_incomplete_rule() {
		Rule rule = new Rule();
		rule.setTitle(null);
		rule.setDescription("Rx DESC");
		rule.setCode("JAVA");
		ruleDao.saveOrUpdate(rule);
	}

	@Test
	public void test_update_an_existing_rule() {
		Rule oldRule = ruleDao.get(2L);
		oldRule.setTitle("Rx modified");

		Rule newRule = ruleDao.saveOrUpdate(oldRule);
		assertNotNull(newRule);
		assertEquals("Rx modified", newRule.getTitle());

		Rule ruleInDb = ruleDao.get(2L);
		assertNotNull(ruleInDb);
		assertEquals("Rx modified", ruleInDb.getTitle());
	}

	@Test
	public void test_delete_an_existing_rule() {
		List<Rule> allRules = ruleDao.list();
		int rulesCounter = allRules.size();

		ruleDao.delete(0L);

		allRules = ruleDao.list();
		assertNotNull(allRules);
		assertEquals(rulesCounter - 1, allRules.size());
	}

	@Test(expected = HibernateOptimisticLockingFailureException.class)
	public void test_delete_an_unexisting_rule() {
		ruleDao.delete(10L);
	}

}
