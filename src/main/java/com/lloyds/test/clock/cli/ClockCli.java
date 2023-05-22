package com.lloyds.test.clock.cli;

import com.lloyds.test.clock.service.TimeProcessingService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class ClockCli implements CommandLineRunner {

    private TimeProcessingService timeProcessingService;
    public ClockCli(TimeProcessingService timeProcessingService){
        this.timeProcessingService = timeProcessingService;
    }

    @Override
    public void run(String... args) throws Exception {
        //log.info("Talking-clock(HH:mm):");
        System.out.println("Talking-clock(HH:mm):");
        Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){

                String line = scanner.nextLine();
                try {
                    //log.info(timeProcessingService.processTimeInHumanReadableFormat(line));
                    System.out.println(timeProcessingService.processCliTimeToHumanReadableText(line));
                }catch(Exception e){
                    log.error("Invalid Input:"+e.getMessage());
                }
                if (line.equals("##")) {
                    scanner.close();
                }
                //log.info("Talking-clock(HH:mm):");
                System.out.println("Talking-clock(HH:mm):");
            }
    }
}