package com.shadow.constant;

import java.io.File;

public class CommonConstant {

	public static final String USERDIR = System.getProperty("user.dir"),
			SCREENSHOTPATH = USERDIR + File.separator + "target"+File.separator+"surefire-reports"+File.separator+"html"+File.separator+"screenshots",
			APPLICATIONPROP = USERDIR + File.separator + "application.properties",
			TESTDATA=USERDIR + File.separator+"testFile";


}
