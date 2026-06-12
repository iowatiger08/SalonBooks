package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.service.EncryptionService;

public class EncryptionTest extends BaseTestCase {

  @Autowired EncryptionService encryptionService;

  @Test
  public void testEncryption() {
    String pswd = "password1";
    String encryptedPswd = encryptionService.encryptString(pswd);
    assertNotNull(encryptedPswd);
    String decryptedPswd = encryptionService.decryptString(encryptedPswd);
    assertTrue(StringUtils.equals(decryptedPswd, pswd));
  }

  @Disabled
  @Test
  public void testEncryptionCoded() {
    String pswd = "password1";
    String encryptedPswd = encryptionService.encryptString(pswd);
    assertNotNull(encryptedPswd);
    assertTrue(StringUtils.equals(encryptedPswd, "uJSU3i56jEmQv6GnNY4FYQ=="));
  }
}
