package org.fusesource.example.drools.blog;

import java.util.Collection;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.drools.core.runtime.impl.ExecutionResultImpl;
import org.fusesource.example.drools.Cheese;
import org.fusesource.example.drools.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelCommandTest extends CamelSpringTestSupport {

	@Produce(uri = "direct://ruleOnCommandDT")
	protected ProducerTemplate ruleOnCommandEndpoint;

	@Test
	public void testRuleOnCommand() throws Exception {
		Person person = new Person();
		person.setName("Young Scott");
		person.setAge(18);

		// Remark : passing person here is not required
		ExecutionResultImpl response = ruleOnCommandEndpoint.requestBody(
				person, ExecutionResultImpl.class);

		assertNotNull(response);

		// Expecting the given person,
		// and the DroolsCommandHelper's automatically generated Cheese
		Collection<String> identifiers = response.getIdentifiers();
		assertNotNull(identifiers);
		assertTrue(identifiers.size() >= 2);

		boolean personFound = false;
		boolean cheeseFound = false;
		for (String identifier : identifiers) {
			final Object value = response.getValue(identifier);
			assertNotNull(value);
			if (value instanceof Person) {
				assertFalse(((Person) value).isCanDrink());
				personFound = true;
			} else if (value instanceof Cheese) {
				cheeseFound = true;
			}
			System.out.println(identifier + " = " + value);
		}
		assertTrue(personFound);
		assertTrue(cheeseFound);

	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"META-INF/spring/camel-context-decision-table.xml");
	}
}
