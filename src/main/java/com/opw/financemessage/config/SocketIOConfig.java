package com.opw.financemessage.config;


import com.opw.financemessage.factory.SystemParameters;
import com.opw.financemessage.models.Issuer;
import com.opw.financemessage.socket.SocketIO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
public class SocketIOConfig implements BeanFactoryPostProcessor  {
    private SystemParameters systemParameters = new SystemParameters();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        List<Issuer> issuerList = systemParameters.getIssuerList();
        for (int i = 0; i < issuerList.size(); i++) {
//            System.out.println("Register my bean: " + i);
            Issuer issuer =  issuerList.get(i);
            beanFactory.registerSingleton(issuer.getId(), new SocketIO(issuer.getId(), issuer.getIp(), issuer.getPort()));
        }
    }
}

//@Configuration
//public class SocketIOConfig implements ApplicationContextAware {
//    private SystemParameters systemParameters = new SystemParameters();
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        List<Issuer> issuerList = systemParameters.getIssuerList();
//        for (int i = 0; i < issuerList.size(); i++) {
//            System.out.println("Register my bean: " + i);
//            Issuer issuer =  issuerList.get(i);
//            ((ConfigurableApplicationContext) applicationContext).getBeanFactory()
//                    .registerSingleton(issuer.getId(), new SocketIO(issuer.getId(), issuer.getIp(), issuer.getPort()));
//        }
//    }
//}
