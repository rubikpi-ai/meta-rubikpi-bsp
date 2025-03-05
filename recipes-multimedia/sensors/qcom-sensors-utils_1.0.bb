inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Sensors-utils Library"

DEPENDS += "glib-2.0 property-vault syslog-plumber"

QCM6490_SHA256SUM = "624a9e6f743ab7f60850e00b968f839e15359e0ade0c1f40b0199e12c3f28c39"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${includedir}/*"
FILES:${PN} += "/usr/lib/*"
FILES:${PN} += "/usr/bin/*"
FILES:${PN}-dev  = "${libdir}/*.la ${includedir}"


INSANE_SKIP:${PN} = "dev-so"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
