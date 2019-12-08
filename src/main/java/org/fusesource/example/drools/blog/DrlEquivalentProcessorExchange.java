package org.fusesource.example.drools.blog;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.fusesource.example.drools.Person;

public class DrlEquivalentProcessorExchange implements Processor {

	@Override
	public void process(Exchange exchange) {
		// Process input
		Object inBody = exchange.getIn().getBody();
		Object[] droolsResults = fireAllRules(inBody);

		// Set output
		Object outBody;
		if (droolsResults.length == 1) {
			outBody = droolsResults[0];
		} else {
			outBody = droolsResults;
		}
		exchange.getOut().setBody(outBody);
	}

	public Object[] fireAllRules(Object... facts) {
		// Each fact has a chance to trigger a rule
		for (Object input : facts) {
			// Look for rules that apply to a single "Person" fact
			if (input instanceof Person) {
				Person p = (Person) input;
				// rule "CanDrink"
				if (p.getAge() >= 21) {
					p.setCanDrink(true);
				}
			}
		}
		// The (possibly modified) input becomes the output
		return facts;
	}

}
