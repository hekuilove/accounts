package org.quinn.accounts;

import org.quinn.accounts.dao.mapper.IncomeMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}
	

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		IncomeMapper incomeMapper = ac.getBean(IncomeMapper.class);
		assertNotNull(incomeMapper.findAll());
	}
}
