package sheetsoft.com.mkmnavigator.android.backend;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

public abstract class ProgramInput {

    public class TimeoutException extends Exception{

    }

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
        } finally {
            return bar;
        }
    }


    public static HttpsURLConnection setupHTTPSURLConnection(String urlString,CACertificateStrings caCert){

        HttpsURLConnection httpsURLConnection;

        try {

            InputStream caInput = new ByteArrayInputStream(caCert.content.getBytes("UTF-8") );
            SSLContext sslContext;

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca = cf.generateCertificate(caInput);
            caInput.close();

            KeyStore keyStore  = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            httpsURLConnection = (HttpsURLConnection) (new URL(urlString)).openConnection();
            httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());

            httpsURLConnection.setRequestProperty("User-Agent", Motor.mHTTPUserAgent);

            return httpsURLConnection;

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static BufferedReader getBufferedReaderFromHTTPSConnection(HttpsURLConnection in){

        BufferedReader br=null;

        try {
            br = new BufferedReader(new InputStreamReader(in.getInputStream()));
            return br;
        } catch (IOException e) {
//            Log.e("TAGG",e.getMessage());
        }
        return br;
    }


    public static String getHTMLWithSSLLineByLine(BufferedReader in){

        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO implement a method that returns the html string by string and deals with possible timeouts


}
