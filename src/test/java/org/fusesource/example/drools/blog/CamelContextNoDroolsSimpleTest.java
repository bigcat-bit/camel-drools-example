package org.fusesource.example.drools.blog;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelContextNoDroolsSimpleTest extends AbstractCamelContextTest {

    @Override
    protected ClassPathXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/blog-camel-context-no-drools-simple.xml");
    }
}
 