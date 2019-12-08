package org.fusesource.example.drools.blog;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.fusesource.example.drools.Person;
import org.junit.Test;

public abstract class AbstractCamelContextTest extends CamelSpringTestSupport {

    // templates to send to input endpoint
    @Produce(uri = "direct://ruleOnBody")
    protected ProducerTemplate ruleOnBodyEndpoint;

    @Test
    public void testRuleOnBody() throws Exception {
        Person person = new Person();
        person.setName("Young Scott");
        person.setAge(18);

        Person response = ruleOnBodyEndpoint.requestBody(person, Person.class);

        assertNotNull(response);
        assertFalse(person.isCanDrink());

        // Test for alternative result

        person.setName("Scott");
        person.setAge(21);

        response = ruleOnBodyEndpoint.requestBody(person, Person.class);

        assertNotNull(response);
        assertTrue(person.isCanDrink());
    }
}
