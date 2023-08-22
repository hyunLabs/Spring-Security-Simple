package com.hyunlabs.simple.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageTo3dController {

    @GetMapping("/imageTo3D/grayscale")
    public String grayscale() {
        return "/imageTo3D/grayscale";
    }
}
