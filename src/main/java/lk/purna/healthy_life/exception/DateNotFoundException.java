package lk.purna.healthy_life.exception;

import org.aspectj.weaver.ast.Not;

public class DateNotFoundException extends NotFoundException {

    public DateNotFoundException(String message) {
        super(message);
    }
}
