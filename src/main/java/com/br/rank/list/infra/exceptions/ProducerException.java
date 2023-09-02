package com.br.rank.list.infra.exceptions;

public class ProducerException extends RuntimeException {
    public ProducerException(final Throwable throwable) {
        super(throwable);
    }
}
