package com.voda.coronaVirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CoronaVirusApplication
{

	public static void main(final String[] args)
	{
		SpringApplication.run(CoronaVirusApplication.class, args);
		System.out.println("Starting");
	}

}
