package com.joeygibson.eclipse.junitlaunchfixer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.preference.IPreferenceStore;

import com.joeygibson.eclipse.junitlaunchfixer.preferences.PreferenceConstants;

public class MinervaLaunchConfigurationListener
	implements ILaunchConfigurationListener
{
	protected Pattern pattern = Pattern.compile("-Xmx(\\d+\\w)");

	private static final String VMARGS_KEY = "org.eclipse.jdt.launching.VM_ARGUMENTS";

	private static final String TEST_KIND_KEY = "org.eclipse.jdt.junit.TEST_KIND";

	@Override
	public void launchConfigurationAdded(ILaunchConfiguration config)
	{
		processVmArgs(config);
	}

	protected void processVmArgs(ILaunchConfiguration config)
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
				vmArgs = matcher.replaceFirst(maxHeap);
			}
			else
			{
				vmArgs += "-Xmx" + maxHeap;
			}

			ILaunchConfigurationWorkingCopy wc = config.getWorkingCopy();

			wc.setAttribute(VMARGS_KEY, vmArgs);
			wc.doSave();
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
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
