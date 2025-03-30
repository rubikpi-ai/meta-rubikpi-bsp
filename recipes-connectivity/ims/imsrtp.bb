inherit cmake python3native systemd qprebuilt

DESCRIPTION = "Real Time Protocols for IP Multimedia Support(IMS) on Qualcomm modems"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
PACKAGE_ARCH    ?= "${SOC_ARCH}"

QCM6490_SHA256SUM = "b6556fcbddc4593c8c9c67c6a2424132db027aa3415805a7bd09b6c726fd4d21"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI   = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

DEPENDS += "glib-2.0 qmi-framework diag imsdpl gstreamer1.0 gstreamer1.0-plugins-base"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${systemd_unitdir}/system/"
