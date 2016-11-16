package com.mkmnav.backend;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public abstract class ProgramInput {

    public static String getHTMLFromFile(File inputFileName) {
		FileReader fis = null;
		int foo;
		String bar = "";
		try {
            fis = new FileReader(inputFileName);
			while ((foo = fis.read() )!=-1){
                String x = String.valueOf((char) foo);
				bar = bar + x;
			}
			fis.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
		return bar;
    }




    public static String getHTMLFromURLThroughHTTPS(String urlString, CACertificateStrings caCert)  {

        String res = "";

        try {

            InputStream caInput = new ByteArrayInputStream(caCert.content.getBytes("UTF-8") );
            SSLContext sslContext = constructSSLContext(caInput);

            HttpsURLConnection urlConnection =  (HttpsURLConnection) (new URL(urlString)).openConnection();
            urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

            urlConnection.setRequestProperty("User-Agent", Motor.mHTTPUserAgent);

            InputStream in = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String foo;
            while ((foo = br.readLine()) != null) {
                res = res + foo;
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }


    //constructs and sslcontext that are used for https connection
    //the input is the certificate input stream
    public static SSLContext constructSSLContext(InputStream caCertificateInputStream){
        SSLContext sslContext = null;
        try{

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca = cf.generateCertificate(caCertificateInputStream);
            caCertificateInputStream.close();

            KeyStore keyStore  = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            //algorithm
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);


        } catch (Exception e){
            e.printStackTrace();
        }

        return sslContext;

    }
}
