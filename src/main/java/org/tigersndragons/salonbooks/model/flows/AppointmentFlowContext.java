package org.tigersndragons.salonbooks.model.flows;

import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;

public class AppointmentFlowContext extends BaseFlowContext {
	private static final long serialVersionUID = 1L;
	
	private Appointment cmp;
	private boolean inEnrollmentFlow = false;
	
	public AppointmentFlowContext(Appointment program, Employee emp) {
		cmp = program;
		setEmployee(emp);
	}
	public AppointmentFlowContext() {
	}

	public Appointment getCmp() {
		return cmp;
	}

	public void setCmp(Appointment cmp) {
		this.cmp = cmp;
	}

	public boolean isInEnrollmentFlow() {
		return inEnrollmentFlow;
	}

	public void setInEnrollmentFlow(boolean inEnrollmentFlow) {
		this.inEnrollmentFlow = inEnrollmentFlow;
	}


}
