/**
*Eli Schmitter
*Wesley Grove
**/


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Consumer;

public class Week12InClass implements Iterable {
	private final String KEY = "flippertygibbet";
	private final String TRANSFORMATION = "AES";
	private final String ALGORITHM = "SHA-1";

	private HashMap<String, String> pwds = new HashMap<String, String>();
	private HashMap<String, String> salts = new HashMap<String, String>();

	/**
	 * Create a new password
	 * Validates password has 1 uppercase, 1 lowercase, 1 symbol, 1 number, at least 12 characters
	 * @param userid
	 * @param password
	 */
	public void createPassword(String userid, String password) {
		if (password.length()>=12){
			if(password.matches(".*\\d.*")&&password.matches(".*[!@#$%^&*].*")&&!password.equals(password.toLowerCase())&& !password.equals(password.toUpperCase())){
				String salt= generateSalt();
				pwds.put(userid,encrypt(password,salt));
				salts.put(userid,salt);
			}
			else{
				System.out.println("bad password");

			}

		}
		else{
			System.out.println("bad password");

		}


	}

	/** change password
	 * old password must match
	 * Validates new password has 1 uppercase, 1 lowercase, 1 symbol, 1 number, at least 12 characters
	 * @param userid
	 * @param oldPassword
	 * @param newPassword
	 */
	public void changePassword(String userid, String oldPassword, String newPassword) {
	if(decrypt(pwds.get(userid),salts.get(userid)).equals(oldPassword)){
		createPassword(userid,newPassword);
	}
	}

	/** Retrieves the password
	 *
	 * @param userid
	 * @return
	 */
	private String retrievePassword( String userid ) {
		return decrypt(pwds.get(userid), salts.get(userid));
	}

	/**
	 * Iterates through the a list of encrypted password, salt combinations
	 * (Does not iterate through unencrypted passwords)
	 * @return
	 */
	public Iterator iterator ( )  {
		ArrayList<String> it= new ArrayList();
		for(Map.Entry<String, String> i : pwds.entrySet()) {
			it.add(pwds.get(i));
			it.add(salts.get(i));
		}
		return it.iterator();
	}

	private String generateSalt() {
		return Long.toString(System.currentTimeMillis());
	}

	private String encrypt(String plaintext, String salt) {
		if (plaintext == null || plaintext.isEmpty()) {
			throw new IllegalArgumentException ( "Bad Input" );
		}
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);

			byte[] key = (salt+KEY).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance(ALGORITHM);
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit

			SecretKeySpec secretKeySpec = new SecretKeySpec(key, TRANSFORMATION);

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

			byte[] inputBytes = plaintext.getBytes();
			byte[] outputBytes = cipher.doFinal(inputBytes);

			return Base64.getEncoder ( ).encodeToString ( outputBytes);
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace ( );
		} catch ( InvalidKeyException e ) {
			e.printStackTrace ( );
		} catch ( NoSuchPaddingException e ) {
			e.printStackTrace ( );
		} catch ( BadPaddingException e ) {
			e.printStackTrace ( );
		} catch ( IllegalBlockSizeException e ) {
			e.printStackTrace ( );
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace ( );
		}
		return null;
	}

	private String decrypt(String encryptedText, String salt) {
		if (encryptedText == null || encryptedText.isEmpty()) {
			throw new IllegalArgumentException ( "Bad Input" );
		}
		try {
			Cipher c = Cipher.getInstance(TRANSFORMATION);
			byte[] keyBytes = Arrays.copyOf ( MessageDigest.getInstance(ALGORITHM).digest((salt+KEY).getBytes("UTF-8")), 16);
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, TRANSFORMATION));
			return new String(c.doFinal(Base64.getDecoder().decode(encryptedText)));
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace ( );
		} catch ( InvalidKeyException e ) {
			e.printStackTrace ( );
		} catch ( NoSuchPaddingException e ) {
			e.printStackTrace ( );
		} catch ( BadPaddingException e ) {
			e.printStackTrace ( );
		} catch ( IllegalBlockSizeException e ) {
			e.printStackTrace ( );
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace ( );
		}
		return null;
	}

	public static void main( String [] args ) {
		Week12InClass foo = new Week12InClass();
		foo.createPassword ( "CS1122", "FUBARaaaaaa@aaaa1" );
		System.out.println( foo.retrievePassword ( "CS1122" ) );
		System.out.println(foo.pwds);
		System.out.println(foo.salts);
	}


}
