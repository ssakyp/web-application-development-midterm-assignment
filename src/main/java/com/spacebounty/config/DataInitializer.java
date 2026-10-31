package com.spacebounty.config;

import com.spacebounty.model.HuntLog;
import com.spacebounty.model.WantedCriminal;
import com.spacebounty.repository.HuntLogRepository;
import com.spacebounty.repository.WantedCriminalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(WantedCriminalRepository criminals, HuntLogRepository hunts) {
        return args -> {
            if (criminals.count() > 0) return;
            List<WantedCriminal> targets = List.of(
                    new WantedCriminal("Mara Voss", "The Comet", new BigDecimal("12500"), new BigDecimal("14.220"), new BigDecimal("-77.410"), "Known to raid courier routes.", 7, true, true, false),
                    new WantedCriminal("Unknown", "Void Jackal", new BigDecimal("5000"), new BigDecimal("-3.118"), new BigDecimal("44.901"), "Identity remains unconfirmed.", 5, true, false, true),
                    new WantedCriminal("Ilyan Ro", "Glass Knife", new BigDecimal("22000"), new BigDecimal("31.700"), new BigDecimal("8.020"), "Corporate espionage specialist.", 9, true, false, true),
                    new WantedCriminal("Tessa Quill", "Dust Queen", new BigDecimal("7800"), new BigDecimal("-22.700"), new BigDecimal("115.300"), "Controls a small smuggling crew.", 6, false, true, false),
                    new WantedCriminal("Bram Kade", "Rust Saint", new BigDecimal("1500"), new BigDecimal("2.501"), new BigDecimal("-12.110"), "Low profile salvage thief.", 2, false, false, false)
            );
            criminals.saveAll(targets);
            hunts.saveAll(List.of(
                    new HuntLog(targets.get(0), false, "Lost the trail near the relay station."),
                    new HuntLog(targets.get(0), true, "Captured after a three-day pursuit."),
                    new HuntLog(targets.get(1), false, "Target escaped through an asteroid field."),
                    new HuntLog(targets.get(1), false, "Decoy ship intercepted."),
                    new HuntLog(targets.get(2), false, "Armor was stronger than intelligence suggested."),
                    new HuntLog(targets.get(2), true, "Cornered in an abandoned mining ring."),
                    new HuntLog(targets.get(3), false, "Crew spotted us first."),
                    new HuntLog(targets.get(3), false, "Negotiation attempt failed."),
                    new HuntLog(targets.get(4), true, "Arrest completed without resistance."),
                    new HuntLog(targets.get(4), true, "Recovered stolen cargo and closed the case.")
            ));
        };
    }
}
