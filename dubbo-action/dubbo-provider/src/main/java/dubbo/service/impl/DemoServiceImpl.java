package dubbo.service.impl;

import dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello "+name;
    }
}
