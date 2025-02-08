inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camx qcom-adreno qcom-fastcv-binaries"

QCS9100_SHA256SUM = "b157ab2138009d7ac6af10e43c313658de8fdc7acf3601377f1eaec95ed1c2c3"
QCS8300_SHA256SUM = "2a3854bfcbe49fd04715500f2a3e3ebdb991ad29b1711641734921bb77166407"

SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/lib/rfsa/adsp/* \
    /usr/include/* \
    /lib/firmware/* \
    /system/etc/camera/*"
FILES:${PN}-dev = ""

#Skips check for .so symlinks
INSANE_SKIP:${PN} = "already-stripped"
#The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
