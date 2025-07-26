package org.huy.validator;

import org.huy.dto.AbstractProductRequest;
import org.huy.dto.ProductModifyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductRequestValidator {

    public void validateProductRequest(AbstractProductRequest request) {
        this.validate(request);

        if(request instanceof ProductModifyRequest) {
            this.validateProductModifyRequest((ProductModifyRequest) request);
        }
    }

    private void validateProductModifyRequest(ProductModifyRequest request) {
        if(!request.isValidProductId()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid product id");
    }

    private void validate(AbstractProductRequest request) {
        if(request == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");

        if(!request.isValidCategory()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unrecognized category");

        if(!request.isValidStock()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid stock value");

        if(!request.isValidName()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid product name");

        if(!request.isValidPrice()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid product price");
    }
}
