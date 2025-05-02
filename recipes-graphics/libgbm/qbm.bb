SUMMARY = "qbm"
DESCRIPTION = "Provide QC contributed QBM (Qualcomm Buffer Management) library."

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
BSD-3-Clause_LICENSE  = "file://inc/qbm.h;beginline=2;endline=27"
BSD-3-Clause-Clear_LICENSE = "file://inc/qbm.h;beginline=60;endline=90"

LIC_FILES_CHKSUM = " \
${BSD-3-Clause-Clear_LICENSE};md5=01550572dbf950bfb945602fe114d132 \
${BSD-3-Clause_LICENSE};md5=966a1bac2e99d152d17ed2b6d7ad8bab"

DEPENDS = "glib-2.0 wayland"

PROVIDES += "virtual/libqbm libqbm"

SRCPROJECT = "git://git.codelinaro.org/clo/le/display/libgbm.git;protocol=https"
SRCBRANCH  = "display.qclinux.1.0.r1-rel"
SRCREV     = "5eb3c08f78412718642498ba95178ee1b974a0d7"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=display/vendor/qcom/opensource/display/libgbm"

S = "${WORKDIR}/display/vendor/qcom/opensource/display/libgbm"

SRC_URI:append = " file://pkgconfig/qbm.pc "
SRC_URI:append = " file://files/0001-gbm-decouple-gbm-with-downstream-display-driver.patch "

inherit autotools-brokensep pkgconfig ptest

PACKAGE_ARCH = "${SOC_ARCH}"

PREBUILT = "1"

EXTRA_OECONF += "--with-sanitized-headers=${STAGING_INCDIR}/linux-msm/usr/include"
EXTRA_OECONF += " \
    --enable-compilewithdrm \
"

CFLAGS += "-I${STAGING_INCDIR}/glib-2.0/ -I${STAGING_LIBDIR}/glib-2.0/include"
CFLAGS += "-DUSE_GLIB"
LDFLAGS += "-lglib-2.0"


PACKAGE_ARCH ?= "${MACHINE_ARCH}"

# The headers for GBM are contained in a completely separate package. Force
# that subsidiary package to be installed anytime that gbm-dev is.
RPROVIDES:${PN} += "virtual/libqbm"

do_install:append(){
    install -d ${D}${libdir}/
    ln -sf libqbm.so  ${D}${libdir}/libqbm.so.1
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

PACKAGES = "${PN}-dbg ${PN}"
FILES:${PN}-dbg  = "${libdir}/.debug/* ${bindir}/.debug/* /usr/lib/.debug/*"
FILES:${PN}      = "${libdir}/* /usr/lib/* ${bindir}/* ${includedir}/*"
