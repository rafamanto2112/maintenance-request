package br.com.vital.maintenancerequest.domain.maintenanceRequests;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

import br.com.vital.maintenancerequest.domain.DomainException;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Contract {

	private final Long number;
	private final String name;
	private final String cnpj;
	private String manager;
	private final LocalDate effectiveDate;

	public Contract(final Long number, final String name, final String cnpj, final String manager, final LocalDate effectiveDate) {
		validate(number, name, cnpj, manager, effectiveDate);

		this.number = number;
		this.name = name;
		this.cnpj = cnpj;
		this.manager = manager;
		this.effectiveDate = effectiveDate;
	}

	public void validate(final Long number, final String name, final String cnpj, final String manager, final LocalDate effectiveDate) {
		if(Objects.isNull(name) || name.trim().isEmpty()) {
			throw new DomainException("D00302", "Contract Name invalid");
		}

		if(Objects.isNull(cnpj)) {
			throw new DomainException("D00305", "Contract invalid");
		} else if(!isValidCnpj(cnpj)) {
			throw new DomainException("D00306", "Invalid CNPJ");
		}

		if(Objects.isNull(number)) {
			throw new DomainException("D00301", "Invalid Contract Number");
		}

		if(Objects.isNull(manager) || manager.trim().isEmpty()) {
			throw new DomainException("D00307", "The manager must be informed");
		}

		if(Objects.isNull(effectiveDate)) {
			throw new DomainException("D00303", "The effective date must be informed");
		} else {
			validateEffetiveDate(effectiveDate);
		}
	}



	private void validateEffetiveDate(final LocalDate effectiveDate) {
		if(effectiveDate.isBefore(LocalDate.now())) {
			throw new DomainException("D00304", "Contract expired");
		}
	}

	private boolean isValidCnpj(final String cnpj) {
		if(cnpj.trim().length() < 14) {
			return false;
		}

		final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

		return pattern.matcher(cnpj).matches();
	}

}
