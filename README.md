# JUnitLaunchFixer

This plugin will automatically add a user-specified max heap setting to all new JUnit launchers. 
It also supports one-time scanning and updating of existing launchers.

## Introduction
JUnitLaunchFixer is a simple plugin to automatically set the JVM max heap size parameter for any 
JUnit based launchers in Eclipse. In this way, you can use the plugin to automatically set 
the heap size of any JUnit launch created either manually, or automatically in Eclipse.

## Details
This plugin came about due to the fact that we were working on a project that uses JUnit for testing, 
and the tests initially setup a very large environment for testing. The testing environment 
was so large that the default JVM heap size just wasn't enough. However, there didn't seem to be 
any way of setting the max heap for launched tests in Eclipse. Thus, JUnitLaunchFixer was born.

## Isn't This Really Old?
Yes, it is. I no longer use Eclipse, and I doubt anyone still uses this plugin, but on 3/12/2015, Google 
[announced](http://google-opensource.blogspot.com/2015/03/farewell-to-google-code.html) that they were
going to close [Google Code](https://code.google.com). So, to keep the code from dropping off into oblivion, I
moved it here. 

## Installation
To get the latest and greatest version of the plugin installed, you can use the Eclipse 
update manager with the update site of 
[https://github.com/joeygibson/junitlaunchfixer/tree/master/JUnitLaunchFixer-update-site](https://github.com/joeygibson/junitlaunchfixer/tree/master/JUnitLaunchFixer-update-site).
