package dev.stocky37.epic7.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TestLambda implements RequestHandler<Object, String> {

	@Inject
	TestService service;

	@Override
	public String handleRequest(Object aVoid, Context context) {
		System.out.println(service);
		return service.test();
	}
}
