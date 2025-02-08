SUMMARY = "AROSP"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://gsl/src/gsl_common.c;beginline=8;endline=10;md5=6f047b2e814de1595f895ac349329d91 \
                     file://gsl/inc/gsl_common.h;beginline=10;endline=12;md5=490aced097891fbcbebdd049f2ceb2a2"

inherit autotools pkgconfig

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/args.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "302af33ff80adb5a8eb2323752d21d68d28305c7"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/args"

S = "${WORKDIR}/audio/opensource/args"

DEPENDS = "glib-2.0 virtual/kernel diag diag-router"
DEPENDS += "linux-kernel-qcom-headers"

RDEPENDS:${PN} = "glib-2.0 diag diag-router"

EXTRA_OECONF += " --with-syslog --with-glib"
EXTRA_OECONF += " --with-sanitized-headers=${STAGING_INCDIR}/linux-kernel-qcom/usr/include"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

PACKAGECONFIG ??= "qcom"
PACKAGECONFIG[qcom] = "--with-qcom, --without-qcom"

RRECOMMENDS:${PN} = " \
   kernel-module-audio-pkt \
   kernel-module-spf-core"
