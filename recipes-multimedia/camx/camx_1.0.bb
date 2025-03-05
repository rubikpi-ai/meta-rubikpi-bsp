inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxlib fastrpc"

QCS9100_SHA256SUM = "a44d839ae5d98c9b54b84558e9fff1b508bbb373e6dc01b724ef7827d282fc24"
QCS8300_SHA256SUM = "87f1e6d76e872c658e9281a5c62bfab4ce81e2365a4a6a932dc523d34212d668"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""

#Skips check for .so symlinks
INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

