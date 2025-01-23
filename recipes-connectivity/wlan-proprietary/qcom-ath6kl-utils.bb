inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros ath6kl utils."

DEPENDS += "diag libnl glib-2.0"

PV = "1.0"

QCM6490_SHA256SUM = "4ccdf082bc2eacc052e046665cbbe9e7287b49a73227ed9fffa7a9da27ee3b15"
QCS9100_SHA256SUM = "c195c2c548950ab55353ed7bf7fd18afd25fba6fc2126feacd18f02157c91eab"
QCS8300_SHA256SUM = "a55207734d19ba34b6f95aa37e8fdc79694c84b0922fc6bf27f68ca71f639810"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
