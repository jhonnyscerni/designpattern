package br.com.designpattern.comportamentais.observer.config;

import br.com.designpattern.comportamentais.observer.service.NotificationManager;
import br.com.designpattern.comportamentais.observer.domain.listeners.BuyerNotificationListener;
import br.com.designpattern.comportamentais.observer.domain.listeners.InternalTeamNotificationListener;
import br.com.designpattern.comportamentais.observer.domain.listeners.SellerNotificationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    public NotificationConfig(NotificationManager notificationManager,
                              SellerNotificationListener sellerListener,
                              BuyerNotificationListener buyerListener,
                              InternalTeamNotificationListener internalTeamListener) {
        // Configurar assinaturas
        notificationManager.subscribe("ORDER_CREATED", sellerListener);
        notificationManager.subscribe("PAYMENT_APPROVED", sellerListener);
        notificationManager.subscribe("ORDER_SHIPPED", buyerListener);
        notificationManager.subscribe("PAYMENT_FAILED", internalTeamListener);
    }
}
