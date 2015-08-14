package com.endovectors.uta.presentation.button;

import com.endovectors.uta.presentation.button.button_one.ButtonOne;
import com.endovectors.uta.presentation.button.button_three.ButtonThree;
import com.endovectors.uta.presentation.button.button_two.ButtonTwo;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class Decoder {

    final ButtonOne buttonOne;
    final ButtonTwo buttonTwo;
    final ButtonThree buttonThree;

    final GpioPinDigitalInput button1;
    final GpioPinDigitalInput button2;
    final GpioPinDigitalInput button3;

    final GpioController gpio = GpioFactory.getInstance();

    public Decoder (ButtonOne one, ButtonTwo two, ButtonThree three) {
        buttonOne = one;
        buttonTwo = two;
        buttonThree = three;

        button1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_UP);
        button2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_UP);
        button3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, PinPullResistance.PULL_UP);
        setup();
    }

    public void setup(){
        button1.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Button1 clicked");
                buttonOne.doClick();
            }
        });

        button2.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Button2 clicked");
                buttonTwo.doClick();
            }
        });

        button3.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Button3 clicked");
                buttonThree.doClick();
            }
        });
    }
}
