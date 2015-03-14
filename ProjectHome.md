This plugin will automatically add a user-specified max heap setting to all new JUnit launchers. It also supports one-time scanning and updating of existing launchers.

To get the latest and greatest version of the plugin installed, you can use the Eclipse update manager with the update site of http://junitlaunchfixer.googlecode.com/svn/trunk/JUnitLaunchFixer-update-site/.

## Latest News ##

**09/10/2011**: Updated to version 1.0.4. This version fixes a bug that caused it not to register new values when changed from the "on startup" dialog. It also allows you to set the -XX:MaxPermSize parameter.

**09/22/2009**: I have now tested JUnitLaunchFixer with three versions of Eclipse: Europa, Ganymede and Galileo. It works a treat with all three. This was on Windows7. I have tested with Ganymede on Snow Leopard, but I will test with the other two soon.

**09/22/2009**: I was seeing in some cases the launchers disappearing from the Run and Debug dropdown menus. I'm not entirely sure why it was happening, but I added some code that should prevent it from happening in the future.

**09/21/2009**: It looks like I got the problem with the update site resolved, so you should be OK to install from there. I've also tested with Ganymede on my Mac under Snow Leopard, and it works a treat.

**09/20/2009**: The first time the workbench is launched after installing the plugin, a list selection dialog will be shown allowing you to select which launchers to update, and to what heap size. After that, if you want to do it again, there's an option in the preferences. You don't have to update any of them, this is just a convenience, and it will only happen once, unless you want it to happen again.

This is the first official release of the plugin. So far, I have only tested it with Eclipse 3.4 (Ganymede), but I will test it shortly on Europa and Galileo. The only testing has been on Windows7, but I will be testing on my Mac Pro with Snow Leopard soon.

The update site is dorked up right now. It wants to install version 1.0.0, even though I've updated everything to 1.0.1. This is my first time dealing with Eclipse plugins, so it may take a while to figure this out. If you want to install it, please just download the source and build it yourself.