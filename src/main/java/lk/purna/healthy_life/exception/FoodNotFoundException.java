package lk.purna.healthy_life.exception;

import org.aspectj.weaver.ast.Not;

public class FoodNotFoundException extends NotFoundException {


    public FoodNotFoundException(String message) {
        super(message);
    }
}
