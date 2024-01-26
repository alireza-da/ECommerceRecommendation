package main.java.models.states.eccontroller;

import main.java.models.ECommerceController;

public class Normal implements ECControllerState{

    @Override
    public void onError(ECommerceController eCommerceController) {
            eCommerceController.setState(new Safe());
    }

    @Override
    public void onRecovery(ECommerceController eCommerceController) {

    }
}
