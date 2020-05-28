package br.com.vital.maintenancerequest.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import javax.xml.bind.DatatypeConverter;

import org.springframework.context.annotation.Configuration;

import br.com.vital.maintenancerequest.exceptions.TechinicalException;

@Configuration
public class GuidGenerator {

	public String generate() {
		final LocalTime localTime = LocalTime.now();
		final LocalDate localDate = LocalDate.now();

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(localDate.getYear())
		.append(localDate.getMonth())
		.append(localDate.getDayOfMonth())
		.append(localTime.getHour())
		.append(localTime.getMinute())
		.append(localTime.getSecond())
		.append(localTime.get(ChronoField.MILLI_OF_SECOND))
		.append(new SecureRandom().nextInt(localDate.getYear()));

		final MessageDigest messageDigest = generateMessageDigest();

		messageDigest.update(toByteArray(stringBuilder.toString(), StandardCharsets.UTF_8.displayName()));

		return new String(DatatypeConverter.printHexBinary(messageDigest.digest())).substring(0, 15);
	}


	public byte[] toByteArray(final String text, final String charset) {
		byte[] planText;
		try {
			planText = text.getBytes(charset);
			return planText;
		} catch (final UnsupportedEncodingException e) {
			throw new TechinicalException("Fail to instance guid", e);
		}

	}

	public MessageDigest generateMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (final NoSuchAlgorithmException e) {
			throw new TechinicalException("Fail to instance guid", e);
		}
	}

}
