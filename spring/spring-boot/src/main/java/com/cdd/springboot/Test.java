package com.cdd.springboot;

import com.cdd.springboot.demo.Test1;
import com.cdd.springboot.demo.Test2;
import com.cdd.springboot.demo.Test3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试springbootapplication注解是否只能在启动类上
 * SpringBootApplication不一定非要注释在启动类上面
 * <p>
 * {@link AliasFor spring4.2开始提供的，可以把其他注解的属性指定到本注解上
 * {@link Indexed}Spring Framework 5.0 版本引入的注解 @Indexed，为 Spring 模式注解添加索引，以提升应用启动性能。
 * 当工程打包为 JAR 或在 IDE 工具中重新构建后，METE-INF/spring.components 文件将自动生成。换言之，该文件在编译时生成。
 * 当 Spring 应用上下文执行 @CompoentScan 扫描时，METE-INF/spring.components 将被 CandidateComponentsIndexLoader 读取并加载，
 * 转化为 CandidateComponentsIndex 对象，进而 @CompoentScan 不再扫描指定的 package，而是读取 CandidateComponentsIndex 对象，从而达到提升性能的目的。
 * {@link Configuration#proxyBeanMethods()}
 * <p>
 * {@link Autowired Inject Resource}1、@Autowired是spring自带的，@Inject是JSR330规范实现的，@Resource是JSR250规范实现的，需要导入不同的包
 * <p>
 * 2、@Autowired、@Inject用法基本一样，不同的是@Autowired有一个request属性
 * <p>
 * 3、@Autowired、@Inject是默认按照类型匹配的，@Resource是按照名称匹配的
 * <p>
 * 4、@Autowired如果需要按照名称匹配需要和@Qualifier一起使用，@Inject和@Name一起使用
 *
 * @author cuoduidui
 * @date 2019-11-11 23:55
 **/
@SpringBootApplication()
public class Test {
    public static void main(String[] args) {
        List<Integer> parray = new ArrayList();
        List<Integer> pcounter = new ArrayList<>();
        prime(parray, pcounter, 2, 100);
        parray.forEach(System.out::println);
//        pcounter.forEach(System.out::println);
    }

    private static void prime(List<Integer> parray, List<Integer> pcounter, int i, int b) {
        if (i > 2) {
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    pcounter.add(i);
                    if (i < b) prime(parray, pcounter, i + 1, b);
                    return;
                }
            }
        }
        parray.add(i);
        if (i < b) prime(parray, pcounter, i + 1, b);
    }

    @Bean
    public String getSpringBootApplication(ApplicationContext applicationContext) {
        Test2 test2 = applicationContext.getBean(Test2.class);
//        Test3 test3 = applicationContext.getBean(Test3.class);
        String[] strings = applicationContext.getBeanNamesForAnnotation(Component.class);
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Test1.class);
//        test2.equals("1");
        Annotation[] annotations = Test3.class.getAnnotations();
        String test1Beans = beans.keySet().stream().collect(Collectors.joining(","));
        System.out.println(test1Beans);
        return "1";
    }
}
