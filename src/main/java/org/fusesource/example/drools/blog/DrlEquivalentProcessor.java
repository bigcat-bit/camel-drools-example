package org.fusesource.example.drools.blog;

import org.fusesource.example.drools.Person;

public class DrlEquivalentProcessor {

	public Object fireAllRules(Object fact) {
		// Look for rules that apply to a single "Person" fact
		if (fact instanceof Person) {
			Person p = (Person) fact;
			// rule "CanDrink"
			if (p.getAge() >= 21) {
				p.setCanDrink(true);
			}
		}
		// The (possibly modified) input becomes the output
		return fact;
	}
}
