package com.redhat.foo.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Delay {

  private static final Logger LOGGER=LoggerFactory.getLogger(Delay.class);

  public static void execute() {
    try {
      int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
      TimeUnit.SECONDS.sleep(randomNum);
    } catch (InterruptedException e) {
      LOGGER.info("Error while executing the delay");

    }
  }

}
