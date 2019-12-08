package org.fusesource.example.drools.blog;

import org.fusesource.example.drools.Person;

public class DrlEquivalentProcessorSimple {

	public Person fireAllRules(Person p) {
		// rule "CanDrink"
		if (p.getAge() >= 21) {
			p.setCanDrink(true);
		}
		return p;
	}
}
