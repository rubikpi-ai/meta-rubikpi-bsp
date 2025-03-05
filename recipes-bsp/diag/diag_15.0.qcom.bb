inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Library and routing applications for diagnostic traffic"

DEPENDS += "glib-2.0 time-genoff"

QCM6490_SHA256SUM = "b0b79d77ab0cc2a577994a0a13c2fab90ec6b8338f388e6635861faebaef678c"
QCS9100_SHA256SUM = "aad9d7431b398acc115fbc90508f416df7a21daab8a14664320a0defd363ef7c"
QCS8300_SHA256SUM = "6048978bdbf71c4bfe1bf05981d73dd2cfd71ff343c64c707ccf1e8c6ac20093"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${systemd_unitdir}/system/"

