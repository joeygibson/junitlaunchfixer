/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 

package com.joeygibson.eclipse.junitlaunchfixer;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator
	extends AbstractUIPlugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "com.spectrumk12.minerva.JUnitLaunchFixer";

	// The shared instance
	private static Activator plugin;

	private ILaunchConfigurationListener launchConfigurationListener = new MinervaLaunchConfigurationListener();;
	
	private ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
	
	/**
	 * The constructor
	 */
	public Activator()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context)
		throws Exception
	{
		super.start(context);
		plugin = this;
		
		launchManager.addLaunchConfigurationListener(launchConfigurationListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context)
		throws Exception
	{
		plugin = null;
		super.stop(context);
		
		launchManager.removeLaunchConfigurationListener(launchConfigurationListener);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
