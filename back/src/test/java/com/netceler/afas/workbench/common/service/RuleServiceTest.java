package com.netceler.afas.workbench.common.service;

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
public class RuleServiceTest {

	@Autowired
	private IRuleService ruleService;

	@Test
	public void test_list_all_rules_have_been_initiated() {
		final List<Rule> allRules = ruleService.list();
		assertNotNull(allRules);
		assertNotEquals(0, allRules.size());
	}

	@Test
	public void test_get_an_existant_rule() {
		Rule rule = ruleService.get(1L);
		assertNotNull(rule);
		assertEquals("R2", rule.getTitle());
		assertEquals("R2 DESC", rule.getDescription());
		assertNull(rule.getCode());
	}

	@Test
	public void test_get_an_unexistant_rule() {
		Rule rule = ruleService.get(10L);
		assertNull(rule);
	}

	@Test
	public void test_create_a_new_rule() {
		List<Rule> allRules = ruleService.list();
		int rulesCounter = allRules.size();

		Rule rule = new Rule();
		rule.setTitle("Rx");
		rule.setDescription("Rx DESC");
		rule.setCode("JAVA");

		Rule newRule = ruleService.saveOrUpdate(rule);
		assertNotNull(newRule);
		assertNotNull(newRule.getId());

		allRules = ruleService.list();
		assertNotNull(allRules);
		assertEquals(rulesCounter + 1, allRules.size());
	}

	@Test(expected = ConstraintViolationException.class)
	public void test_create_a_incomplete_rule() {
		Rule rule = new Rule();
		rule.setTitle(null);
		rule.setDescription("Rx DESC");
		rule.setCode("JAVA");
		ruleService.saveOrUpdate(rule);
	}

	@Test
	public void test_update_an_existing_rule() {
		Rule oldRule = ruleService.get(2L);
		oldRule.setTitle("Rx modified");

		Rule newRule = ruleService.saveOrUpdate(oldRule);
		assertNotNull(newRule);
		assertEquals("Rx modified", newRule.getTitle());

		Rule ruleInDb = ruleService.get(2L);
		assertNotNull(ruleInDb);
		assertEquals("Rx modified", ruleInDb.getTitle());
	}

	@Test
	public void test_delete_an_existing_rule() {
		List<Rule> allRules = ruleService.list();
		int rulesCounter = allRules.size();

		ruleService.delete(4L);

		allRules = ruleService.list();
		assertNotNull(allRules);
		assertEquals(rulesCounter - 1, allRules.size());
	}

	@Test(expected = HibernateOptimisticLockingFailureException.class)
	public void test_delete_an_unexisting_rule() {
		ruleService.delete(10L);
	}

}
