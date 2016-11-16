package com.mkmnav.backend;

/**
 * Created by chapa on 14-11-2016.
 */

public enum CACertificateStrings {
    //CA certificate for magiccardmarket
    MKM ("-----BEGIN CERTIFICATE-----\n" +
            "MIIH5DCCBsygAwIBAgIQS0DQ/r4WtsIiYKd0wuI/kDANBgkqhkiG9w0BAQsFADB4\n" +
            "MQswCQYDVQQGEwJJTDEWMBQGA1UEChMNU3RhcnRDb20gTHRkLjEpMCcGA1UECxMg\n" +
            "U3RhcnRDb20gQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxJjAkBgNVBAMTHVN0YXJ0\n" +
            "Q29tIENsYXNzIDMgT1YgU2VydmVyIENBMB4XDTE2MTAyODE2MzAyNVoXDTE5MTAy\n" +
            "ODE2MzAyNVowdTELMAkGA1UEBhMCREUxDzANBgNVBAgMBkJlcmxpbjEPMA0GA1UE\n" +
            "BwwGQmVybGluMScwJQYDVQQKDB5TYW1tZWxrYXJ0ZW5tYXJrdCBMdGQuICYgQ28g\n" +
            "S0cxGzAZBgNVBAMMEm1hZ2ljY2FyZG1hcmtldC5ldTCCASIwDQYJKoZIhvcNAQEB\n" +
            "BQADggEPADCCAQoCggEBAPOCNjnVakwPlYfnHxZci66YLhXe9WPlYdOFoJgNhoq7\n" +
            "Vlu2bjGrdz28bkP6k/l9ixLIpkGq9QZEezZ8ZnwnlAh9ni23evS8gl/Z3kUbUSzv\n" +
            "FuGW+cW7b/syZlpx6VLEjx8PbDV5jqsUW06DNsAc6jidqm1hLyTbDlb87y5VJFEg\n" +
            "WBum14oAFCkDWRtxwSglZ+UPw7w6Qtbeh4ZuyznlVIlLbKjvnG5V66PkU2k189/a\n" +
            "7aZuax+D6AUwLQMpLBX9lS/zqpYfC9IwnhMbJWxMeXPwEcvc96nllum09nYgGHyv\n" +
            "hGzu3kn6f9lZcJk6Futa3nFjel6DVHwSE+ktjr28H/sCAwEAAaOCBGswggRnMA4G\n" +
            "A1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggrBgEFBQcDAgYIKwYBBQUHAwEwCQYD\n" +
            "VR0TBAIwADAdBgNVHQ4EFgQUd7hL9Ae/0BGvazAbufJQic3FzO0wHwYDVR0jBBgw\n" +
            "FoAUsT8cknuSsFolszj7nAekJlAy41EwbwYIKwYBBQUHAQEEYzBhMCQGCCsGAQUF\n" +
            "BzABhhhodHRwOi8vb2NzcC5zdGFydHNzbC5jb20wOQYIKwYBBQUHMAKGLWh0dHA6\n" +
            "Ly9haWEuc3RhcnRzc2wuY29tL2NlcnRzL3NjYS5zZXJ2ZXIzLmNydDA4BgNVHR8E\n" +
            "MTAvMC2gK6AphidodHRwOi8vY3JsLnN0YXJ0c3NsLmNvbS9zY2Etc2VydmVyMy5j\n" +
            "cmwwgcwGA1UdEQSBxDCBwYISbWFnaWNjYXJkbWFya2V0LmV1ghQqLm1hZ2ljY2Fy\n" +
            "ZG1hcmtldC5ldYIXKi5kZS5tYWdpY2NhcmRtYXJrZXQuZXWCFyouZnIubWFnaWNj\n" +
            "YXJkbWFya2V0LmV1ghcqLmVzLm1hZ2ljY2FyZG1hcmtldC5ldYIXKi5pdC5tYWdp\n" +
            "Y2NhcmRtYXJrZXQuZXWCFyouZW4ubWFnaWNjYXJkbWFya2V0LmV1ghgqLnd3dy5t\n" +
            "YWdpY2NhcmRtYXJrZXQuZXUwIwYDVR0SBBwwGoYYaHR0cDovL3d3dy5zdGFydHNz\n" +
            "bC5jb20vMFEGA1UdIARKMEgwCAYGZ4EMAQICMDwGCysGAQQBgbU3AQIFMC0wKwYI\n" +
            "KwYBBQUHAgEWH2h0dHBzOi8vd3d3LnN0YXJ0c3NsLmNvbS9wb2xpY3kwggH3Bgor\n" +
            "BgEEAdZ5AgQCBIIB5wSCAeMB4QB2ADS7atbD35wD7qikmf94kUhsnV5crJLQH3v9\n" +
            "G84Z20jvAAABWAw39qQAAAQDAEcwRQIhAInLOuK9zR4KlC1S0hXApnId5F4jYAh6\n" +
            "TDEAjsb3sGwXAiA3zaZ3PJUMti2lUT0my0jw2FT5x9hhY4Zmi4gNPzbfTQB3AO5L\n" +
            "vbd1zmC64UJpH6vhnmajD35fsHLYgwDEe4l6qP3LAAABWAw3/tcAAAQDAEgwRgIh\n" +
            "AKboROamg1/hGimhqt6lPu6xNeveGDCXRzYNZKruWAZAAiEA5QYUHujDrgovVLLv\n" +
            "xIU20ueFPfRM+FK8afPvRAAtG38AdwCkuQmQtBhYFIe7E6LMZ3AKPDWYBPkb37jj\n" +
            "d80OyA3cEAAAAVgMN/s+AAAEAwBIMEYCIQCTFerdaGZlVuBgoE7IHG46ZuLYntDJ\n" +
            "RLBIiegDpy7woAIhAMedltRe8BzWBm5evNSsvcshGIRR5wzk2ZabvXJMeEIYAHUA\n" +
            "aPaY+B9kgr46jO65KB1M/HFRXWeT1ETRCmesu09P+8QAAAFYDDf8igAABAMARjBE\n" +
            "AiAR44npoVJLyJr53vDSXdcjPCSF5xXsvrRA1UYisd2jSwIgQLXAlw3I7CILukbF\n" +
            "V2ZXdiqVTkDTk/J0xP7+xTL7TB8wDQYJKoZIhvcNAQELBQADggEBACRVkNrihn0i\n" +
            "oVRFeilU82bqC5J8cmqmK3rOI8HSr5oRHfO1Z9l6hylz/h7HPVktbz/WQlk4MSr4\n" +
            "lkk8Vcy6tdt0iVgGWtXaeuuWCOm17fZiw4CYisBAViBLUOV5C5uIxXtm+HkBpcar\n" +
            "2WIlfRXrNDYqTUXa8JKbAFDajOvZIXmUhcUrFrJuFTIuZU+/lwtUJtEtNnVAEEAD\n" +
            "El3YH02UYBjuBk3Bfi5EzkSSPMoCvlf7Q9uiIGd62j+rfaEeei9zVUCLSKE2Z/V2\n" +
            "dqb0zVGIXIJpjMp7getoDUy2qA2zqTQGXzmAKKa646S1STDtjdv8Ax3MqcpGt3qC\n" +
            "4Uree+Y8fXQ=\n" +
            "-----END CERTIFICATE-----\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIF5TCCA82gAwIBAgIQE4v+8zKU+dgW+UXCcZUpmDANBgkqhkiG9w0BAQsFADB9\n" +
            "MQswCQYDVQQGEwJJTDEWMBQGA1UEChMNU3RhcnRDb20gTHRkLjErMCkGA1UECxMi\n" +
            "U2VjdXJlIERpZ2l0YWwgQ2VydGlmaWNhdGUgU2lnbmluZzEpMCcGA1UEAxMgU3Rh\n" +
            "cnRDb20gQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwHhcNMTUxMjE2MDEwMDA1WhcN\n" +
            "MzAxMjE2MDEwMDA1WjB4MQswCQYDVQQGEwJJTDEWMBQGA1UEChMNU3RhcnRDb20g\n" +
            "THRkLjEpMCcGA1UECxMgU3RhcnRDb20gQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkx\n" +
            "JjAkBgNVBAMTHVN0YXJ0Q29tIENsYXNzIDMgT1YgU2VydmVyIENBMIIBIjANBgkq\n" +
            "hkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr2ccb+VF4NdGS3UstoDymhdNLf/ertLU\n" +
            "AIo6uDH+jjee+qrVo1sWEsEZPjSFlsO+07ND9I1vFr0wugf82JrBeYmAbaCMvt03\n" +
            "9+sF01N/V1h2VbaoqIZEuLvQE9r9j+HyzaAVOFVWzibPfJN1KXoKq/u6CTggEVcH\n" +
            "XX9JnypKZx6eWOnHf/nD7f5fTa+4T53faS1pGzpYgWljMOqHjQ9SnVraOUS6n4mf\n" +
            "NrbCGVzZJnjZrl78lZC/6BHARw93id1qKE8KvDJkV0M9CGWT5UWu3SgMJyyOpisJ\n" +
            "A12heNKMq7ZruUbJGQA5ub/GEytzch/yPje46LkUZYhN4vEb2KUdOwIDAQABo4IB\n" +
            "ZDCCAWAwDgYDVR0PAQH/BAQDAgEGMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEF\n" +
            "BQcDATASBgNVHRMBAf8ECDAGAQH/AgEAMDIGA1UdHwQrMCkwJ6AloCOGIWh0dHA6\n" +
            "Ly9jcmwuc3RhcnRzc2wuY29tL3Nmc2NhLmNybDBmBggrBgEFBQcBAQRaMFgwJAYI\n" +
            "KwYBBQUHMAGGGGh0dHA6Ly9vY3NwLnN0YXJ0c3NsLmNvbTAwBggrBgEFBQcwAoYk\n" +
            "aHR0cDovL2FpYS5zdGFydHNzbC5jb20vY2VydHMvY2EuY3J0MB0GA1UdDgQWBBSx\n" +
            "PxySe5KwWiWzOPucB6QmUDLjUTAfBgNVHSMEGDAWgBROC+8apEBbpRdphzDKNGhD\n" +
            "0EGu8jA/BgNVHSAEODA2MDQGBFUdIAAwLDAqBggrBgEFBQcCARYeaHR0cDovL3d3\n" +
            "dy5zdGFydHNzbC5jb20vcG9saWN5MA0GCSqGSIb3DQEBCwUAA4ICAQCF8ugU0xvB\n" +
            "oRYdpPRNulGLXFKxVFQSFheclnhv07/fQzb1EolhckTfHJsJT2AmaMHmZlBws2rx\n" +
            "qGoMHi6T8e4HPgndMEWyVo7cLFyrSfq5BANAFXq1MOAdkY+m1m8fmaCElTm9rHd/\n" +
            "ckvdLa7/qFgdRifUg8dpZJ8ZuxD4BEKHWV0CsdblyNpDMKPoN6XSSAuig06dT4NY\n" +
            "nddHIrGJ8Ik7PShDLJsXfAPunSYl4AS4HQRXQkfaWGnw0ymrEgKZKyrYnaAfVF4j\n" +
            "mgzSmVjEoeVJwiWnZCBSLueJ9RnAi9BjsXgevgFHvnaBRvGZH5SavvqCFbWEhHl1\n" +
            "k7qfteSbwstpXL0fVQqnJjAFUb5l7lepat+9+TYvrR5GQSuxiNCIJYVAF3m/PY3i\n" +
            "9C3qMDHfoUDLNf+Cn/WZPEr9naHRVcwgqBzYIAWrsxRllVPY6I5XxXdrLU2I6V1i\n" +
            "1aL4cOFw60UjDvAARsJIMejnNoA2LSLyASdT686naUmCv+cPnPMgLvX6Xc7qWDqP\n" +
            "2Kp9MLd0lnw9brTsSjtZtqlQDQ8FBnAmuZWR0V4kjI/KdFeXkItat/6Nrdjowga8\n" +
            "CFYhAhJTxp+GBFjKLfgDDVcLHDe98Fo18v471qQ3Fen4CJKWPXTItVxuZQjn32lz\n" +
            "nOzjMFqm31y+2n8A7qXaK1weKmrAo64e8Q==\n" +
            "-----END CERTIFICATE-----\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIHhzCCBW+gAwIBAgIBLTANBgkqhkiG9w0BAQsFADB9MQswCQYDVQQGEwJJTDEW\n" +
            "MBQGA1UEChMNU3RhcnRDb20gTHRkLjErMCkGA1UECxMiU2VjdXJlIERpZ2l0YWwg\n" +
            "Q2VydGlmaWNhdGUgU2lnbmluZzEpMCcGA1UEAxMgU3RhcnRDb20gQ2VydGlmaWNh\n" +
            "dGlvbiBBdXRob3JpdHkwHhcNMDYwOTE3MTk0NjM3WhcNMzYwOTE3MTk0NjM2WjB9\n" +
            "MQswCQYDVQQGEwJJTDEWMBQGA1UEChMNU3RhcnRDb20gTHRkLjErMCkGA1UECxMi\n" +
            "U2VjdXJlIERpZ2l0YWwgQ2VydGlmaWNhdGUgU2lnbmluZzEpMCcGA1UEAxMgU3Rh\n" +
            "cnRDb20gQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwggIiMA0GCSqGSIb3DQEBAQUA\n" +
            "A4ICDwAwggIKAoICAQDBiNsJvGxGfHiflXu1M5DycmLWwTYgIiRezul38kMKogZk\n" +
            "pMyONvg45iPwbm2xPN1yo4UcodM9tDMr0y+v/uqwQVlntsQGfQqedIXWeUyAN3rf\n" +
            "OQVSWff0G0ZDpNKFhdLDcfN1YjS6LIp/Ho/u7TTQEceWzVI9ujPW3U3eCztKS5/C\n" +
            "Ji/6tRYccjV3yjxd5srhJosaNnZcAdt0FCX+7bWgiA/deMotHweXMAEtcnn6RtYT\n" +
            "Kqi5pquDSR3l8u/d5AGOGAqPY1MWhWKpDhk6zLVmpsJrdAfkK+F2PrRt2PZE4XNi\n" +
            "HzvEvqBTViVsUQn3qqvKv3b9bZvzndu/PWa8DFaqr5hIlTpL36dYUNk4dalb6kMM\n" +
            "Av+Z6+hsTXBbKWWc3apdzK8BMewM69KN6Oqce+Zu9ydmDBpI125C4z/eIT574Q1w\n" +
            "+2OqqGwaVLRcJXrJosmLFqa7LH4XXgVNWG4SHQHuEhANxjJ/GP/89PrNbpHoNkm+\n" +
            "Gkhpi8KWTRoSsmkXwQqQ1vp5Iki/untp+HDH+no32NgN0nZPV/+Qt+OR0t3vwmC3\n" +
            "Zzrd/qqc8NSLf3Iizsafl7b4r4qgEKjZ+xjGtrVcUjyJthkqcwEKDwOzEmDyei+B\n" +
            "26Nu/yYwl/WL3YlXtq09s68rxbd2AvCl1iuahhQqcvbjM4xdCUsT37uMdBNSSwID\n" +
            "AQABo4ICEDCCAgwwDwYDVR0TAQH/BAUwAwEB/zAOBgNVHQ8BAf8EBAMCAQYwHQYD\n" +
            "VR0OBBYEFE4L7xqkQFulF2mHMMo0aEPQQa7yMB8GA1UdIwQYMBaAFE4L7xqkQFul\n" +
            "F2mHMMo0aEPQQa7yMIIBWgYDVR0gBIIBUTCCAU0wggFJBgsrBgEEAYG1NwEBATCC\n" +
            "ATgwLgYIKwYBBQUHAgEWImh0dHA6Ly93d3cuc3RhcnRzc2wuY29tL3BvbGljeS5w\n" +
            "ZGYwNAYIKwYBBQUHAgEWKGh0dHA6Ly93d3cuc3RhcnRzc2wuY29tL2ludGVybWVk\n" +
            "aWF0ZS5wZGYwgc8GCCsGAQUFBwICMIHCMCcWIFN0YXJ0IENvbW1lcmNpYWwgKFN0\n" +
            "YXJ0Q29tKSBMdGQuMAMCAQEagZZMaW1pdGVkIExpYWJpbGl0eSwgcmVhZCB0aGUg\n" +
            "c2VjdGlvbiAqTGVnYWwgTGltaXRhdGlvbnMqIG9mIHRoZSBTdGFydENvbSBDZXJ0\n" +
            "aWZpY2F0aW9uIEF1dGhvcml0eSBQb2xpY3kgYXZhaWxhYmxlIGF0IGh0dHA6Ly93\n" +
            "d3cuc3RhcnRzc2wuY29tL3BvbGljeS5wZGYwEQYJYIZIAYb4QgEBBAQDAgAHMDgG\n" +
            "CWCGSAGG+EIBDQQrFilTdGFydENvbSBGcmVlIFNTTCBDZXJ0aWZpY2F0aW9uIEF1\n" +
            "dGhvcml0eTANBgkqhkiG9w0BAQsFAAOCAgEAjo/n3JR5fPGFf59Jb2vKXfuM/gTF\n" +
            "wWLRfUKKvFO3lANmMD+x5wqnUCBVJX92ehQN6wQOQOY+2IirByeDqXWmN3PH/UvS\n" +
            "Ta0XQMhGvjt/UfzDtgUx3M2FIk5xt/JxXrAaxrqTi3iSSoX4eA+D/i+tLPfkpLst\n" +
            "0OcNOrg+zvZ49q5HJMqjNTbOx8aHmNrs++myziebiMMEofYLWWivydsQD032ZGNc\n" +
            "pRJvkrKTlMeIFw6Ttn5ii5B/q06f/ON1FE8qMt9bDeD1e5MNq6HPh+GlBEXoPBKl\n" +
            "CcWw0bdT82AUuoVpaiF8H3VhFyAXe2w7QSlc4axa0c2Mm+tgHRns9+Ww2vl5GKVF\n" +
            "P0lDV9LdJNUso/2RjSe15esUBppMeyG7Oq0wBhjA2MFrLH9ZXF2RsXAiV+uKa0hK\n" +
            "1Q8p7MZAwC+ITGgBF3f0JBlPvfrhsiAhS90a2Cl9qrjeVOwhVYBsHvUwyKMQ5bLm\n" +
            "KhQxw4UtjJixhlpPiVktucf3HMiKf8CdBUrmQk9io20ppB+Fq9vlgcitKj1MXVuE\n" +
            "JnHEhV5xJMqlG2zYYdMa4FTbzrqpMrUi9nNBCV24F10OD5mQ1kfabwo6YigUZ4LZ\n" +
            "8dCAWZvLMdibD4x3TrVoivJs9iQOLWxwxXPR3hTQcY+203sC9uO41Alua551hDnm\n" +
            "fyWl8kgAwKQB2j8=\n" +
            "-----END CERTIFICATE-----");


    public final String content;

    private CACertificateStrings(String in){
        this.content = in;
    }

}
