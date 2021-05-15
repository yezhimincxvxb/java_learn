package com.yzm.network.http;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/http")
public class HttpRequestController {

    @Autowired
    private HttpRequestService httpRequestService;

    @GetMapping(path = "/test01")
    public void test01() {
        httpRequestService.test01();
    }

}
