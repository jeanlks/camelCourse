package com.udemy.learn.dbmail.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{startRoute}}")
                .log("Timer Invoked and the body ${body}")
                .pollEnrich("{{fromRoute}}")
                .to("{{toRoute}}");
    }
}
