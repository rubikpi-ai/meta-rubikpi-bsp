inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxlib-kt cameradlkm fastrpc qcom-sensinghub qcom-sensors-utils qcom-sensors-core qmi-framework abseil-cpp"

QCM6490_SHA256SUM = "1718ff4706194b6116fb2bdff0e1c5413d29a9d1259081e852b9fd3df008c921"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    /lib/firmware/*"
FILES:${PN}-dev = ""


INSANE_SKIP = "1"
INSANE_SKIP:${PN} = "dev-so"

