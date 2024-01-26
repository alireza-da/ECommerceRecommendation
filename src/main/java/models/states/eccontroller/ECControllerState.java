package main.java.models.states.eccontroller;

import main.java.models.ECommerceController;

public interface ECControllerState {
    void onError(ECommerceController eCommerceController);
    void onRecovery(ECommerceController eCommerceController);
}
