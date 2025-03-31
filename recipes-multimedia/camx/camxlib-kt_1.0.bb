inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Camx"

DEPENDS += "syslog-plumber glib-2.0 property-vault camxapi-kt fastrpc qcom-adreno"

RDEPENDS:${PN} += "cameradlkm"

QCM6490_SHA256SUM = "2e27e4ff641c6b33737ade02ead07af6a128bde56c936c2d601ad095b6983b96"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Install firmware file at right location
relocate_firmware_files () {
    install -d ${D}${nonarch_base_libdir}/firmware/
    mv ${D}/lib/* ${D}${nonarch_base_libdir}/
    rm -rf ${D}/lib/
}
do_install[postfuncs] += "relocate_firmware_files"

FILES:${PN} = "\
    /usr/lib/* \
    /usr/bin/* \
    /usr/include/* \
    ${nonarch_base_libdir}/firmware/*"
FILES:${PN}-dev = ""

#Skips check for archtecture
INSANE_SKIP:${PN}-dbg = "arch"
INSANE_SKIP:${PN} = "arch"
# The modules require .so to be dynamicaly loaded
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "file-rdeps"
