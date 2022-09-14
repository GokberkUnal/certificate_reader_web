package com.example.certificate_reader.utils;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CrtFileReader{

    public ECertificate crtFileReader(String path)throws Exception{
        File file = new File(path);

        byte[] result;

        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            result = AsnIO.streamOku(input);
        }

        ECertificate eCertificate = new ECertificate(result);
        System.out.println(eCertificate.getPublicKeyAlgorithm());
        return eCertificate;
    }
}
