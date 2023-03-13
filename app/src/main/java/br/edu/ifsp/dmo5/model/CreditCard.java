package br.edu.ifsp.dmo5.model;

import java.util.Objects;

import br.edu.ifsp.dmo5.model.exception.DebitOperationException;

public class CreditCard {

    private Integer id;
    private Double balance;
    private static int LAST_CARD_ID = 0;

    public CreditCard(){
        balance = 15000.00;
        id = LAST_CARD_ID + 1;
        LAST_CARD_ID = id;
    }

    public void creditValue(Double value) {
        this.balance += value;
    }

    public void debitValue(Double value) throws DebitOperationException {

        if(this.balance < value){
            throw new DebitOperationException();
        }
        this.balance -= value;
    }

    public Double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id.equals(that.id) && balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }
}
