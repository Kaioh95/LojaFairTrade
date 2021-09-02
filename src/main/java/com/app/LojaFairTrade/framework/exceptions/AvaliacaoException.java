package com.app.LojaFairTrade.framework.exceptions;

public class AvaliacaoException extends Exception {

    private String message;

    public AvaliacaoException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return "Erro na Avaliação: "+this.message;
    }
}
