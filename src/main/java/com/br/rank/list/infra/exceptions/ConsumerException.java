package com.br.rank.list.infra.exceptions;

public class ConsumerException extends RuntimeException {

    public ConsumerException(final Throwable throwable){
        super(throwable);
    }
}
