inherit cmake pkgconfig python3native systemd qprebuilt

DESCRIPTION = "Data Portability Layer for IP Multimedia Support(IMS) on Qualcomm modems"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "971d8759c4851eeffbee860bd4c63cf00d7972eda6f9ff9e45b9b2b0659c07db"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "virtual/kernel openssl glib-2.0 glib-2.0-native qmi-framework diag modemmanager"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
