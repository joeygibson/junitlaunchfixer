package com.joeygibson.eclipse.junitlaunchfixer.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.joeygibson.eclipse.junitlaunchfixer.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer
	extends AbstractPreferenceInitializer
{
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences()
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_UPDATE_EXISTING_LAUNCHERS, true);
		store.setDefault(PreferenceConstants.P_MAX_HEAP, "1G");
	}
}
