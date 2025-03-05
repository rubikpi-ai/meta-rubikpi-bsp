inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "syslog-plumber glib-2.0 qmi-framework diag"

QCM6490_SHA256SUM = "5b1082e7b27fd135d285e1f8bb10c1c67ea1c2670d4933f50c9c6ebcf0827dcd"
QCS9100_SHA256SUM = "e6d1dbb8f5b55b281332f7ab97adb0a83272eb202bc6896c0e7043690472afee"
QCS8300_SHA256SUM = "7026a8753f21db4b191890ef32ad4ca40ad0a940e308160657eff4f48952de7a"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

