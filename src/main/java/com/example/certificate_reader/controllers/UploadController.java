package com.example.certificate_reader.controllers;

import com.example.certificate_reader.utils.CrtFileReader;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {

    private final String UPLOAD_DIR = "/Users/goku/IdeaProjects/certificate_reader/src/main/resources/static/uploads/";

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @PostMapping("/certificate")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        ECertificate cert;
        CrtFileReader crtfilereader=new CrtFileReader();


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);


        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println(path);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            cert=crtfilereader.crtFileReader(path.toString());
            System.out.println(cert.getEmail());
            attributes.addAttribute("post",cert.getEmail());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return "certificate";
    }



}
