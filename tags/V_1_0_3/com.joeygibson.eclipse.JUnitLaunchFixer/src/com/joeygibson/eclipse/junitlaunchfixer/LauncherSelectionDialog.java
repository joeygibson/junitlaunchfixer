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

	private Text text;

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

		new Label(child, SWT.NONE).setText("Max Heap Size:");
		text = new Text(child, SWT.SINGLE | SWT.BORDER);
		
		text.setText(heapSize);
		
		text.addVerifyListener(new VerifyListener()
		{
			@Override
			public void verifyText(VerifyEvent event)
			{
				event.doit = false;

				char myChar = event.character;

//				String text = ((Text) event.widget).getText();

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
		super.okPressed();

		heapSize = text.getText();
	}
}
