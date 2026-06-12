package org.tigersndragons.salonbooks.model.flows;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.exception.LoginNotFoundException;
import org.tigersndragons.salonbooks.exception.PersonNotFoundException;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.PersonService;

@Component
@Getter
@Setter
public class SalonFlows implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonService personService;
    @Autowired
    private EmployeeService employeeService;

    private String entityMonth;
    private Long entityDate;
    private Long entityHour;
    private Long entityMinute;
    private Long entityYear;
    private TimeZone localeTZ;

    public final int[] DATES = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    public final int[] HOURS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    public final String[] MONTHS = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    public final int[] YEARS = {2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026};
    public final int[] MINUTES = buildMinutes();

    private int[] buildMinutes() {
        int[] minutes = new int[60];
        for (int i = 0; i < 60; i++) minutes[i] = i;
        return minutes;
    }

    public Person lookupCustomer(String phoneNumber) throws PersonNotFoundException, ValidationException {
        Person customer = personService.lookupByPhoneNumber(cleanPhoneNumber(phoneNumber));
        if (customer != null) {
            return customer;
        }
        return personService.createPerson(phoneNumber);
    }

    public String cleanPhoneNumber(String number) throws ValidationException {
        return ServiceUtils.cleanPhoneNumber(number);
    }

    public Employee loginEmployee(String username, String password) throws LoginNotFoundException {
        try {
            ServiceUtils.assertNotNull("username cannot be null", username);
            ServiceUtils.assertNotNull("password cannot be null", password);
            return employeeService.getEmployee(username, password);
        } catch (IllegalArgumentException e) {
            throw new LoginNotFoundException("Invalid login credentials");
        }
    }

    public void convertEntityDateToModel(LocalDateTime entityDt) {
        LocalDateTime shifted = entityDt.atOffset(ZoneOffset.ofHours(-6)).toLocalDateTime();
        entityDate = (long) shifted.getDayOfMonth();
        entityHour = (long) shifted.getHour();
        entityMinute = (long) shifted.getMinute();
        entityMonth = shifted.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        entityYear = (long) shifted.getYear();
        localeTZ = TimeZone.getTimeZone(ZoneOffset.ofHours(-6));
    }

    public LocalDateTime convertModelToDateTime() {
        if (entityYear == null || entityDate == null) {
            return LocalDateTime.now();
        }
        return LocalDateTime.of(
            entityYear.intValue(),
            convertMonthString(entityMonth),
            entityDate.intValue(),
            entityHour == null ? 0 : entityHour.intValue(),
            entityMinute == null ? 0 : entityMinute.intValue()
        );
    }

    private int convertMonthString(String month) {
        if (month == null) throw new IllegalArgumentException("no month provided");
        for (int i = 0; i < MONTHS.length; i++) {
            if (StringUtils.equalsIgnoreCase(MONTHS[i], month)) return i + 1;
        }
        return 0;
    }
}
