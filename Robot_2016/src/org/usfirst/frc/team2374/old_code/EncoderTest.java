package org.usfirst.frc.team2374.old_code;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class EncoderTest {

    Encoder encoder = new Encoder(1, 2);
    Talon T1;

    public void EncoderRate() {
        T1.set(1);
        Timer.delay(2);
        T1.set(0);
        double rate = encoder.getRate();
        double count = encoder.get();
        System.out.println("Encoder Counts per Feet:" + (rate));
    }
}
