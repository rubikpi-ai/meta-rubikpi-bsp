inherit autotools pkgconfig

DESCRIPTION = "Bluetooth OBEX"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

DEPENDS += "glib-2.0 btvendorhal"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
SRCBRANCH  = "bt-performant.qclinux.1.0.r1-rel"
SRCREV     = "994ecfd61700075ee47270d282fcf2e3c80e4c36"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=bluetooth/btapp"

S = "${WORKDIR}"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

AUTOTOOLS_SCRIPT_PATH = "${S}/btapp/obex_profiles"

EXTRA_OECONF = "--with-glib"
EXTRA_OECONF += "--with-common-includes=${STAGING_INCDIR}"
