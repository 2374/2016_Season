package org.usfirst.frc.team2374.robot.components;

import org.usfirst.frc.team2374.robot.Component;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class MountedCamera extends Component {

	public AxisCamera camera;
	public HSLImage currentImage;

	public MountedCamera(String cameraHost) { // cameraHost is typically the IP
												// address at which the camera
												// can be found
		camera = new AxisCamera(cameraHost);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (camera.isFreshImage()) {
			try {
				currentImage = camera.getImage();
			} catch (NIVisionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SmartDashboard.putData("Mounted Camera View",
					(NamedSendable) currentImage);
		}
	}

}
