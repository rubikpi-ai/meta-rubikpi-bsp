inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ath11k-fwtest"

DEPENDS += "libnl"

PV = "1.0"

QCM6490_SHA256SUM = "be8fd5fec3b840f0dfe98d533fbc2b8995ba7aef74ab9a9240db681ec6bd01c9"
QCS9100_SHA256SUM = "cbbd7a2d5ff24fe9d258a648127f8406a0dc879fd6ca4c74861b5cc5d94cb858"
QCS8300_SHA256SUM = "81306c5ed0ef872cea761f8acc058c4ad3afe9c26dfe4ed02aa4e0098cfada0b"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
