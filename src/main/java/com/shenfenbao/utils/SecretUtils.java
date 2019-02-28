package main.java.com.shenfenbao.utils;

import java.io.UnsupportedEncodingException;
 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
 
 
/**
 * main.java.com.shenfenbao.utils.SecretUtils {3DES���ܽ��ܵĹ����� }
 * @author William
 * @date 2013-04-19
 */
public class SecretUtils {
 
    //��������㷨����DES��DESede(��3DES)��Blowfish
    private static final String Algorithm = "DESede";    
    private static final String PASSWORD_CRYPT_KEY = "296CD6EB2CA94321ABEF575F4CFC10EC";
    
    

	public static String byteToHex(byte b){  
	    String hex = Integer.toHexString(b & 0xFF);  
	    if(hex.length() < 2){  
	        hex = "0" + hex;  
	    }  
	    return hex;  
	}
	
	/** 
	 * �ֽ�����ת16���� 
	 * @param bytes ��Ҫת����byte���� 
	 * @return  ת�����Hex�ַ��� 
	 */  
	public static String bytesToHex(byte[] bytes) {  
	    StringBuffer sb = new StringBuffer();  
	    for(int i = 0; i < bytes.length; i++) {  
	        String hex = Integer.toHexString(bytes[i] & 0xFF);  
	        if(hex.length() < 2){  
	            sb.append(0);  
	        }  
	        sb.append(hex);  
	    }  
	    return sb.toString();  
	}  
	
	
	/** 
	 * Hex�ַ���תbyte 
	 * @param inHex ��ת����Hex�ַ��� 
	 * @return  ת�����byte 
	 */  
	public static byte hexToByte(String inHex){  
	   return (byte)Integer.parseInt(inHex,16);  
	}
	
	/** 
	 * hex�ַ���תbyte���� 
	 * @param inHex ��ת����Hex�ַ��� 
	 * @return  ת�����byte������ 
	 */  
	public static byte[] hexToByteArray(String inHex){  
	    int hexlen = inHex.length();  
	    byte[] result;  
	    if (hexlen % 2 == 1){  
	        //����  
	        hexlen++;  
	        result = new byte[(hexlen/2)];  
	        inHex="0"+inHex;  
	    }else {  
	        //ż��  
	        result = new byte[(hexlen/2)];  
	    }  
	    int j=0;  
	    for (int i = 0; i < hexlen; i+=2){  
	        result[j]=hexToByte(inHex.substring(i,i+2));  
	        j++;  
	    }  
	    return result;   
	}  



    /**
     * ���ܷ���
     * @param src Դ���ݵ��ֽ�����
     * @return 
     */
    public static byte[] encryptMode(byte[] src) {
        try {
        	 SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
             Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");    //ʵ�����������/���ܵ�Cipher������
             c1.init(Cipher.ENCRYPT_MODE, deskey);    //��ʼ��Ϊ����ģʽ
             return c1.doFinal(src);
         } catch (java.security.NoSuchAlgorithmException e1) {
             e1.printStackTrace();
         } catch (javax.crypto.NoSuchPaddingException e2) {
             e2.printStackTrace();
         } catch (java.lang.Exception e3) {
             e3.printStackTrace();
         }
         return null;
     }
    
    
    /**
     * ���ܺ���
     * @param src ���ĵ��ֽ�����
     * @return
     */
    public static byte[] decryptMode(byte[] src) {  
    	try
    	{
    		SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);    //��ʼ��Ϊ����ģʽ
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
     }
    
    
    /*
     * �����ַ���������Կ�ֽ����� 
     * @param keyStr ��Կ�ַ���
     * @return 
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
        byte[] key = new byte[24];
        byte[] key16B = hexToByteArray(keyStr);
        System.arraycopy(key16B, 0, key, 0, 16);
        System.arraycopy(key16B, 0, key, 16, 8);
        return key;
    } 
}