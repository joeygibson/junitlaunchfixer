/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 

package com.joeygibson.eclipse.junitlaunchfixer;

import static com.joeygibson.eclipse.junitlaunchfixer.LaunchProcessor.processVmArgs;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;

public class LaunchConfigurationListener
	implements ILaunchConfigurationListener
{
	@Override
	public void launchConfigurationAdded(ILaunchConfiguration config)
	{
		processVmArgs(config);
	}

	@Override
	public void launchConfigurationChanged(ILaunchConfiguration config)
	{
	}

	@Override
	public void launchConfigurationRemoved(ILaunchConfiguration config)
	{
	}
}
