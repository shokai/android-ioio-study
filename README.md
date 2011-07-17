IOIO LED Blink
==============
blink LED

import IOIOLib
--------------

put [IOIOLib](http://codaset.com/ytai/ioio) in [workspace](http://gyazo.com/eabb43ed9c938ab32adcaff92c6a8289.png).


Build and Install
-----------------

use Eclipse+ADT, or

    android update project --path `pwd`
    ant debug
    adb install -r bin/MainActivity-debug.apk


Uninstall
---------

    adb uninstall org.shokai.ioio.ledblink
