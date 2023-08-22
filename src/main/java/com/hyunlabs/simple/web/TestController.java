package com.hyunlabs.simple.web;

import com.hyunlabs.simple.common.ResultMap;
import com.hyunlabs.simple.exception.NotFoundException;
import com.hyunlabs.simple.web.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final String ROOT_PATH = "D:/05.python/captioning/imageSet";
    @GetMapping("/test")
    public String test(@RequestParam String str1) {

        if(str1 == null || str1.equals("")) {
            throw new NotFoundException("NotFound");
        }

        return str1;
    }

    @GetMapping("/v1/getVideoList")
    public ResponseEntity getVideoList() {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
        ResultMap resultMap = new ResultMap();

        File rootDir = new File(ROOT_PATH);

        if(rootDir.list().length > 0) {
            resultMap.put("videoList", rootDir.list());
        } else {
            throw new NotFoundException("NotFound");
        }

        ResponseEntity response = new ResponseEntity(resultMap, HttpStatus.OK);

        return response;
    }

    @GetMapping("/v1/getImageList")
    public ResponseEntity imageSet(@RequestParam String videoName) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(videoName == null || videoName.equals("")) {
            throw new NotFoundException("NotFound");
        }

        String dirPath = ROOT_PATH + File.separator + videoName;
        File dir = new File(dirPath);
        if(dir.isDirectory()) {
            List<Map<String, String>> imageList = new ArrayList<Map<String, String>>();
            for(String fileName : dir.list()) {
                Map<String, String> imageMap = new HashMap<String, String>();
                imageMap.put("imageName", fileName);
                imageMap.put("imagePath", "/video/" + videoName + "/" + fileName);
                imageList.add(imageMap);
            }
            resultMap.put("imageList", imageList);
        } else {
            throw new NotFoundException("NotFound");
        }

        ResponseEntity response = new ResponseEntity(resultMap, HttpStatus.OK);

        return response;
    }

    @GetMapping("/v1/sift/search")
    public ResponseEntity queryImage(@RequestParam String filePath) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(filePath == null || filePath.equals("")) {
            throw new NotFoundException("NotFound");
        }

        String dirPath = ROOT_PATH + File.separator + filePath.replaceAll("/video/", "");

        String cmd = "C:/ProgramData/Anaconda3/python.exe \"d:/05.python/vision_test/sift_es copy.py\" " + dirPath;
        Process p = null;

        String str = "";

        try {
            p = Runtime.getRuntime().exec("cmd /c " + cmd);

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String l = null;
            StringBuffer sb = new StringBuffer();
//            sb.append(cmd);

            while ((l = r.readLine()) != null) {
                sb.append(l);
                sb.append("\n");
            }
            str = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        ResponseEntity response = new ResponseEntity(str, HttpStatus.OK);

        return response;
    }
}
