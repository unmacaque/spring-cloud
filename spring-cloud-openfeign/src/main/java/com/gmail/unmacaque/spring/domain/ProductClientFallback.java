package com.gmail.unmacaque.spring.domain;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
class ProductClientFallback implements ProductClient {

	@Override
	public List<Product> getProducts() {
		return Collections.emptyList();
	}

}
