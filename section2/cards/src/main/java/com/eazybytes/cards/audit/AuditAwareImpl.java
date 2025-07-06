package com.eazybytes.cards.audit;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */

    /**
     * Returns the current auditor, which is the user performing the action,
     * or the system if it is an automated process.
     *
     * @return the current auditor
     */
    /* <<<<<<<<<<  8e8f76b3-e6a9-4bb3-8817-4fbd4b35a966  >>>>>>>>>>> */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}