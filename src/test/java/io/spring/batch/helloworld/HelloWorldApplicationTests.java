package io.spring.batch.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class HelloWorldApplicationTests {

    @Test
    void applicationContext() {
        ApplicationContext ac = new AnnotationConfigApplicationContext();

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }


    @Test
    void contextLoads() {
    }

}
