package com.camel.udemy.course.first.firstexercise;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstexerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstexerciseApplication.class, args);

		CamelContext ctx = new DefaultCamelContext();

			try {
				ctx.addRoutes(new RouteBuilder() {
                   @Override
                   public void configure() throws Exception {
                       from("file:/Users/Jean/Documents/input?noop=true")
							   .to("log:?level=INFO&showBody=true")
                               .to("file:/Users/Jean/Documents/output");
                   }
               });

				ctx.start();
				Thread.sleep(300000);
				ctx.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
}
