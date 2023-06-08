package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {

    @Scheduled(cron = "${cron.expression}")
    public void scheduledTask() {
        System.out.println("Cette tâche s'affiche toutes les minutes.");
    }
}
