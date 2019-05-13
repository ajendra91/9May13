package com.example.Camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MyApplication extends RouteBuilder {

	public static void main(String[] args) {

		SpringApplication.run(MyApplication.class, args);
	}

	@Override
	public void configure() throws Exception {


		fun();
	}

	private void fun() {

		//from("file:ajay?noop=true").to("file:output");

		//from("file:ajay?noop=true").to("file:output?fileName=kk.csv");

		//from("file:ajay?noop=true").filter(header(Exchange.FILE_NAME).startsWith("ajendra")).to("file:output");

		//from("file:ajay?noop=true").filter(body().contains("ajendra")).to("file:output");

		//from("file:ajay?noop=true").filter(body().startsWith("ajendra")).to("file:output");

		/*from("file:input?noop=true").process(p->{
			String body = p.getIn().getBody(String.class);
			StringBuilder sb=new StringBuilder();
			Arrays.stream(body.split(" ")).forEach(s->{
				sb.append(s+",");
			});
			p.getIn().setBody(sb);
		}).to("file:output");*/


		from("file:input?noop=true").unmarshal().csv().split(body().tokenize(",")).choice()
				.when(body().contains("close")).to("file:output");

	}
}
