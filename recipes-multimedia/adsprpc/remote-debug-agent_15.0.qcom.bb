inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "To create remote debug agent for LE"

DEPENDS += "glib-2.0"

PBT_ARCH = "aarch64"

AARCH64_SHA256SUM = "43e2618e9f3c97551d4acf12944741cab43d7c27213421ad1b0104ce48132050"
SRC_URI[aarch64.sha256sum] = "${AARCH64_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}      = "${bindir}/*"

PACKAGECONFIG ?= "glib"
PACKAGECONFIG[glib] = "--with-glib, --without-glib, glib-2.0"
