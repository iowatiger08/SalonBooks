package org.tigersndragons.salonbooks.model.flows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;

public abstract class BaseFlowContext implements  Serializable {
	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private List<Appointment> openAppointmentList = new ArrayList<Appointment>();
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Appointment> getOpenAppointmentList() {
		return openAppointmentList;
	}
	public void setOpenAppointmentList(List<Appointment> openAppointmentList) {
		this.openAppointmentList = openAppointmentList;
	}
}
