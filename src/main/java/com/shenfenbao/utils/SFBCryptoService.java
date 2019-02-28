package com.shenfenbao.utils;

public class SFBCryptoService {
	static {
		System.loadLibrary("libsfbcrypto");
	}
	
	public static native byte[] tripleDESEncrypt(String key, String src);
	
	public static native String tripleDESDecrypt(String key, byte[] encryptData, int dataLen);
}
