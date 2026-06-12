package org.tigersndragons.salonbooks.model.factory;

import org.apache.commons.lang3.StringUtils;
import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.type.ContactType;

public class ContactFactory {

    public static Contact createContact(String label, ContactType type) {
        return createContactForType(type, label);
    }

    public static Contact defaultContact() {
        return createContactForType(null, null);
    }

    private static Contact createContactForType(ContactType type, String label) {
        if (type == null || type.getId() == null) return defaultMobileContact("");
        int id = type.getId().intValue();
        if (id == 0) return defaultMobileContact(label);
        if (id == 1) return defaultHomePhoneContact(label);
        if (id == 4) return defaultTwitterContact(label);
        if (id == 6) return defaultEmailContact(label);
        return new Contact();
    }

    private static Contact defaultMobileContact(String label) {
        Contact newOne = new Contact();
        ContactType ct = new ContactType();
        ct.setId(0L);
        ct.setName("MOBILE_PHONE");
        newOne.setContactType(ct);
        newOne.setLabel(StringUtils.isEmpty(label) ? "3196210000" : label);
        newOne.setIsActive("Y");
        newOne.setIsURL("N");
        newOne.setFormat("PHONE:" + label);
        return newOne;
    }

    private static Contact defaultHomePhoneContact(String label) {
        Contact newOne = new Contact();
        ContactType ct = new ContactType();
        ct.setId(1L);
        ct.setName("HOME_PHONE");
        newOne.setContactType(ct);
        newOne.setLabel(StringUtils.isEmpty(label) ? "0001114444" : label);
        newOne.setIsURL("N");
        newOne.setFormat("PHONE:" + label);
        return newOne;
    }

    private static Contact defaultTwitterContact(String label) {
        Contact newOne = new Contact();
        ContactType ct = new ContactType();
        ct.setId(4L);
        ct.setName("TWITTER");
        newOne.setContactType(ct);
        if (StringUtils.isEmpty(label)) {
            newOne.setLabel("@default");
        } else if (label.charAt(0) != '@') {
            newOne.setLabel("@" + label);
        } else {
            newOne.setLabel(label);
        }
        newOne.setFormat("http://twitter.com/" + label);
        newOne.setContactType(ct);
        newOne.setIsURL("Y");
        return newOne;
    }

    private static Contact defaultEmailContact(String label) {
        Contact newOne = new Contact();
        ContactType ct = new ContactType();
        ct.setId(6L);
        ct.setName("EMAIL");
        newOne.setLabel(StringUtils.isEmpty(label) ? "mailto:handle@default.com" : label);
        newOne.setFormat("mailto:" + label);
        newOne.setContactType(ct);
        newOne.setIsURL("Y");
        return newOne;
    }
}
