package br.edu.ifsp.dmo5.model.exception;

public class DebitOperationException extends RuntimeException{

    public DebitOperationException(){
        super("Player debitante sem saldo suficiente para realizar esta operação");
    }

}
