/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 

package com.joeygibson.eclipse.junitlaunchfixer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.preference.IPreferenceStore;

import com.joeygibson.eclipse.junitlaunchfixer.preferences.PreferenceConstants;

public class LaunchProcessor 
{
	protected static Pattern pattern = Pattern.compile("-Xmx\\d+\\w");

	private static final String VMARGS_KEY = "org.eclipse.jdt.launching.VM_ARGUMENTS";

	private static final String TEST_KIND_KEY = "org.eclipse.jdt.junit.TEST_KIND";

	private static final String FAVORITE_GROUPS_KEY = "org.eclipse.debug.ui.favoriteGroups";
	
	private static final String RUN_GROUP_KEY = "org.eclipse.debug.ui.launchGroup.run";
	
	private static final String DEBUG_GROUP_KEY = "org.eclipse.debug.ui.launchGroup.debug";

	public static List<ILaunchConfiguration> filterNonJUnitLaunchers(ILaunchConfiguration[] configs)
	{
		List<ILaunchConfiguration> goodConfigs = new ArrayList<ILaunchConfiguration>();
		
		for (ILaunchConfiguration config : configs)
		{
			try
			{
				String testKind = config.getAttribute(TEST_KIND_KEY, "");

				if (testKind != null && testKind.length() > 0 && testKind.contains("junit"))
				{
					goodConfigs.add(config);
				}
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}
		}
		
		return goodConfigs;
	}
	
	public static void processVmArgs(ILaunchConfiguration config)
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String maxHeap = store.getString(PreferenceConstants.P_MAX_HEAP);

		try
		{
			String testKind = config.getAttribute(TEST_KIND_KEY, "");

			if (testKind == null || testKind.length() == 0 || !testKind.contains("junit"))
			{
				return;
			}

			String vmArgs = config.getAttribute(VMARGS_KEY, "");

			Matcher matcher = pattern.matcher(vmArgs);

			if (matcher.find())
			{
				vmArgs = matcher.replaceFirst("-Xmx" + maxHeap);
			}
			else
			{
				vmArgs += "-Xmx" + maxHeap;
			}

			ILaunchConfigurationWorkingCopy wc = config.getWorkingCopy();

			wc.setAttribute(VMARGS_KEY, vmArgs);
			
			// Ensure it still shows up in the dropdowns
			List<String> favorites = new ArrayList<String>();
			favorites.add(RUN_GROUP_KEY);
			favorites.add(DEBUG_GROUP_KEY);
			wc.setAttribute(FAVORITE_GROUPS_KEY, favorites);

			wc.doSave();
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
	}
}
