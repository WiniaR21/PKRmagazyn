package com.WirtualnyMagazynPKR.application.service;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.springframework.stereotype.Component;

@Component
public class ProductNotification {
    private final int notificationTimeDuration = 8000;
    private final NotificationVariant errorTheme = NotificationVariant.LUMO_ERROR;
    private final NotificationVariant successTheme = NotificationVariant.LUMO_SUCCESS;
    private final NotificationVariant updateTheme = NotificationVariant.LUMO_CONTRAST;
    private final Notification.Position bottomLeft = Notification.Position.BOTTOM_START;
    private final Notification.Position topCenter = Notification.Position.TOP_CENTER;

    public void showSuccessSaveNotification(Product product){
        Notification notification = Notification
                .show("Dodano nowy produkt: " + product.getProductName().getName() + ", " + product.getGrossWeight() + "kg brutto.");
        notification.addThemeVariants(successTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(bottomLeft);
    }
    public void showInvalidSaveNotification(){
        Notification notification = Notification
                .show("Nie można dodać produktu!");
        notification.addThemeVariants(errorTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(topCenter);
    }
    public void showInvalidDeleteNotification(){
        Notification notification = Notification
                .show("Nie można usunąć produktu!");
        notification.addThemeVariants(errorTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(topCenter);
    }
    public void showSuccessDeleteNotification(Product product){
        Notification notification = Notification
                .show("Usunięto produkt: " + product.getProductName().getName() + ", " + product.getGrossWeight() + "kg brutto.");
        notification.addThemeVariants(successTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(bottomLeft);
    }
    public void showSuccessUpdateNotification(){
        Notification notification = Notification
                .show("Zaktualizowano dane o produkcie!");
        notification.addThemeVariants(updateTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(bottomLeft);
    }

    public void showSuccessNewProductTypeAdded(ProductName name) {
        Notification notification = Notification
                .show("Dodano nowy typ produktu: " + name.getName() + "!");
        notification.addThemeVariants(successTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(bottomLeft);
    }

    public void showThisTypeExist(String name) {
        Notification notification = Notification
                .show("Produkt " + name + " już isnieje w bazie danych.");
        notification.addThemeVariants(errorTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(topCenter);
    }

    public void showNameIsInvalid() {
        Notification notification = Notification
                .show("Podaj poprawną nazwę produktu");
        notification.addThemeVariants(errorTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(topCenter);
    }

    public void showInvalidWeight() {
        Notification notification = Notification
                .show("Waga NETTO jest większa niż BRUTTO. Podaj właściwe dane.");
        notification.addThemeVariants(updateTheme);
        notification.setDuration(notificationTimeDuration);
        notification.setPosition(topCenter);
    }
}
