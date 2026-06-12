package org.tigersndragons.salonbooks.core;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Marker class kept for reference; test classes now use @SpringBootTest directly.
 */
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class TestContextConfiguration {
}
