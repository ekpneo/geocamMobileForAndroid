GeoCam Mobile Documentation
===========================

Hardware Requirements
---------------------
GeoCam Mobile, being an Android application, is best tested on actual hardware.
The phone we are targeting the closest, and indeed, the only phone we officially
support, is the `Motorola Droid`_ with Android 2.2.  Even with this, we would
prefer to keep this application mostly compatible with Android 1.6 devices, such
as the `HTC G1`_.  The other device that this software has been tested to work on
is the `Nexus One`_.  If you have success with other Android-based phones, please
let us know!

.. _`Motorola Droid`: http://www.motorola.com/Consumers/US-EN/Consumer-Product-and-Services/Mobile-Phones/Motorola-DROID-US-EN
.. _`HTC G1`: http://www.htc.com/www/product/g1/overview.html
.. _`Nexus One`: http://www.google.com/phone/detail/nexus-one

Simply Using GeoCam Mobile
--------------------------

.. image:: http://chart.apis.google.com/chart?cht=qr&chs=230x230&chl=market://search%3Fq%3Dpname:gov.nasa.arc.geocam.geocam
   :align: right

If you have no wish to contribute code to GeoCam Mobile and simply wish to use
it, you may simply download it from the android market.  It can be located
in the Android Market by searching for "Geocam."  Alternatively, you can scan
the QR code to the right with your phone.

Getting the code
----------------
There are two ways to get the code for tweaking on your own.  The first way is
geared towards people who do not want to mess with version control and don't
intend on contributing back to the project.

The second way is geared towards developers and people who already have a
familiarity with git and github, or intend on making modifications which they
would like to share or contribute back to the project.

The Easy Way
~~~~~~~~~~~~

The easiest way to grab the source code is to click the Downloads button on
the main repository's webpage_.  This will allow you to download either a 
tarball or zip file of the latest sources.  For the impatient, here is the
direct link for the tarball: 
https://github.com/geocam/geocamMobileForAndroid/tarball/master

When untarring or unzipping the source, you may see it create an oddly-named
directory to put the files into.  This is due to the tarball being generated
by github off of the latest commit.  It is perfectly fine, and encouraged,
that you rename this directory.  For the purposes of this documentation,
we assume the directory is called ``geocam-mobile``.

You may now skip to the section `Building the Application`_.

.. _webpage: https://github.com/geocam/geocamMobileForAndroid

The Git Way
~~~~~~~~~~~
Before you begin, you should make sure you have Git_ installed.  For an
excellent introduction to git, please see the `Pro Git`_ book.  It's free
to read online and a giant bottle of awesome-sauce.

Now would also be a good time to create a GitHub_ account if you don't
already have one.  While it's possible to clone our git repo without an
account, it's a lot easier to contribute your changes back if you're also
on GitHub_.

If you have a GitHub Account
############################
Click the Fork button on the main repo's webpage_.  This will create a 
copy of the repo within your github account. For more information about 
forking on GitHub_, see the GitHub help_ page on forking.

Next, you will want to clone the repo onto your development machine for
compiling and tweaking.  For those with a new GitHub_ account, be sure to 
setup your git environment.  You can find help for that here_.

You may clone the repo with the following command. Be sure to change ``$USER``
to be your github username:

``$ git clone git@github.com:$USER/geocamMobileForAndroid.git geocam-mobile``

This will clone the GeoCam Mobile git repo into a directory named 
``geocam-mobile``.  You may change the directory name to whatever name you 
wish, but this guide will assume that it is named ``geocam-mobile``.

If you do not have a GitHub Account
###################################
You will want to clone the repo onto your development machine for compiling 
and tweaking.  You clone the repo with the following command:

``$ git clone git://github.com/geocam/geocamMobileForAndroid.git geocam-mobile``

This will clone the GeoCam Mobile git repo into a directory named 
``geocam-mobile``.  You may change the directory name to whatever name you 
wish, but this guide will assume that it is named ``geocam-mobile``.

.. _`Pro Git`: http://progit.org/book/

.. _Git: http://git-scm.com/
.. _GitHub: http://github.com
.. _help: http://help.github.com/forking/
.. _here: http://help.github.com/

Building the Application
------------------------
Now that we have the source code, it's time to actually build the application
to test and use on a phone or in the emulator.  There are, again, two ways
of doing this.  The first way is Eclipse-based.  This is by far the easiest way,
but may lack some of the custom features you depend on in your favorite editor.

The second way is via the command-line.  It's meant for more advanced users,
and has the benefit that it embeds the current git commit information within 
the About dialog within the application.

