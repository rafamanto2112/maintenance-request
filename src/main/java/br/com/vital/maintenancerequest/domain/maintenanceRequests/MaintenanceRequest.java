package br.com.vital.maintenancerequest.domain.maintenanceRequests;

import java.time.LocalDate;
import java.util.Objects;

import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.Entity;
import br.com.vital.maintenancerequest.domain.subsidiaries.Subsidiary;
import lombok.Getter;

@Getter
public class MaintenanceRequest extends Entity{

	private final RequestType requestType;
	private final Author requester;
	private final Author approver;
	private final Contract contract;
	private final LocalDate startDate;
	private final LocalDate requestDate;
	private StatusType status;
	private final Subsidiary subsidiary;
	private final String justification;

	public MaintenanceRequest(final Author requester, final Author approver, final Subsidiary subsidiary,
			final RequestType type, final String justification, final Contract contract, final LocalDate startDate, final StatusType status) {

		validate(requester, approver, subsidiary, type, justification, contract, startDate, status);

		this.requester = requester;
		this.approver = approver;
		this.subsidiary = subsidiary;
		this.requestType = type;
		this.justification = justification;
		this.contract = contract;
		this.startDate = startDate;
		this.requestDate = LocalDate.now();
		this.status = status;
	}

	public void cancel() {
		this.status = StatusType.CANCELED;
	}

	public void validate(final Author requester, final Author approver, final Subsidiary subsidiary, final RequestType type, final String justification, final Contract contract,
			final LocalDate startDate, final StatusType status) {

		if(Objects.isNull(contract)) {
			throw new DomainException("D00108", "The contract must be informed");
		}

		if(Objects.isNull(requester)) {
			throw new DomainException("D00109", "The requester must be informed");
		}

		if(Objects.isNull(approver)) {
			throw new DomainException("D00110", "The approver must be informed");
		}

		if(Objects.isNull(type)) {
			throw new DomainException("D00101", "The rquest type must be informed");
		}

		if(Objects.isNull(startDate)) {
			throw new DomainException("D00102", "The start date must be informed");
		} else if(isStartDateValid(startDate)) {
			throw new DomainException("D00103", "Start date invalid");
		}

		if(Objects.isNull(status)) {
			throw new DomainException("D00104", "The status must be informed");
		} else if(StatusType.PENDING != status) {
			throw new DomainException("D00105", "Status invalid");
		}

		if(Objects.isNull(subsidiary)) {
			throw new DomainException("D00106", "Subsidiary must be informed");
		}

		if(Objects.isNull(justification) || justification.trim().isEmpty()) {
			throw new DomainException("D00107", "Justification must be informed");
		}

	}

	private boolean isStartDateValid(final LocalDate startDate) {
		return startDate.isBefore(LocalDate.now().plusDays(1));
	}


}
