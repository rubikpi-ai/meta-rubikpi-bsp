inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault qcom-ath6kl-utils"

RDEPENDS:${PN} = "property-vault"


PBT_ARCH = "armv8-2a"

PV = "1.0"

ARMV8_SHA256SUM = "87e118847a9daf09e4512cec4669f507466d489b72cb7dddf204454bdd814936"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
