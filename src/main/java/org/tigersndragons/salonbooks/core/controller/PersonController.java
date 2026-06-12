package org.tigersndragons.salonbooks.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.PersonProfile;
import org.tigersndragons.salonbooks.model.flows.PersonFormModel;
import org.tigersndragons.salonbooks.model.type.GenderType;
import org.tigersndragons.salonbooks.service.AddressService;
import org.tigersndragons.salonbooks.service.PersonService;

@Controller
public class PersonController {
  @Autowired private PersonService personService;
  @Autowired PersonFormModel personFlowActions;
  @Autowired private AddressService addressService;

  final List<GenderType> genderList = new ArrayList<GenderType>(Arrays.asList(GenderType.values()));

  @RequestMapping(value = "/person/{phoneNumberEntered}", method = RequestMethod.GET)
  public String showPerson(
      @PathVariable("phoneNumberEntered") String phoneNumberEntered, Map<String, Object> model) {

    Person person = personService.lookupByPhoneNumber(phoneNumberEntered);
    PersonProfile profile = null;
    if (person.getLastName() != null && person.getId() != null) {
      profile = personService.getPersonProfile(person);
    } else {
      profile = personService.createPersonProfile(person);
    }

    personFlowActions.convertProfiletoFormModel(profile);
    model.put("personFlowActions", personFlowActions);
    model.put("primaryPhoneNumber", phoneNumberEntered);
    model.put("genderList", genderList);
    model.put("appointmentList", profile.getAppointments());
    return "person";
  }

  @RequestMapping(value = "/person/{phoneNumberEntered}", method = RequestMethod.POST)
  public String addEditPerson(
      @ModelAttribute("personFlowActions") PersonFormModel personFlowActions, Model model)
      throws ValidationException {
    PersonProfile p = personFlowActions.extractProfilefromModel();
    save(p);
    model.addAttribute("personProfile", p);
    model.addAttribute("primaryPhoneNumber", p.getPerson().getPrimaryPhoneNumber());
    return "person"; // new ModelAndView("person","person", model);
  }

  @Transactional
  private void save(PersonProfile p) {
    personService.save(p.getPerson());
    addressService.saveAddress(p.getAddress());
  }

  public Person createPersonForPhoneNumber(String phoneNumber) throws ValidationException {
    Person person = personService.createPerson(ServiceUtils.cleanPhoneNumber(phoneNumber));
    personService.save(person);
    return person;
  }

  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  public void setAddressService(AddressService addressService) {
    this.addressService = addressService;
  }

  public void setPersonFlowActions(PersonFormModel personFlowActions) {
    this.personFlowActions = personFlowActions;
  }
}
