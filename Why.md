# Introduction #

JUnitLaunchFixer is a simple plugin to automatically set the JVM max heap size parameter for any JUnit based launches in Eclipse.  In this way, you can use the plugin to automatically set the heap size of any JUnit launch created either manually, or automatically in Eclipse.

# Details #

This plugin came about due to the fact that we are working on a project that uses JUnit for testing, and the tests initially setup a very large environment for testing.  The testing environment was so large that the default JVM heap size just wasn't enough.  However, there didn't seem to be any way of setting the max heap for launched tests in Eclipse.  Thus, JUnitLaunchFixer was born.