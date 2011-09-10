/*******************************************************************************
 * Copyright (c) 2009, Joey Gibson, Chris DeLashmutt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.joeygibson.eclipse.junitlaunchfixer;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListSelectionDialog;

public class LauncherSelectionDialog
	extends ListSelectionDialog
{
	private String heapSize;
	
	private String maxPermSize;

	private Text maxHeapSpaceText;
	
	private Text maxPermSizeText;

	public LauncherSelectionDialog(Shell parentShell, Object input, IStructuredContentProvider contentProvider,
			ILabelProvider labelProvider, String message)
	{
		super(parentShell, input, contentProvider, labelProvider, message);
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = (Composite) super.createDialogArea(parent);

		Composite child = new Composite(composite, SWT.NONE);
		
		RowLayout layout = new RowLayout();
		layout.pack = false;
		
		child.setLayout(layout);

		// Max Heap Space
		new Label(child, SWT.NONE).setText("Max Heap Size:");
		maxHeapSpaceText = new Text(child, SWT.SINGLE | SWT.BORDER);
		
		maxHeapSpaceText.setText(heapSize);
		
		maxHeapSpaceText.addVerifyListener(new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent event)
			{
				event.doit = false;

				char myChar = event.character;

				// Allow 0-9
				if (Character.isDigit(myChar) || myChar == 'm' || myChar == 'M' ||
						myChar == 'g' || myChar == 'G')
				{
					event.doit = true;
				}
				
				// Allow backspace
				if (myChar == '\b')
				{
					event.doit = true;
				}
			}
		});
		
		// Max Perm Size
		new Label(child, SWT.NONE).setText("Max PermGen Size:");
		maxPermSizeText = new Text(child, SWT.SINGLE | SWT.BORDER);
		
		maxPermSizeText.setText(maxPermSize);
		
		maxPermSizeText.addVerifyListener(new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent event)
			{
				event.doit = false;

				char myChar = event.character;

				// Allow 0-9
				if (Character.isDigit(myChar) || myChar == 'm' || myChar == 'M' ||
						myChar == 'g' || myChar == 'G')
				{
					event.doit = true;
				}
				
				// Allow backspace
				if (myChar == '\b')
				{
					event.doit = true;
				}
			}
		});
		
		return composite;
	}

	public String getHeapSize()
	{
		return heapSize;
	}

	public void setHeapSize(String heapSize)
	{
		this.heapSize = heapSize;
	}

	@Override
	protected void okPressed()
	{
		heapSize = maxHeapSpaceText.getText();
		maxPermSize = maxPermSizeText.getText();
		
		super.okPressed();
	}

	public String getMaxPermSize()
	{
		return maxPermSize;
	}

	public void setMaxPermSize(String maxPermSize)
	{
		this.maxPermSize = maxPermSize;
	}
}
