/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hoogstad.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class HelloWorldController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping(value="/hello/{name}", method=RequestMethod.GET)
    @ResponseBody
    String name(@PathVariable String name) {
        return "Hello " + name;
    }

}