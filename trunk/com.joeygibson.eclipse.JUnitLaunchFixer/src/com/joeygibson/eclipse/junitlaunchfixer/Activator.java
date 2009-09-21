/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.joeygibson.eclipse.junitlaunchfixer;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.joeygibson.eclipse.junitlaunchfixer.preferences.PreferenceConstants;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator
	extends AbstractUIPlugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "com.joeygibson.eclipse.junitlaunchfixer";

	// The shared instance
	private static Activator plugin;

	private ILaunchConfigurationListener launchConfigurationListener = new LaunchConfigurationListener();;

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

		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		if (store.getBoolean(PreferenceConstants.P_UPDATE_EXISTING_LAUNCHERS))
		{
			Runnable r = new Runnable()
			{
				@Override
				public void run()
				{
					Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

					ILaunchConfiguration[] launchers = null;

					try
					{
						launchers = launchManager.getLaunchConfigurations();
						
						if (launchers == null || launchers.length == 0)
						{
							return;
						}
					}
					catch (CoreException e)
					{
						e.printStackTrace();
					}

					List<ILaunchConfiguration> filteredLaunchers = LaunchProcessor.filterNonJUnitLaunchers(launchers);
					
					if (filteredLaunchers.size() == 0)
					{
						return;
					}
					
					System.out.printf("Launchers length: %d\n", filteredLaunchers.size());
					LauncherSelectionDialog dlg = new LauncherSelectionDialog(shell, filteredLaunchers,
							new ArrayContentProvider(), new LaunchLabelProvider(), "Select launchers to update");

					dlg.setHeapSize(store.getString(PreferenceConstants.P_MAX_HEAP));
					dlg.open();

					String heapSize = dlg.getHeapSize();

					if (heapSize != null && heapSize.length() > 0)
					{
						store.setValue(PreferenceConstants.P_MAX_HEAP, heapSize);

						Object[] res = dlg.getResult();

						if (res != null)
						{
							for (Object o : res)
							{
								ILaunchConfiguration config = (ILaunchConfiguration) o;

								LaunchProcessor.processVmArgs(config);
							}
						}
					}
				}
			};

			PlatformUI.getWorkbench().getDisplay().syncExec(r);

			store.setValue(PreferenceConstants.P_UPDATE_EXISTING_LAUNCHERS, false);
		}
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
