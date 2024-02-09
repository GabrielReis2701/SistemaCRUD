import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class CriptografarMensagem {
	
	 // Criptografar
	public static String  Criptografar(String salt,String mensagem) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
	    SecretKeySpec keySpec = new SecretKeySpec(salt.getBytes(), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	    byte[] encryptedMessage = cipher.doFinal(mensagem.getBytes());
	    String encryptedMessageBase64 = Base64.getEncoder().encodeToString(encryptedMessage);
		return encryptedMessageBase64;
	}
    

    // Descriptografar
	public static String Descriptografar(String salt,String mensagem) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
	    SecretKeySpec keySpec = new SecretKeySpec(salt.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(mensagem));
		
		return new String(decryptedMessage);
	}
   

}
