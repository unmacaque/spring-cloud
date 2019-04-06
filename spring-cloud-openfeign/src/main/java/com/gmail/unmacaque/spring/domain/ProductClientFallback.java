package com.gmail.unmacaque.spring.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
class ProductClientFallback implements ProductClient {

	@Override
	public List<Product> getProducts() {
		return Collections.emptyList();
	}

}
