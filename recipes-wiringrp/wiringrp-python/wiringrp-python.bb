SUMMARY = "WiringPi-Python for RUBIK Pi"
LICENSE = "CLOSED"

DEPENDS += "libxcrypt wiringrp python3 swig-native"

SRC_URI =  " \
    git://github.com/rubikpi-ai/WiringRP-Python.git;protocol=https;branch=main;name=python;destsuffix=git/python \
    git://github.com/rubikpi-ai/WiringRP.git;protocol=https;branch=main;name=c;destsuffix=git/python/WiringPi \
"

SRCREV_python = "8b797fdde07d564f648bddba900507ac241eba6e"
SRCREV_c = "4bfb0de9f6605978e55ee2e89374b2eb2a84358d"

SRCREV_FORMAT = "python_c"

S = "${WORKDIR}/git/python"

inherit setuptools3 pkgconfig

CFLAGS:append = " -I${STAGING_INCDIR}/wiringpi"
LDFLAGS:append = " -L${STAGING_LIBDIR} -lwiringPi -lwiringPiDev"

export WIRINGPI_LIB_DIR = "${STAGING_LIBDIR}"
export WIRINGPI_INC_DIR = "${STAGING_INCDIR}/wiringpi"

SETUPTOOLS_BUILD_ARGS:append = " install"

FILES_${PN} += "${libdir}"
FILES_${PN} += "${includedir}"