Prerequisites
~~~~~~~~~~~~~
Before building, we should make sure that your development machine is properly
set up to build.  Here are the base prerequisites for building GeoCam Mobile:

* JDK: GeoCam Mobile is a java application when it all gets boiled down, so
  you will need to have a JDK_ installed on your machine.  Installing a JDK
  is not within the scope of this document, but it should be rather 
  straightforward.  Also, your machine should meet the minimum `system 
  requirements for Android`_ development.

* `Android SDK tools`_: You will want to download the latest SDK tools (r07 at
  the time of writing.)  Follow the instructions_ to install it.

* Android platform: Within the android SDK, you will want to make sure you have
  the *Android 2.2 SDK with Google-API* component installed, as that is the version 
  we compile against.

.. _JDK: http://www.oracle.com/technetwork/java/javase/downloads/index.html
.. _`system requirements for Android`: http://developer.android.com/sdk/requirements.html
.. _`Android SDK tools`: http://developer.android.com/sdk/index.html
.. _instructions: http://developer.android.com/sdk/installing.html

Building via Eclipse
~~~~~~~~~~~~~~~~~~~~
There are a few more requirements to install before you can build with Eclipse.

* Eclipse: First, if you don't have Eclipse installed already, you should 
  install it.  See the `Eclipse Downloads`_ page for obtaining it.  The 
  "Eclipse IDE for Java  Developers" is a fine version and will work just fine.

* Android Plugin for Eclipse: The Android developers have put together a plugin
  that will help you write Android-based applications within Eclipse.  Install
  it by following the instructions_.

After those things are installed, you should be all set to actually build an
installable application.  Now we will import the GeoCam Mobile application
source into eclipse.

* Select the *File->Import* menu option.
* Under the *General* folder, select *Existing Projects into Workspace* and
  click *Next*.
* Select the ``geocam-mobile`` directory as the root directory.  It should 
  display "GeoCamMobile" in the android subdirectory as a project to import.
* Make sure the *Copy projects inte workspace* is unchecked. Click Finish.

You should now be able to build a debug APK for the project.

* Under the *Project* menu, select the *Clean* option.
* You can either clean all the projects, or only GeoCam Mobile. Click *OK*.

It should now be rebuilding in the background.

.. _`Eclipse Downloads`: http://eclipse.org/downloads/

Note that we only have experience building on OS X (10.5 or higher) and Ubuntu
Linux 10.04 and 10.10.  Any Unix-like system should do.  Windows is a wild-card,
use at your own risk.

Building via Ant
~~~~~~~~~~~~~~~~
Building without Eclipse adds two more prerequisites to install before building.

* POSIX Shell: Be sure you have POSIX compliant shell.  This is used for
  embedding version information within the app.

* Ant: Ant is akin to Makefiles, but for Java.  Android uses ant to build their
  applications outside of Eclipse.  Your platform probably already has it 
  installed, but if not, make sure to install it.  It is in the aptitude 
  package manager on Ubuntu.  You will need at least Ant version 1.8.

After you obtain the source to the android application, we must setup the
project directory to build with ant.

First, we must find the ID of the 2.2 Google APIs that you installed with the
SDK: 

*Note* If the ``android`` utility is not in your path, you will need to put
in the full path to the tool explicitly.

::

    $ android list target
    Available Android targets:
    id: 1 or "android-8"
         Name: Android 2.2
         Type: Platform
         API level: 8
         Revision: 2
         Skins: HVGA (default), WVGA854, WVGA800, QVGA, WQVGA400, WQVGA432
    id: 2 or "Google Inc.:Google APIs:8"
         Name: Google APIs
          Type: Add-On
          Vendor: Google Inc.
          Revision: 2
          Description: Android + Google APIs
          Based on Android 2.2 (API level 8)
          Libraries:
           * com.google.android.maps (maps.jar)
               API for Google Maps
          Skins: WQVGA400, WVGA854, HVGA (default), WQVGA432, QVGA, WVGA800

*Note* The output you get may be different from the output listed above.  It is
just an example.  The output depends on what API targets you have installed.

Looking at the output, we see that the ID for the Android 2.2 API with Google
APIs is target id 2.

Now we must update our build parameters to use this target.  In the 
``geocam-mobile/android`` directory, run the following, substituting ``$ID`` 
with the id you found in the previous step.  If ``android`` isn't in your
path, you will have to append the full path to it explicitly.

::

    $ android update project -p . -t $ID

You should now be able to build the application with the following:

:: 

    $ ant debug

This should generate a bin/GeoCamMobile-debug.apk which you can install to your
device and test.
