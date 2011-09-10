/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.joeygibson.eclipse.junitlaunchfixer;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.viewers.LabelProvider;

public class LaunchLabelProvider
	extends LabelProvider
{
	@Override
	public String getText(Object element)
	{
		ILaunchConfiguration config = (ILaunchConfiguration) element;

		return config.getName();
	}
}
