package br.edu.ifsp.dmo5.model;


import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo5.model.exception.DebitOperationException;

public class StarBank {

    private static StarBank starBank = null;
    private List<CreditCard> creditCardArray = null;

    private StarBank() {

    }

    public static StarBank getInstance() {
        if (starBank == null) {
            starBank = new StarBank();
        }
        return starBank;
    }


    public void starCreditCards() {
        creditCardArray = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            creditCardArray.add(new CreditCard());
        }
    }

    public void roundCompleted(CreditCard payer, CreditCard receiver, Double value) throws DebitOperationException {

        try {
            receiver.creditValue(value);
            payer.debitValue(value);
            updateList(payer);
            updateList(receiver);
        } catch (DebitOperationException ex) {
            throw ex;
        }
    }

    public List<CreditCard> getCreditCardArray() {
        return creditCardArray;
    }

    public boolean transfer(CreditCard payer, CreditCard receiver, Double value) throws DebitOperationException {
        try {
            receiver.creditValue(value);
            payer.debitValue(value);
            updateList(payer);
            updateList(receiver);
        } catch (DebitOperationException ex) {
            return false;
        }
        return true;
    }

    public void receive(CreditCard receiver, Double value) {
        receiver.creditValue(value);
        updateList(receiver);
    }

    public boolean pay(CreditCard payer, Double value) throws DebitOperationException {
        try {
            payer.debitValue(value);
            updateList(payer);
        } catch (DebitOperationException ex) {
            throw ex;
        }
        return true;
    }

    private void updateList(CreditCard action) {
        for (int i = 0; i < creditCardArray.size() - 1; i++) {
            if (creditCardArray.get(i).getId() == action.getId()) {
                creditCardArray.set(i, action);
            }
        }
    }


}
