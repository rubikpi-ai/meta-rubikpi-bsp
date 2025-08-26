inherit qprebuilt pkgconfig

SUMMARY = "Fan Management for RUBIK Pi 3"
DESCRIPTION = "When the temperature is too high, control the fan rotation"
HOMEPAGE = "https://github.com/rubikpi-ai/rubikpi3_thermal"
LICENSE = "CLOSED"

DEPENDS += "libnl"

FILESPATH =+ "${THISDIR}/:"
SRC_URI = "https://raw.githubusercontent.com/rubikpi-ai/prebuilt/HLOS-BINs/rubikpi3-thermal_1.1.0.tar.gz;downloadfilename=rubikpi3-thermal_1.1.0.tar.gz"
SRC_URI[sha256sum] = "510af8ab75910d1e4953011fcab3dd6a35ec1d9ad221c33fa8af1eb6d41b449e"

RDEPENDS:${PN} += "glibc"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/include/* \
    /usr/lib/systemd/system/* \
    /usr/lib/systemd/system/multi-user.target.wants/* \
    /etc/rubikpi/* \
"

FILES:${PN}-dev = ""

INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "already-stripped"
#The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "file-rdeps"

SYSTEMD_SERVICE:${PN} = "rubikpi-thermal.service"
