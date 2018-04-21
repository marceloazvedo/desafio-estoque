package br.com.controleestoque.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.controleestoque.model.enums.Dispositivo;

public class StringUtils {

	private static final String[] CARACTERES ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	private static final String FORMATO_DATA_SEM_HORA = "dd/MM/yyyy";
	private static final String FORMATO_DATA_COMPLETO_MILIS = "dd/MM/yyyy HH:mm:ss.SSS";
	private static final String FORMATO_DATA_COMPLETO = "dd/MM/yyyy HH:mm:ss";
	
	public static String generatePassword(){
		String senha = "";
		for (int x=0; x<10; x++){
	        int j = (int) (Math.random()*CARACTERES.length);
	        senha += CARACTERES[j];
		}
		return senha;
	}
	
	public static String generateLoginFromEmail(String email){
		String values[] = email.split("@");
		return values[0];
	}
	
	public static String getTokenHash(Long id, String senha, Dispositivo dispositivo) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		StringBuilder fullPassword = new StringBuilder();
		fullPassword.append(String.valueOf(id))
			.append(getDataCompletaMilis(Calendar.getInstance()))
			.append(senha)
			.append(dispositivo.name());

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(fullPassword.toString().getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

	public static String getDataSemHoras(Calendar calendar){
		Date minhaData = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DATA_SEM_HORA);
		return simpleDateFormat.format(minhaData);
	}
	
	public static String getDataCompleta(Calendar calendar){
		Date minhaData = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DATA_COMPLETO);
		return simpleDateFormat.format(minhaData);
	}
	
	private static String getDataCompletaMilis(Calendar calendar){
		Date minhaData = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DATA_COMPLETO);
		return simpleDateFormat.format(minhaData);
	}
	
	public static String limparCpfCnpj(String cpfCnpj){
		String newCpfCnpj = "";
		newCpfCnpj = cpfCnpj.replace(".", "").replace("-", "").replace(",", "").replace("/", "");
		return newCpfCnpj; 
	}
	
	public static Calendar getCalendarFullDate(String data) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA_COMPLETO);
		calendar.setTime(sdf.parse(data));
		return calendar;
	}
	
	public static Calendar getCalendarSimpleDate(String data) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA_SEM_HORA);
		calendar.setTime(sdf.parse(data));
		return calendar;
	}
}
