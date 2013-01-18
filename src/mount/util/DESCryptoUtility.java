package mount.util;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;

public class DESCryptoUtility {

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public static byte[] encrypt(byte[] arrB, String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		Key key = getKey(strKey.getBytes());

		Cipher encryptCipher = Cipher.getInstance("DES");

		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		return encryptCipher.doFinal(arrB);
	}

	public static String encrypt(String strIn, String strKey) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes(), strKey));
	}

	public static byte[] decrypt(byte[] arrB, String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		Cipher decryptCipher = Cipher.getInstance("DES");

		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		return decryptCipher.doFinal(arrB);
	}

	public static String decrypt(String strIn, String strKey) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn), strKey));
	}

	private static Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}
}
